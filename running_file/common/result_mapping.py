
result_mapping = {
     1: [{"name": "year", "type": "INT32"}, {"name": "isComment", "type": "BOOL"}, {"name": "lengthCategory", "type": "INT32"}, {"name": "messageCount", "type": "INT32"}, {"name": "averageMessageLength", "type": "FLOAT32"}, {"name": "sumMessageLength", "type": "INT32"}, {"name": "percentageOfMessages", "type": "FLOAT32"}],
     2: [{"name": "tag.name", "type": "STRING"}, {"name": "countWindow1", "type": "INT32"}, {"name": "countWindow2", "type": "INT32"}, {"name": "diff", "type": "INT32"}],
     3: [{"name": "forum.id", "type": "ID"}, {"name": "forum.title", "type": "STRING"}, {"name": "forum.creationDate", "type": "DATETIME"}, {"name": "person.id", "type": "ID"}, {"name": "messageCount", "type": "INT32"}],
     4: [{"name": "person.id", "type": "ID"}, {"name": "person.firstName", "type": "STRING"}, {"name": "person.lastName", "type": "STRING"}, {"name": "person.creationDate", "type": "DATETIME"}, {"name": "messageCount", "type": "INT32"}],
     5: [{"name": "person.id", "type": "ID"}, {"name": "replyCount", "type": "INT32"}, {"name": "likeCount", "type": "INT32"}, {"name": "messageCount", "type": "INT32"}, {"name": "score", "type": "INT32"}],
     6: [{"name": "person1.id", "type": "ID"}, {"name": "authorityScore", "type": "INT32"}],
     7: [{"name": "relatedTag.name", "type": "STRING"}, {"name": "count", "type": "INT32"}],
     8: [{"name": "person.id", "type": "ID"}, {"name": "score", "type": "INT32"}, {"name": "friendsScore", "type": "INT32"}],
     9: [{"name": "person.id", "type": "ID"}, {"name": "person.firstName", "type": "STRING"}, {"name": "person.lastName", "type": "STRING"}, {"name": "threadCount", "type": "INT32"}, {"name": "messageCount", "type": "INT32"}],
    10: [{"name": "expertCandidatePerson.id", "type": "ID"}, {"name": "tag.name", "type": "STRING"}, {"name": "messageCount", "type": "INT32"}],
    11: [{"name": "count", "type": "INT64"}],
    12: [{"name": "messageCount", "type": "INT32"}, {"name": "personCount", "type": "INT32"}],
    13: [{"name": "zombie.id", "type": "ID"}, {"name": "zombieLikeCount", "type": "INT32"}, {"name": "totalLikeCount", "type": "INT32"}, {"name": "zombieScore", "type": "FLOAT32"}],
    14: [{"name": "person1.id", "type": "ID"}, {"name": "person2.id", "type": "ID"}, {"name": "city1.name", "type": "STRING"}, {"name": "score", "type": "INT32"}],
    15: [{"name": "weight", "type": "FLOAT32"}],
    16: [{"name": "person.id", "type": "ID"}, {"name": "messageCountA", "type": "INT32"}, {"name": "messageCountB", "type": "INT32"}],
    17: [{"name": "person1.id", "type": "ID"}, {"name": "messageCount", "type": "INT32"}],
    18: [{"name": "person1.id", "type": "ID"}, {"name": "person2.id", "type": "ID"}, {"name": "mutualFriendCount", "type": "INT32"}],
    19: [{"name": "person1.id", "type": "ID"}, {"name": "person2.id", "type": "ID"}, {"name": "totalWeight", "type": "FLOAT32"}],
    20: [{"name": "person1.id", "type": "ID"}, {"name": "totalWeight", "type": "INT64"}],
}

