create graph view ldbc_bi_graph (
  vertex City (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'City')
  ),
  vertex Comment (
    id BIGINT,
    browserUsed VARCHAR,
    creationDate BIGINT,
    locationIP VARCHAR,
    content VARCHAR,
    length INTEGER,
    identify id(id, 'Comment')
  ),
  vertex Company (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Company')
  ),
  vertex Continent (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Continent')
  ),
  vertex Country (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Country')
  ),
  vertex Forum (
    id BIGINT,
    title VARCHAR,
    creationDate BIGINT,
    memberCount INTEGER,
    identify id(id, 'Forum')
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
    identify id(id, 'Person')
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
    identify id(id, 'Post')
  ),
  vertex Tag (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'Tag')
  ),
  vertex TagClass (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'TagClass')
  )
  vertex University (
    id BIGINT,
    name VARCHAR,
    url VARCHAR,
    identify id(id, 'University')
  ),
  edge containerOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'containerOf')
  ),
  edge hasCreator (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasCreator')
  ),
  edge hasInterest (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasInterest')
  ),
  edge hasMember (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'hasMember')
  ),
  edge hasModerator (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasModerator')
  ),
  edge hasTag (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasTag')
  ),
  edge hasType (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'hasType')
  ),
  edge isLocatedIn (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'isLocatedIn')
  ),
  edge isPartOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'isPartOf')
  ),
  edge isSubClassOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'isSubClassOf')
  ),
  edge knows (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'knows')
  ),
  edge likes (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'likes')
  ),
  edge replyOf (
    srcId BIGINT,
    tarId BIGINT,
    edge prop(srcId, tarId, 0, 'replyOf')
  ),
  edge studyAt (
    srcId BIGINT,
    tarId BIGINT,
    classYear INTEGER,
    edge prop(srcId, tarId, 0, 'studyAt')
  ),
  edge workAt (
    srcId BIGINT,
    tarId BIGINT,
    workFrom INTEGER,
    edge prop(srcId, tarId, 0, 'workAt')
  ),
  edge rootPost (
    srcId BIGINT,
    tarId BIGINT,
    creationDate BIGINT,
    edge prop(srcId, tarId, creationDate, 'rootPost')
  ),
  edge weight19 (
    srcId BIGINT,
    tarId BIGINT,
    weight INTEGER,
    edge prop(srcId, tarId, weight, 'weight19')
  ),
  edge weight20 (
    srcId BIGINT,
    tarId BIGINT,
    weight INTEGER,
    edge prop(srcId, tarId, weight, 'weight20')
  )
)