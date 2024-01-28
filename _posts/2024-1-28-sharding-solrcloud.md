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

Sharding operates on a shared-nothing architecture. Each physical shard operates independently and is unaware of other shards. Now, since Sharding as a concept can be intrinsic or application-driven.
In former, only the physical shards that contain the data that you request will process the data in parallel for you. 
For latter, the application layer coordinates data storage and access from these multiple shards.

#### Key-based or Algorthmic Sharding

the data is plugged into a hash function to determine which shard each data value must go to.

A hash function takes a piece of input data and generates a discrete output value known as the hash value. In key-based sharding, the hash value is the shard ID, which determines in which shard the data is stored. The values entered into the hash function all come from the same column, known as the shard key, to ensure that entries are placed consistently and with the appropriate accompanying data in the correct shards.

This key is static -- i.e., its values don't change over time. If they do, it could slow down performance.

Advantages of key-based sharding

suitable for distributing data evenly to prevent hotspots; and
no need to maintain a data map, since data is distributed algorithmically.
Disadvantages of key-based sharding

difficult to dynamically add or remove additional servers to the database;
during data rebalancing, both new and old hashing functions could become invalid; and
during migration, servers cannot write any new data, which may lead to application downtime.

#### Range-based Sharding

range-based sharding involves sharding data according to the ranges of a given value. The range is based on a field, which is known as the shard key.

Advantages of range-based sharding

straightforward implementation; and
simple algorithm since different shards have identical schema to each other, as well as the original database.
Disadvantages of range-based sharding

may create database hotspots, since data could be unevenly distributed; and
a poor choice of shard key could create unbalanced shards and adversely impact performance.


#### Directory Sharding
In directory-based sharding, a lookup table is created and maintained. It uses a shard key to track which shard holds what kind of data.

Advantages of directory-based sharding

provides greater flexibility in terms of dynamically assigning data to shards;
superior to key-based sharding because it doesn't require a hash function; and
superior to range-based sharding since it ties each key to its own specific shard.
Disadvantages of directory-based sharding

can have a negative impact on application performance because it requires connection to the lookup table before every query or write; and
corruption or failure of the lookup table could lead to data loss or accessibility issues.


## Legacy Distributed Index & Querying (Solr)



## SolrCloud

### How it is better than Legacy
