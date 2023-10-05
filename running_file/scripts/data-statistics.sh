#!/usr/bin/env bash

set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

. scripts/vars.sh

if [ ! -d "${TuGraph_PARAMETER}" ]; then
    echo "Parameter directory ${TuGraph_PARAMETER} does not exist."
    exit 1
fi

python3 -u data_statistics.py ${SF}