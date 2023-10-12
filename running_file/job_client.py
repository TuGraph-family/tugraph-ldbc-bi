import logging
import argparse
import requests
import json
import time
import os
import sys
import socket
import math

logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)

parser = argparse.ArgumentParser(description='deploy geaflow.')
parser.add_argument('action', type=str, help='start, stop')
args = parser.parse_args()

ak = os.getenv('AK')
sk = os.getenv('SK')
name = os.getenv('JOB_NAME')
bucket_name = os.getenv('BUCKET')
endpoint = os.getenv('ENDPOINT')
oss_url = "http://" + bucket_name + "." + endpoint
host = "http://" + os.getenv('K8S_INGRESS')
partition = int(os.getenv('PARTITION'))
driver_ip_port = os.getenv('TuGraph_ENDPOINT').split(':')
worker_jvm_gb = int(os.getenv('WORKER_JVM_GB'))
MB = 1024 * 1024

jobPackageUrl = oss_url + "/geaflow.zip"
driverEntry = "com.antfin.gryphon.server.GryphonServer"
jarList = [
    {
        "name": "rayag",
        "version": "0.9",
        "url": oss_url + "/" + os.getenv('JAR'),
        "md5": os.getenv('JAR_MD5')
    }
]

class GeaflowClient:

    def __init__(self, endpoint, namespaceId, worker_info):
        self.endpoint = endpoint
        self.worker_info = worker_info
        self.namespaceId = namespaceId

    def get_worker_memory_mb(self):
        res = requests.get(self.endpoint + '/nodes?view=summary')
        nodes = json.loads(res.text)
        for node in nodes['data']['summary']:
            node_info = node['raylet']
            if node_info['nodeManagerHostname'] == "raycluster-sample-8c64g-worker-1":
                node_id = node_info['nodeId']
                node_mem = node['mem']['total']
        res = requests.get(self.endpoint+ '/nodes/' + node_id)
        mem = json.loads(res.text)['data']['detail']['raylet']['resourcesTotal']['memory']
        return max(int(mem/MB) - 5 * 1024, int(node_mem/MB))


    def submitJob(self, mainClass, jobPackageUrl, jarList):
        jobParams = self.generateSubmitParams(mainClass, jobPackageUrl, jarList)
        return requests.post(self.endpoint + "/jobs", json.dumps(jobParams), headers=self.getHeaders(), timeout=100)

    def queryJobStatus(self, jobId):
        return requests.get(self.endpoint + "/jobs/" + jobId, params={'view': 'state'}, headers=self.getHeaders(), timeout=100)

    def stopJob(self, jobId):
        return requests.delete(self.endpoint + "/jobs/" + jobId, params={}, headers=self.getHeaders(), timeout=100)

    def getHeaders(self):
        return {"Accept": "application/json"}

    def generateSubmitParams(self, mainClass, jobPackageUrl, jarList):
        
        jobParams = {}
        jobParams["namespaceId"] = self.namespaceId
        jobParams["owner"] = "admin"
        jobParams["language"] = "JAVA"
        jobParams["url"] = jobPackageUrl
        jobParams["driverEntry"] = mainClass
        jobParams["dependencies"] = {
            "python": [],
            "archive": [],
            "java": jarList
        }
        jobParams["jvm_Options"] = ["-Dray.task.return_task_exception=false"]

        jobParams["name"] = name
        maximum_worker_processes = int(self.worker_info['memory'] / worker_jvm_gb / 1.5)

        while True:
            try:
                mem = self.get_worker_memory_mb()
                break
            except KeyError:
                time.sleep(5)

        clusterArgs = {
            "containerMemory": mem,
            "num_workers_per_process_java": math.ceil(partition /  self.worker_info['number'] / maximum_worker_processes),
            "driver_jvm_args": "-Xmx6g,-Xms6g,-Xmn2g",
            "containerVcores": int(self.worker_info['cpu']),
            "workNodeNumber": int(self.worker_info['number'] + 1),
            "maximum_worker_processes": maximum_worker_processes,
            "jvm_args": "-Xmx{}g,-Xms{}g,-XX:MaxDirectMemorySize={}g,-Dray.job.logging-level=WARN,-XX:MaxGCPauseMillis=3000".format(worker_jvm_gb, worker_jvm_gb, int(worker_jvm_gb / 2))
        }
        numJavaWorkersPerProcess = clusterArgs["num_workers_per_process_java"]
        totalMemoryMb = clusterArgs["containerMemory"]
        workNodeNumber = clusterArgs["workNodeNumber"]
        jobParams["numJavaWorkersPerProcess"] = numJavaWorkersPerProcess
        jobParams["numInitialJavaWorkerProcesses"] = workNodeNumber
        jobParams["totalMemoryMb"] = totalMemoryMb
        jobParams["javaWorkerProcessDefaultMemoryMb"] = 500
        jobParams["env"] = {
            "JOB_NODE_COUNT": workNodeNumber
        }
        job_args = {
            "rayag.state.job.id" : name,
            "partition.num": partition,
            "geaflow.state.oss.bucket.name": bucket_name,
            "geaflow.state.oss.endpoint": endpoint,
            "geaflow.state.oss.access.id": ak,
            "geaflow.state.oss.secret.key": sk
        }
        driverArgs = {'args' : job_args}
        driverArgs['args'] = job_args
        driverArgs['clusterArgs'] = clusterArgs
        jobParams["driverArgs"] = ["'" + json.dumps(driverArgs) + "'"]
        return jobParams

    def wait_port_open(self, ip, port):
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
            sleep_second = 5
            times = 1
            while sock.connect_ex((ip, port)) != 0:
                time.sleep(sleep_second)
                print("\r", end="")    
                print("waiting port open: {} seconds".format(times * sleep_second), "▓" * times, end="")    
                sys.stdout.flush()    
                times = times + 1


if __name__ == "__main__":
    worker_info = json.loads(open('deploy_cluster/worker_info').read())
    client = GeaflowClient(host, "geaflow", worker_info)
    if args.action == "start":
        result = client.submitJob(mainClass=driverEntry, jobPackageUrl=jobPackageUrl, jarList=jarList)
        data = json.loads(result.text)
        if data["result"]:
            jobId = data["data"]["jobId"]
            with open('job_id', 'w') as f:
                f.write(jobId)
            time.sleep(5)
            res = json.loads(client.queryJobStatus(jobId).text)
            while res['data']['state'] != 'RUNNING':
                time.sleep(5)
                res = json.loads(client.queryJobStatus(jobId).text)
                if res['data']['state'] == 'FAILED':
                    print('fail to start, check!!!')
                    exit(1)
            client.wait_port_open(driver_ip_port[0], int(driver_ip_port[1]))
            print('\nstart success')
    elif args.action == "stop" and os.path.isfile('job_id'):
        with open('job_id', 'r') as f:
            jobId = f.read()
        client.stopJob(jobId)
        time.sleep(5)
        res = json.loads(client.queryJobStatus(jobId).text)
        state = res['data']['state']
        while state != 'CANCEL' and state != 'FAILED':
            time.sleep(5)
            res = json.loads(client.queryJobStatus(jobId).text)
            state = res['data']['state']


