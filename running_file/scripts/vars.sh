pushd . > /dev/null

cd "$( cd "$( dirname "${BASH_SOURCE[0]:-${(%):-%x}}" )" >/dev/null 2>&1 && pwd )"
cd ..

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
export PARTITION=7
# jvm heap size  for each worker process.
export WORKER_JVM_GB=40


if [ -e "driver.ip" ];then
  export TuGraph_DRIVER_IP=$(head -n 1 ./driver.ip)
else
  export TuGraph_DRIVER_IP=127.0.0.1
fi
export TuGraph_QUERIES_DIR=`pwd`/queries
export TuGraph_ENDPOINT=${TuGraph_DRIVER_IP}:57401
export TuGraph_PARAMETER=parameters/parameters-sf${SF}
export TuGraph_DATA_DIR=oss://geaflow-ldbc/${OSS_DIR}
export TuGraph_WORK_DIR=/home/admin/ray-pack/tmp/job
export K8S_INGRESS=raycluster-sample.ray.tugraph.com
export TuGraph_LIB_DIR=`pwd`/lib
export BUCKET=geaflow-ldbc
export ENDPOINT=oss-cn-hangzhou-internal.aliyuncs.com
export JOB_NAME=geaflow1

popd > /dev/null
