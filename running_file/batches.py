#!/usr/bin/python3
import json
import time
import os
from datetime import timedelta

from common.util import date_to_unixTimeStampMillis
from common.util import read_query
from driver import driver

VERTICES = ['Comment', 'Forum', 'Person', 'Post']

DRIVER = driver.instance(os.getenv('TuGraph_LIB_DIR'), os.getenv('TuGraph_ENDPOINT'))


def run_query(query:str, parameters:dict):
    start = time.time()
    result_str = DRIVER.query(query, parameters)
    duration = time.time() - start
    # convert results from str to [dict]
    response = json.loads(result_str)
    if 'ERROR' in response:
        raise Exception(f"Error happen in query\n\n:{query}\n\nStop now, please check!")
    return result_str, duration

def run_statistics_query(query:str):
    start = time.time()
    result_str = DRIVER.query(query, None)
    duration = time.time() - start
    # convert results from str to [dict]
    response = json.loads(result_str)
    if 'ERROR' in response:
        raise Exception(f"Error happen in query:\n\n:{query}\n\nStop now, please check!")
    elif len(response['node']) == 0:
        return 0, duration
    for item in response['node']:
        return item['properties']['count'], duration
    raise Exception(f"Error happen in query:\n\n:{query}\n\nresponse:{response}\n\nStop now, "
                    f"please check!")

def run_batch_update(batch_date, args):
    batch_id = batch_date.strftime('%Y-%m-%d')
    print(f"#################### {batch_id} ####################")
    print("## Inserts")
    t0 = time.time()
    parameters = {"batchDate": batch_id,
                  "dataDir": str(args.data_dir)}
    query = read_query(f"{args.query_dir}/dml/insert_vertex_and_edge.gremlin")
    _, duration = run_query(query, parameters)
    print(f'Batch insert:\t{time.time() - t0:.4f}s')

    print("\n## Maintain root_post ...")
    parameters = {"startDate": date_to_unixTimeStampMillis(batch_date),
                  "endDate": date_to_unixTimeStampMillis(batch_date + timedelta(days=1)),
                  "dataDir": str(args.work_dir)}
    query = read_query(f"{args.query_dir}/precompute_query/root_post_precompute_delta.gremlin")
    __, duration1 = run_query(query, parameters)
    print(f"Precompute delta root_post:\t{duration1:.4f}s")
    parameters = {"dataDir": str(args.work_dir)}
    query = read_query(f"{args.query_dir}/dml/load_precompute_root_post.gremlin")
    __, duration2 = run_query(query, parameters)
    print(f"Load delta root_post:\t{duration2:.4f}s")
    print(f"Precompute delta root_post total time:\t{duration1 + duration2:.4f}s")

    print("## Deletes")
    t1 = time.time()
    for vertex in VERTICES:
        parameters = {"batchDate": batch_id,
                      "dataDir": str(args.data_dir)}
        query = read_query(f"{args.query_dir}/dml/del_{vertex}.gremlin")
        __, duration = run_query(query, parameters)
        print(f"Delete {vertex}:\t{duration:.4f}s")

    parameters = {"batchDate": batch_id,
                  "dataDir": str(args.data_dir)}
    query = read_query(f"{args.query_dir}/dml/del_Edge.gremlin")
    __, duration = run_query(query, parameters)
    print(f"Delete Edge:{duration:.4f}")
    print(f'Batch delete:\t{time.time() - t1:.4f} s')
    return time.time() - t0
