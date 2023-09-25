#!/usr/bin/env bash

set -eu
set -o pipefail

. scripts/vars.sh

cd deploy_cluster
python3 deploy_cluster.py

cd -