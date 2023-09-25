# coding : utf-8

import os
import time
import sys
import json
import requests
from aliyun_client import AliyunClient

worker_env_anchor = """${worker_env}"""
worker_list_anchor = """${idList}"""
worker_resource_anchor = """${worker_resource}"""
worker_size_anchor = """${worker_size}"""
master_ip_anchor = """${master_ip}"""

def create_worker_ids(size) :
    id_list = []
    for i in range(1, size+1):
        id_list.append("\n        - raycluster-sample-8c64g-worker-" + str(i))
    return ''.join(id_list)

def create_worker_env(cpu, mem) :
    return """"MEM": {}, "CPU": {}""".format(mem, cpu)

def create_worker_resource(cpu, mem, disk):
    return """
        limits:
          cpu: '{}'
          ephemeral-storage: {}Gi
          memory: {}Gi
        requests:
          cpu: '{}'
          ephemeral-storage: {}Gi
          memory: {}Gi""".format(cpu, disk, mem, int(cpu * 0.7), int(disk * 0.7), int(mem * 0.7))

def create_deploy_yaml(master_info, worker_info):
    master_ip = master_info['ip']
    with open('deployment.yaml.template', 'r') as f:
        data = f.read()
    data = data.replace(master_ip_anchor, master_ip)
    with open('deploy/deployment.yaml', 'w') as f:
        f.write(data)

    with open('raycluster.yaml.template', 'r') as f:
        data = f.read()
    data = open('raycluster.yaml.template').read()
    worker_ids = create_worker_ids(worker_info['number'])
    worker_env = create_worker_env(worker_info['cpu'], worker_info['memory'])
    worker_resource = create_worker_resource(worker_info['cpu'], worker_info['memory'], worker_info['disk'])

    data = data.replace(master_ip_anchor, master_ip)
    data = data.replace(worker_list_anchor, worker_ids)
    data = data.replace(worker_env_anchor, worker_env)
    data = data.replace(worker_resource_anchor, worker_resource)
    data = data.replace(worker_size_anchor, str(worker_info['number']))

    with open('deploy/raycluster.yaml', 'w') as f:
        f.write(data)

def execute_command_slient(command, sleep=1):
    print(command)
    res = os.system(command)
    assert res==0
    time.sleep(sleep)

def wait_process(i):
    print("\r", end="")    
    print("waiting: {} seconds".format(i), "▓" * int(i / 5), end="")    
    sys.stdout.flush()    

def execute_command_result(command):
    with os.popen(command) as f:
        return f.read()

def wait_deploy_success(num):
    res = ""
    i = 0
    while(res.count("Running") != num):
        time.sleep(5)
        i += 5
        wait_process(i)
        res = execute_command_result('./kubectl  --kubeconfig kube.yaml -n geaflow get po')

def get_ingress_ip():
    ingress = execute_command_result('./kubectl  --kubeconfig kube.yaml -n geaflow get ingress')
    while(ingress.count('\n')) != 3:
        time.sleep(1)
    ip = ingress.split('\n')[1].split()[3]
    while (ip == "80"): 
        time.sleep(1)
        ingress = execute_command_result('./kubectl  --kubeconfig kube.yaml -n geaflow get ingress')
        ip = ingress.split('\n')[1].split()[3]
    return ip


def _register_content(revision, cpu, mem, size):
    content = {}
    content['revision'] = revision
    content['name'] = 'geaflow'
    content['namespaceId'] = 'geaflow'
    content['enableSubNamespaceIsolation'] = True
    nodeList = [{'group':'2c8g', 'nodeCount': 1}]
    worker_group = "{}c{}g".format(cpu, mem)
    nodeList.append({'group': worker_group, 'nodeCount': size})
    content['nodeShapeAndCountList'] = nodeList
    res = requests.post("http://raycluster-sample.ray.tugraph.com/namespaces_v3", json.dumps(content), timeout=100)
    return json.loads(res.text)


def register_content(worker_info):
    cpu = worker_info['cpu']
    mem = worker_info['memory']
    size = worker_info['number']
    res = _register_content(0, cpu, mem, size)
    if res['result'] == False:
        revision = res['data']['revision']
        _register_content(revision, cpu, mem, size)

def _check_deploy_ok():
    code = 0
    while code != 200:
        time.sleep(5)
        res = requests.get("http://raycluster-sample.ray.tugraph.com/namespaces?show_node_spec=1")
        code = res.status_code
    return json.loads(res.text)


def check_deploy_ok(size):
    res = _check_deploy_ok()
    while res['result'] == False:
        print('error ' + res)

    i = 0
    while len(res['data']['namespaces'][0]['hostNameList']) != size:
        time.sleep(5)
        i += 5
        wait_process(i)
        res = _check_deploy_ok()


if __name__ == '__main__':
    ak = os.getenv('AK')
    sk = os.getenv('SK')
    cluster_name = os.getenv('CLUSTER_NAME')
    aliyun_client = AliyunClient(ak, sk)
    deploy_file_name = 'deploy.tar.gz'
    aliyun_client.get_oss_file(deploy_file_name)
    execute_command_slient('tar -zxvf {} && rm -f {}'.format(deploy_file_name, deploy_file_name))
    cluster_id = aliyun_client.get_cluster_id(cluster_name)
    aliyun_client.save_kube_config(cluster_id)
    node_ids = aliyun_client.get_node_ids(cluster_id)
    node_attrs = aliyun_client.get_nodes_attr(node_ids)
    master_info = aliyun_client.get_master_info(node_attrs)
    with open('master_info', 'w') as f:
        f.write(json.dumps(master_info))
    worker_info = aliyun_client.get_worker_info(node_attrs)
    with open('worker_info', 'w') as f:
        f.write(json.dumps(worker_info))

    create_deploy_yaml(master_info, worker_info)
    execute_command_slient('./kubectl  --kubeconfig kube.yaml create -f ./crds')
    execute_command_slient('./kubectl  --kubeconfig kube.yaml create -f ./200-sa.yaml')
    execute_command_slient('./kubectl  --kubeconfig kube.yaml -n geaflow create -f ./deploy')
    wait_deploy_success(worker_info["number"] + 3)

    time.sleep(5)
    execute_command_slient('./kubectl  --kubeconfig kube.yaml -n geaflow delete svc raycluster-sample-default-head')
    execute_command_slient('./kubectl  --kubeconfig kube.yaml -n geaflow create -f ./ingress', 5)
    ingress_ip = get_ingress_ip()
    execute_command_slient('echo {} raycluster-sample.ray.tugraph.com >> /etc/hosts'.format(ingress_ip))
    time.sleep(5)
    check_deploy_ok(worker_info["number"] + 1)
    register_content(worker_info)
    print("\ndeploy success")
