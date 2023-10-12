#!/usr/bin/python3
import sys
import time
from pathlib import Path

from batches import *

sf = sys.argv[1]
output = Path(f'output/output-sf{sf}')
output.mkdir(parents=True, exist_ok=True)

print("===============================================================================")
print("Graph statistics")
print("-------------------------------------------------------------------------------")
statistics_file = open(output/'statistics.csv', 'w')
statistics_file.write(f"tool|sf|type|cardinality\n")
total_nodes = 0
total_edges = 0
vertex_type='Organisation'
count, duration = run_statistics_query(f"g.V().hasLabel('University','Company').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Organisation_isLocatedIn_Place'
count, duration = run_statistics_query(f"g.V().hasLabel('University','Company').outE("
                                       f"'isLocatedIn').count().as('count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='Place'
count, duration = run_statistics_query(f"g.V().hasLabel('Continent','Country','City').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Place_isPartOf_Place'
count, duration = run_statistics_query(f"g.V().hasLabel('City','Country').outE("
                                       f"'isPartOf').count().as('count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='Tag'
count, duration = run_statistics_query(f"g.V().hasLabel('{vertex_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Tag_hasType_TagClass'
count, duration = run_statistics_query(f"g.V().hasLabel('Tag').outE('hasType').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='TagClass'
count, duration = run_statistics_query(f"g.V().hasLabel('{vertex_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'isSubclassOf'
count, duration = run_statistics_query(f"g.V().hasLabel('TagClass').outE('{edge_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='Comment'
count, duration = run_statistics_query(f"g.V().hasLabel('{vertex_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Comment_hasCreator_Person'
count, duration = run_statistics_query(f"g.V().hasLabel('Comment').outE('hasCreator').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Comment_hasTag_Tag'
count, duration = run_statistics_query(f"g.V().hasLabel('Comment').outE('hasTag').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Comment_isLocatedIn_Country'
count, duration = run_statistics_query(f"g.V().hasLabel('Comment').outE("
                                       f"'isLocatedIn').count().as('count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Comment_replyOf_Comment'
count, duration = run_statistics_query(f"g.V().hasLabel('Comment').inE('replyOf').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Comment_replyOf_Post'
count, duration = run_statistics_query(f"g.V().hasLabel('Post').inE('replyOf').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='Forum'
count, duration = run_statistics_query(f"g.V().hasLabel('{vertex_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Forum_containerOf_Post'
count, duration = run_statistics_query(f"g.V().hasLabel('Forum').outE('containerOf').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Forum_hasMember_Person'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').inE('hasMember').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Forum_hasModerator_Person'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').inE('hasModerator').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Forum_hasTag_Tag'
count, duration = run_statistics_query(f"g.V().hasLabel('Forum').outE('hasTag').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='Person'
count, duration = run_statistics_query(f"g.V().hasLabel('{vertex_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Person_hasInterest_Tag'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('hasInterest').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Person_isLocatedIn_Country'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE("
                                       f"'isLocatedIn').count().as('count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Person_knows_Person'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('knows').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Person_likes_Comment'
count, duration = run_statistics_query(f"g.V().hasLabel('Comment').inE('likes').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Person_likes_Post'
count, duration = run_statistics_query(f"g.V().hasLabel('Post').inE('likes').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Person_studyAt_University'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('studyAt').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Person_workAt_Company'
count, duration = run_statistics_query(f"g.V().hasLabel('Person').outE('workAt').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

vertex_type='Post'
count, duration = run_statistics_query(f"g.V().hasLabel('{vertex_type}').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{vertex_type}|{count}\n")
print(f"{vertex_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_nodes += count

edge_type = 'Post_hasCreator_Person'
count, duration = run_statistics_query(f"g.V().hasLabel('Post').outE('hasCreator').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Post_hasTag_Tag'
count, duration = run_statistics_query(f"g.V().hasLabel('Post').outE('hasTag').count().as("
                                       f"'count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

edge_type = 'Post_isLocatedIn_Country'
count, duration = run_statistics_query(f"g.V().hasLabel('Post').outE("
                                       f"'isLocatedIn').count().as('count')")
statistics_file.write(f"TuGraph|{sf}|{edge_type}|{count}\n")
print(f"{edge_type} count:\t{count}\tduration:\t{duration:.4f} s")
total_edges += count

statistics_file.write("\n\n\n\n")
statistics_file.write(f"Total nodes count: {total_nodes}")
statistics_file.write(f"\nTotal edges count: {total_edges}")
statistics_file.close()


print("\n")
print("===============================================================================")
print(f"TuGraph data statistics finish")
print(f"Total nodes count: {total_nodes}")
print(f"Total edges count: {total_edges}")
print("-------------------------------------------------------------------------------")
print("\n")
print("\n")