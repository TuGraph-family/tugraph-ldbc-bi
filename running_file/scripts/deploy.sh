#!/usr/bin/env bash

set -eu
set -o pipefail

. scripts/vars.sh

echo
echo "Deploy the cluster..."

cd deploy_cluster
python3 deploy_cluster.py
 
cd -
echo
echo "Cluster deploy success"
