#!/usr/bin/python3
import json
import re
import time
import os
from datetime import date, timedelta

from common.result_mapping import result_mapping
from common.util import *
from driver import driver

DRIVER = driver.instance(os.getenv('TuGraph_LIB_DIR'), os.getenv('TuGraph_ENDPOINT'))


def convert_value_to_string(value, result_type):
    if result_type == "ID":
        return int(value)
    elif result_type == "INT" or result_type == "INT32" or result_type == "INT64":
        return int(value)
    elif result_type == "FLOAT" or result_type == "FLOAT32" or result_type == "FLOAT64":
        return float(value)
    elif result_type == "STRING[]":
        return value
    elif result_type == "STRING":
        return value
    elif result_type in ["DATETIME", "DATE"]:
        return unixTimeStampMillis_to_dateTime(value)
    elif result_type == "BOOL":
        return bool(value)
    else:
        raise ValueError(f"Result type {result_type} not found")


def cast_parameter_to_driver_input(value, type):
    if type == "ID[]" or type == "INT[]" or type == "INT32[]" or type == "INT64[]":
        return [int(x) for x in value.split(";")]
    elif type == "ID" or type == "INT" or type == "INT32" or type == "INT64":
        return int(value)
    elif type == "STRING[]":
        return value.split(";")
    elif type == "DATETIME":
        return dateTime_to_unixTimeStampMillis(value)
    elif type == "DATE":
        return datestr_to_unixTimeStampMillis(value)
    elif type in ["STRING"]:
        return value
    else:
        raise ValueError(f"Parameter type {type} not found")


def run_query(query, query_num, parameters):
    start = time.time()
    result_str = DRIVER.query(query, parameters)
    duration = time.time() - start
    # convert results from str to [dict]
    response = json.loads(result_str)
    if 'ERROR' in response:
        raise Exception(f"Error happen in query:\n\n{query_num}\n\nStop now, please check!")
    if query_num is None or query_num == -1:
        return f"""[{{"query": {query}}}]""", duration
    elif query_num == 11 and len(response['node']) == 0:
        return f"""[{{"count": 0}}]""", duration
    results = []
    for item in response['node']:
        results.append(item['properties'])
    # convert results to string
    mapping = result_mapping[query_num]
    result_tuples = []
    for result in results:
        result_tmp = {}
        for result_descriptor in mapping:
            key = result_descriptor["name"]
            value = None
            if key in result:
                value = result[key]
            result_tmp[key] = None if value == None else convert_value_to_string(value, result_descriptor[ "type"])
        result_tuples.append(result_tmp)
    return json.dumps(result_tuples), duration


def run_queries(query_variants, parameter_csvs, sf, results_file, timings_file, batch_date,
                batch_type, args):
    start = time.time()
    for query_variant in query_variants:
        query_num = int(re.sub("[^0-9]", "", query_variant))
        query_subvariant = re.sub("[^ab]", "", query_variant)

        print(f"========================= Q {query_num:02d}{query_subvariant.rjust(1)} =========================")
        query_num = int(re.sub("[^0-9]", "", query_variant))
        parameters_csv = parameter_csvs[query_variant]

        i = 0
        for query_parameters in parameters_csv:
            i = i + 1

            query_parameters_split = {k.split(":")[0]: v for k, v in query_parameters.items()}
            query_parameters_in_order = json.dumps(query_parameters_split)

            query_parameters = {k.split(":")[0]: cast_parameter_to_driver_input(v, k.split(":")[1])
                                for k, v in query_parameters.items()}
            if args.test:
                print(f'Q{query_variant}: {query_parameters}')
            query = read_query(f"{args.query_dir}/bi_query/bi-{query_num}.gremlin")

            results, duration = run_query(query, query_num, query_parameters)

            results_file.write(
                f"{query_num}|{query_variant}|{query_parameters_in_order}|{results}\n")
            results_file.flush()
            timings_file.write(
                f"TuGraph|{sf}|{batch_date}|{batch_type}|{query_variant}|{query_parameters_in_order}|{duration:.6f}\n")
            timings_file.flush()

            # - test run: 1 query
            # - regular run: 30 queries
            if args.test:
                print(f"-> {duration:.4f} seconds")
                print(f"-> {results}")
            if args.test or i == args.nruns:
                break

    return time.time() - start


def run_precompute(timings_file, sf, batch_id, batch_type, args):
    t0 = time.time()
    # clean precompute weight: weight19, weight20
    query = read_query(f"{args.query_dir}/dml/del_precompute.gremlin")
    __, duration = run_query(query, None, None)
    print(f'cleanup precompute data:\t\t{duration:.4f} s')

    print(f"==================== Precompute for BI 4, 6, 19, 20 ======================")
    # compute values and print to files
    for q in [4, 6, 20]:
        query = read_query(f"{args.query_dir}/precompute_query/bi{q}_precompute.gremlin")
        parameters = {"dataDir": f"{str(args.work_dir)}"}
        __, duration = run_query(query, None, parameters)
        print(f'precompute_bi{q}:\t\t{duration:.4f} s')
        timings_file.write(f"TuGraph|{sf}|{batch_id}|{batch_type}|q{q}_precomputation||{duration}\n")

    t1 = time.time()
    start = date(2010, 1, 1)
    query = read_query(f"{args.query_dir}/precompute_query/bi19_precompute.gremlin")
    # precompute q19
    nbatch = args.bi19_precompute_batch_count
    for i in range(nbatch):
        end = start + timedelta(days=365 * 3 // nbatch + 1)
        params = {'startDate': date_to_unixTimeStampMillis(start),
                  'endDate': date_to_unixTimeStampMillis(end),
                  'dataDir': str(args.work_dir)}
        __, duration = run_query(query, None, params)
        print(f'precompute_bi19({start},{end}):{duration:.4f} s')
        start = end
    q19precomputation_total_duration = time.time() - t1
    timings_file.write(f"TuGraph|{sf}|{batch_id}|{batch_type}|q19_precomputation||{q19precomputation_total_duration}\n")

    # load the files
    query = read_query(f"{args.query_dir}/dml/load_precompute.gremlin")
    parameters = {"dataDir": f"{str(args.work_dir)}"}
    __, duration = run_query(query, None, parameters)
    print(f'load_precompute:\t\t{duration:.4f} s')
    return time.time() - t0
