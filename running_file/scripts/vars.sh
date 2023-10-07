pushd . > /dev/null

cd "$( cd "$( dirname "${BASH_SOURCE[0]:-${(%):-%x}}" )" >/dev/null 2>&1 && pwd )"
cd ..

# sf scale.
export SF=
 # number of pods or nodes
export NUM_NODES=
# remote path for origin data.
export OSS_DIR=
# aliyun access_key_id.
export AK=
# aliyun access_key_secret.
export SK=
# graph store partition number.
export PARTITION=
# k8s cluster name.
export CLUSTER_NAME=geaflow-ldbc
# jvm heap size for each worker process.
export WORKER_JVM_GB=78


if [ -e "driver.ip" ];then
  export TuGraph_DRIVER_IP=$(head -n 1 ./driver.ip)
else
  export TuGraph_DRIVER_IP=127.0.0.1
fi

if [ -e "ingress.ip" ];then
  export K8S_INGRESS=$(head -n 1 ./ingress.ip):8090
else
  export K8S_INGRESS=127.0.0.1:8090
fi

export TuGraph_QUERIES_DIR=`pwd`/queries
export TuGraph_ENDPOINT=${TuGraph_DRIVER_IP}:57401
export TuGraph_PARAMETER=parameters/parameters-sf${SF}
export TuGraph_DATA_DIR=oss://geaflow-ldbc/${OSS_DIR}
export TuGraph_WORK_DIR=/home/admin/ray-pack/tmp/job
export TuGraph_LIB_DIR=`pwd`/lib
export BUCKET=geaflow-ldbc
export ENDPOINT=oss-cn-hangzhou-internal.aliyuncs.com
export JOB_NAME=geaflow1
export JAR=tugraph-ldbc-0.9.jar
export JAR_MD5=5e295db97d966efa6d9f9bdf298fc64f
export BI19_PRECOMPUTE_BATCH_COUNT=53

popd > /dev/null
