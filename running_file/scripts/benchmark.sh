#!/usr/bin/env bash

set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

. scripts/vars.sh

if [ ! -d ${TuGraph_QUERIES_DIR} ]; then
  echo "TuGraph queries directory does not exist!"
  exit 1
fi

if [ ! -d "${TuGraph_PARAMETER}" ]; then
    echo "Parameter directory ${TuGraph_PARAMETER} does not exist."
    exit 1
fi

python3 -u benchmark.py --scale_factor ${SF} \
                        --para ${TuGraph_PARAMETER} \
                        --query_dir ${TuGraph_QUERIES_DIR} \
                        --work_dir ${TuGraph_WORK_DIR} \
                        --bi19_precompute_batch_count ${BI19_PRECOMPUTE_BATCH_COUNT} \
                        --data_dir ${TuGraph_DATA_DIR} $@