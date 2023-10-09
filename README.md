# Benchmark on Aliyun Cluster

# Benchmark on Aliyun Cluster

## Set up the cluster
### A. Create K8s Cluster
Create Aliyun K8s Cluster
* version:  1.26.3-aliyun.1, enhanced version based on Kubernetes 1.26.
* Nodes:
    * 72 ecs.r7.16xlarge(64 vCPU 512 GiB) with ESSD AutoPL (capacity 1024GB) as workers
        *  throughput 1131 MB/s， iops 50k
        *  Intel(R) Xeon(R) Platinum 8369B CPU @ 2.70GHz, 64 cores, 512G, Alibaba Group Enterprise Linux Server release 7.2 (Paladin)
    * 1 ecs.g6.2xlarge(8 vCPU 32 GiB) as master with ESSD P0 (capacity 80G)
        * Intel(R) Xeon(R) Platinum 8269CY CPU @ 2.50GHz, 8 cores, 32G, Alibaba Group Enterprise Linux Server release 7.2 (Paladin)

### B. Configure intances and Set up TuGraph Cluster


1. Login the deploy machine which will also be the LDBC driver running machine.

```sh
ssh root@{DRIVER_MACHINE}
```
* The deployment is processed using the aliyun intranet.
* Beforehand, the operator's ip addresses or ip ranges should be recorded to the aliyun network whitelist.

2. clone repro from github
```sh
git clone https://github.com/TuGraph-family/tugraph-ldbc-bi
cd tugraph-ldbc-bi/running_file/
```

3. get the deployment and driver scripts and install necessary dependency
```sh
sh scripts/install-dependency.sh
```


4. edit environment variables
```sh
vim scripts/vars.sh

# fill the variables below.

# sf scale.
export SF=
# remote path for origin data.
export OSS_DIR=
# k8s cluster name.
export CLUSTER_NAME=
# aliyun access_key_id.
export AK=
# aliyun access_key_secret.
export SK=
# graph store partition number.
export PARTITION=
```   

5. download images and deploy TuGraph cluster

```sh
# cost about 10min
nohup scripts/deploy.sh > deploy.log 2>&1 < /dev/null &

```
* the cluster nodes information is recored in `deploy_cluster/cluster_nodes` file.
* you can login any node by password.

## Download Data
The data is on Aliyun object storage, and the tugraph loader can directly load it.


## Update Environment Config
Add parameters to the directory:  ./parameters
The parameter directory name should be patterned: `parameters-sf{SF}`


## Run benchmark
```sh
nohup ./scripts/run-benchmark.sh 1>benchmark.log 2>&1 < /dev/null &
```
The benchmark result can be found in the `output/output-sf{SF}`:
- load.csv

  graph load time
- timings.csv

  bi query execute time
- results.csv
  
  bi query execute result

# Quick View of SF Benchmark Running
## Prepare Step
```sh
ssh root@{DRIVER_MACHINE}
rm -rf ldbc && mkdir ldbc && cd ldbc

git clone https://github.com/TuGraph-family/tugraph-ldbc-bi
cd tugraph-ldbc-bi/running_file/

sh scripts/install-dependency.sh

# if you have already setup cluster, clean the k8s cluster first.
# sh scripts/delete_k8s.sh
```

## Run SF10


```sh
# fill the variables below.
# export SF=10
# export NUM_NODES=1
# export PARTITION=19
vim scripts/vars.sh

# setup cluster.
nohup sh scripts/deploy.sh > deploy.log 2>&1 < /dev/null &
tail -f deploy.log

# run benchmark
nohup sh scripts/run-benchmark.sh 1>benchmark.log 2>&1 < /dev/null &
tail -f benchmark.log

```  

## Run SF100
```sh
# fill the variables below.
# export SF=100
# export NUM_NODES=1
# export PARTITION=99
vim scripts/vars.sh

# setup cluster.
nohup sh scripts/deploy.sh > deploy.log 2>&1 < /dev/null &
tail -f deploy.log

# run benchmark
nohup sh scripts/run-benchmark.sh 1>benchmark.log 2>&1 < /dev/null &
tail -f benchmark.log

``` 

## Run SF30000
```sh
# fill the variables below.
# export SF=30000
# export NUM_NODES=72
# export PARTITION=1151
vim scripts/vars.sh

# setup cluster.
nohup sh scripts/deploy.sh > deploy.log 2>&1 < /dev/null &
tail -f deploy.log

# run benchmark
nohup sh scripts/run-benchmark.sh 1>benchmark.log 2>&1 < /dev/null &
tail -f benchmark.log

``` 

# Appendix
## Login ecs
All ecs nodes information is recored in `cluster_nodes` file, you can login by 
```sh
sshpass -e ssh -o StrictHostKeyChecking=no root@{IP_ADDRESS}
```

## Login pod
```sh
# get pod
deploy_cluster/kubectl  --kubeconfig deploy_cluster/kube.yaml -n geaflow get pods -o wide
# login
deploy_cluster/kubectl  --kubeconfig deploy_cluster/kube.yaml -n geaflow exec -it ${pod_name} -- /bin/bash

# for example
deploy_cluster/kubectl  --kubeconfig deploy_cluster/kube.yaml -n geaflow exec -it raycluster-sample-8c64g-worker-1 -- /bin/bash
```

## Collect graph statistics info
# set sf=10
```sh
nohup sh scripts/data-statistics.sh 1>statistics.log 2>&1 < /dev/null &
tail -f statistics.log
```
The graph statistics result can be found in the `output/output-sf{SF}/statistics.csv`.

## Generate validate result
```sh
nohup ./scripts/run-benchmark.sh --validate 1>validate.log 2>&1 < /dev/null &
tail -f validate.log
```
The query result can be found in the `output/output-sf{SF}/results.csv`.


