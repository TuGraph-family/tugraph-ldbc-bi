#!/usr/bin/env bash

set -eu
set -o pipefail

. scripts/vars.sh

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

./deploy_cluster/kubectl --kubeconfig ./deploy_cluster/kube.yaml -n geaflow get pod \
raycluster-sample-default-head-0 -o jsonpath='{.status.podIP}' > ./ingress.ip

mkdir -p output/output-sf${SF}
. scripts/load-in-one-step.sh | tee output/output-sf${SF}/load.log
. scripts/benchmark.sh $@ | tee output/output-sf${SF}/benchmark.log