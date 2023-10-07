#!/usr/bin/env bash

set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

. scripts/vars.sh

echo
echo "Stopping the services..."

python3 job_client.py stop

echo
echo "TuGraph stop success"
