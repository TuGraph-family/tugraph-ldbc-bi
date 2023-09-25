#!/usr/bin/env bash

set -eu
set -o pipefail

cd "$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"
cd ..

. scripts/vars.sh

if [ "$(uname)" == "Darwin" ]; then
    DATE_COMMAND=gdate
else
    DATE_COMMAND=date
fi

start_time=$(${DATE_COMMAND} +%s.%3N)


. scripts/stop.sh
. scripts/start.sh
python3 -u setup.py "${TuGraph_QUERIES_DIR}" "${TuGraph_DATA_DIR}" "${TuGraph_WORK_DIR}"

end_time=$(${DATE_COMMAND} +%s.%3N)

mkdir -p output/output-sf${SF}
elapsed=$(python3 -c "import argparse; parser = argparse.ArgumentParser(); parser.add_argument('--start_time', type=float); parser.add_argument('--end_time', type=float); args = parser.parse_args(); elapsed = args.end_time - args.start_time; print(f'{elapsed:.3f}')" --start_time $start_time --end_time $end_time)
echo -e "time\n${elapsed}" > output/output-sf${SF}/load.csv
