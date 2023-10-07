#!/usr/bin/env bash

set -eu
set -o pipefail

echo
echo "Delete the cluster..."

rm -f job_id
cd deploy_cluster

./kubectl  --kubeconfig kube.yaml -n geaflow delete -f ./ingress
./kubectl  --kubeconfig kube.yaml -n geaflow delete -f ./deploy
./kubectl  --kubeconfig kube.yaml -n geaflow delete -f ./200-sa.yaml
./kubectl  --kubeconfig kube.yaml -n geaflow delete -f ./crds

cd -
echo
echo "Cluster delete success"