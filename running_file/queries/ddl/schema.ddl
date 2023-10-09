create graph view ldbc_bi_graph (
  vertex City (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'City', 'LOCAL')
  ),
  vertex Comment (
    id BIGINT,
    browserUsed VARCHAR,
    creationDate BIGINT,
    locationIP VARCHAR,
    content VARCHAR,
    length INTEGER,
    identify id(id, 'Comment', 'DISTRIBUTE')
  ),
  vertex Company (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Company', 'LOCAL')
  ),
  vertex Continent (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Continent', 'LOCAL')
  ),
  vertex Country (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Country', 'LOCAL')
  ),
  vertex Forum (
    id BIGINT,
    title VARCHAR,
    creationDate BIGINT,
    memberCount INTEGER,
    identify id(id, 'Forum', 'DISTRIBUTE')
  ),
  vertex Person (
    id BIGINT,
    firstName VARCHAR,
    lastName VARCHAR,
    gender VARCHAR,
    birthday VARCHAR,
    locationIP VARCHAR,
    browserUsed VARCHAR,
    language VARCHAR,
    email VARCHAR
    creationDate BIGINT,
    popularityScore INTEGER,
    identify id(id, 'Person', 'DISTRIBUTE')
  ),
  vertex Post (
    id BIGINT,
    browserUsed VARCHAR,
    creationDate BIGINT,
    locationIP VARCHAR,
    content VARCHAR,
    length INTEGER,
    language VARCHAR,
    imageFile VARCHAR,
    identify id(id, 'Post', 'DISTRIBUTE')
  ),
  vertex Tag (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Tag', 'LOCAL')
  ),
  vertex TagClass (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'TagClass', 'LOCAL')
  )
  vertex University (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'University', 'LOCAL')
  ),
  edge containerOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'containerOf', 'BOTH', 'DISTRIBUTE')
  ),
  edge hasCreator (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasCreator', 'BOTH', 'DISTRIBUTE')
  ),
  edge hasInterest (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasInterest', 'BOTH', 'DISTRIBUTE')
  ),
  edge hasMember (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'hasMember', 'BOTH', 'DISTRIBUTE')
  ),
  edge hasModerator (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasModerator', 'BOTH', 'DISTRIBUTE')
  ),
  edge hasTag (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasTag', 'BOTH', 'DISTRIBUTE')
  ),
  edge hasType (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasType', 'BOTH', 'LOCAL')
  ),
  edge isLocatedIn (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'isLocatedIn', 'BOTH', 'DISTRIBUTE')
  ),
  edge isPartOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'isPartOf', 'BOTH', 'LOCAL')
  ),
  edge isSubClassOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'isSubClassOf', 'BOTH', 'LOCAL')
  ),
  edge knows (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'knows', 'BOTH', 'DISTRIBUTE')
  ),
  edge likes (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'likes', 'BOTH', 'DISTRIBUTE')
  ),
  edge replyOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'replyOf', 'BOTH', 'DISTRIBUTE')
  ),
  edge studyAt (
    srcId BIGINT,
    tarId BIGINT,
    classYear INTEGER,
    edge prop(srcId, tarId, 0, 'studyAt', 'BOTH', 'DISTRIBUTE')
  ),
  edge workAt (
    srcId BIGINT,
    tarId BIGINT,
    workFrom INTEGER,
    edge prop(srcId, tarId, 0, 'workAt', 'BOTH', 'DISTRIBUTE')
  ),
  edge rootPost (
    srcId BIGINT,
    tarId BIGINT,
    commentCreationDate BIGINT,
    edge prop(srcId, tarId, commentCreationDate, 'rootPost', 'BOTH', 'DISTRIBUTE')
  ),
  edge weight19 (
    srcId BIGINT,
    tarId BIGINT,
    weight INTEGER,
    edge prop(srcId, tarId, weight, 'weight19', 'BOTH', 'DISTRIBUTE')
  ),
  edge weight20 (
    srcId BIGINT,
    tarId BIGINT,
    weight INTEGER,
    edge prop(srcId, tarId, weight, 'weight20', 'BOTH', 'DISTRIBUTE')
  )
)