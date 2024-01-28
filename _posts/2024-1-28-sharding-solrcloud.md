---
layout: post
title: Sharding & SolrCloud
categories: [Miscellaneous]
---

Deep dive into concepts of Sharding, Distributed Index management & SolrCloud

## Sharding

Sharding is a method of splitting and storing a single logical dataset in multiple machines.

   - This becomes necessary if a dataset is too large to be stored in a single database.
   - A single machine can store and process data upto a limit. Sharding overcomes this limitation by splitting data into smaller chunks, called shards, and storing them across several machines.

#### Partitioning vs Sharding

Partitioning is the process of splitting a database table into multiple groups. Partitioning is classified into two types: 

   - Vertical partitioning creates different partitions of the database columns.
      - Effective when queries usually return only a subset of columns of the data.
      - Domain specific. We draw a logical split within the application data, storing them in different databases.
      - Always implemented at the application level.

   - Horizontal partitioning splits the database by rows.
      - Effective when queries return a subset of rows that are often grouped together.
      - Splits a homogeneous type of data into multiple databases. That’s why sharding can be implemented at either the application or database level.
      - Sharding is a first-class concept for DBs & they are natively sharded like HDFS, MongoDB, HBase etc.
      - Helps in parallel processing. Speeds up query resolution, since each machine has to scan fewer rows when responding to a query.

Sharding is a form of Horizontal partitioning, but involves multiple machines.
For eg: segments of solr index is a kind of horizontal partitoning, but shards is a different concept altogether.

#### Benefits of Sharding

- _Improves response time_
Data retrieval takes longer on a single large database. System needs to search through many rows to retrieve the correct data. Since, shard is a logical subset of this entire database, it takes less time to retrieve specific information from a sharded database. 

- _Avoid total service outage_
If the machine/database fails, the application depending on the database fails too. Database sharding prevents this by distributing parts of the database into different machines. Failure of one of the computers does not shut down the application because it can operate with other functional shards. Sharding is also often done in combination with data replication across shards. So, if one shard becomes unavailable, the data can be accessed and restored from an alternate shard.

- _Scale efficiently_
Once a database is horizontally sharded, we can scale it further by going more granular or using varying sharding schemes.

### Challenges of Sharding

Sharding adds additional programming and operational complexity to the application codebase, this this is kind of last resort that is taken up when no other scaling methods works.
Managing multiple servers having different logical partions of data adds operational challenges. 

> Before we begin, see whether sharding can be avoided or deferred.

1. Getting more expensive/bigger machine. Storage capacity is growing at the speed of Moore’s law. 
2. Vertically partition by functionality. Binary blobs tend to occupy large amounts of space and are isolated within your application.

Operations may need to search through many databases to find the requested data. These are called **cross-partition operations** and they tend to be inefficient. Sharding schemes attempt to minimize the number of cross-partition operations.
**Hotspots** are another common problem — having uneven distribution of data and operations. Hotspots largely counteract the benefits of sharding.
Once sharding is employed, **redistributing data** is an important problem.


### Types of Sharding

There exist various sharding strategies. Each strategy has pros and cons.

**Shard** or Partition Key is a portion of primary key which determines how data should be distributed. 
A partition key allows you to retrieve and modify data efficiently by routing operations to the correct database. Entries with the same partition key are stored in the same node. 

- A logical shard is a collection of data sharing the same partition key.
- A database node, sometimes referred as a physical shard, contains multiple logical shards.

Suppose, we shard our entire AS index, based upon generationSources & say we divide our cluster into 2 physical shards. Shard 1 will have logical shards of gS:[Organic,Bootstrapped] & Shard 2 will have logical shards of gs: [Generative,LM,GenAI]


## Legacy Distributed Index & Querying (Solr)



## SolrCloud

### How it is better than Legacy
