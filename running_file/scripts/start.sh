#!/usr/bin/env bash

set -eu
set -o pipefail

cd "$(cd "$(dirname "${BASH_SOURCE[0]}")" >/dev/null 2>&1 && pwd)"
cd ..


./deploy_cluster/kubectl --kubeconfig ./deploy_cluster/kube.yaml -n geaflow get pod \
raycluster-sample-8c64g-worker-0 -o jsonpath='{.status.podIP}' > ./driver.ip

. scripts/vars.sh

echo "==============================================================================="
echo "Loading the TuGraph"
echo "-------------------------------------------------------------------------------"
echo "SF: ${SF}"
echo "TuGraph_DATA_DIR (on the host machine): ${TuGraph_DATA_DIR}"
echo "TuGraph_QUERIES_DIR: ${TuGraph_QUERIES_DIR}"
echo "==============================================================================="


if [ ! -d ${TuGraph_QUERIES_DIR} ]; then
  echo "TuGraph queries directory does not exist!"
  exit 1
fi

echo -n "Starting the services."

python3 job_client.py start

echo
echo "All done."
