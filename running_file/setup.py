#!/usr/bin/python3
import sys
import time

from batches import run_query
from common.util import read_query

queries_dir = sys.argv[1]
data_path = sys.argv[2]
work_path = sys.argv[3]

print("===============================================================================")
print("Schema setup")
print("-------------------------------------------------------------------------------")
t0 = time.time()
query = read_query(f"{queries_dir}/ddl/schema.ddl")
#run_query(query, {})


print("===============================================================================")
print("Load Data")
print("-------------------------------------------------------------------------------")
t1 = time.time()
query = read_query(f"{queries_dir}/ddl/load_all.gremlin")
parameters = {"dataDir": f"{data_path}"}
run_query(query, parameters)

print("===============================================================================")
print("Precompute ROOT_POST")
print("-------------------------------------------------------------------------------")
t2 = time.time()
parameters = {"dataDir": f"{work_path}"}
query = read_query(f"{queries_dir}/precompute_query/root_post_precompute_all.gremlin")
run_query(query, parameters)

print("===============================================================================")
print("Load ROOT_POST")
print("-------------------------------------------------------------------------------")
t3 = time.time()
query = read_query(f"{queries_dir}/dml/load_precompute_root_post.gremlin")
parameters = {"dataDir": f"{work_path}"}
run_query(query, parameters)
t4 = time.time()

print("\n")
print("===============================================================================")
print("TuGraph data is ready for benchmark")
print(f"Schema setup:        {round(t1-t0,2)} s")
print(f"Load Data:           {round(t2-t1,2)} s")
print(f"Precompute ROOT_POST:{round(t3-t2,2)} s")
print(f"Load ROOT_POST:      {round(t4-t3,2)} s")
print("-------------------------------------------------------------------------------")
print("\n")



