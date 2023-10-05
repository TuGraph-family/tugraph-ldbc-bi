#!/usr/bin/python3
import sys
import time
from pathlib import Path

from batches import *

sf = sys.argv[1]
output = Path(f'output/output-sf{sf}')
output.mkdir(parents=True, exist_ok=True)

VERTICES = ['Comment', 'Post', 'Forum', 'Person', 'Company', 'University', 'City', 'Country',
            'Continent', 'Tag', 'TagClass']
print("===============================================================================")
print("Vertex statistics")
print("-------------------------------------------------------------------------------")
vertex_statistics_file = open(output/'vertex_statistics.csv', 'w')
vertex_statistics_file.write(f"tool|sf|vertex_type|cardinality\n")
t0 = time.time()
for vertex in VERTICES:
    count, duration = run_statistics_query(f"g.V().hasLabel('{vertex}').count().as('count')")
    vertex_statistics_file.write(f"TuGraph|{sf}|{vertex}|{count}\n")
    print(f"{vertex} count:\t{count}\tduration:\t{duration:.4f} s")
vertex_statistics_file.close()


print("===============================================================================")
print("Edge statistics")
print("-------------------------------------------------------------------------------")
edge_statistics_file = open(output/'edge_statistics.csv', 'w')
edge_statistics_file.write(f"tool|sf|edge_type|cardinality\n")
t1 = time.time()
edge_type = 'containerOf'
count, duration = run_statistics_query(f"g.V().hasLabel('Forum').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'hasCreator'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').inE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'hasInterest'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'hasMember'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').inE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'hasModerator'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').inE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'hasTag'
count, duration = run_statistics_query(f"g.V().hasLabel('Tag').inE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'hasType'
count, duration = run_statistics_query(f"g.V().hasLabel('Tag').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'isLocatedIn'
count, duration = run_statistics_query(f"g.V().hasLabel('City','Country').inE("
                                       f"'{edge_type}').count().as('count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'isPartOf'
count, duration = run_statistics_query(f"g.V().hasLabel('City','Country').outE("
                                       f"'{edge_type}').count().as('count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'isSubClassOf'
count, duration = run_statistics_query(f"g.V().hasLabel('TagClass').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'knows'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'likes'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'replyOf'
count, duration = run_statistics_query(f"g.V().hasLabel('Comment').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'studyAt'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

edge_type = 'workAt'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('{edge_type}').count().as("
                                       f"'count')")
edge_statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")

print("\n")
print("===============================================================================")
print(f"TuGraph data statistics is finish, totalDuration:{(time.time() - t0):.4f} s")
print("-------------------------------------------------------------------------------")
print("\n")
print("\n")