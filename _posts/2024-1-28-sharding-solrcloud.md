---
layout: post
title: Sharding & SolrCloud
categories: [Miscellaneous]
---

Deep dive into concepts of Sharding, Distributed Index management & SolrCloud

![fasttext](../assets/images/FT-1.png)

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



### Driving Principles

### Types of Sharding

There exist various strategies to distribute data into multiple databases. Each strategy has pros and cons depending on various assumptions a strategy makes. It is crucial to understand these assumptions and limitations. Operations may need to search through many databases to find the requested data. These are called cross-partition operations and they tend to be inefficient. Hotspots are another common problem — having uneven distribution of data and operations. Hotspots largely counteract the benefits of sharding.

Shard or Partition Key is a portion of primary key which determines how data should be distributed. A partition key allows you to retrieve and modify data efficiently by routing operations to the correct database. Entries with the same partition key are stored in the same node. A logical shard is a collection of data sharing the same partition key. A database node, sometimes referred as a physical shard, contains multiple logical shards.

### Challenges of Sharding

Sharding adds additional programming and operational complexity to your application. 
You lose the convenience of accessing the application’s data in a single location. Managing multiple servers adds operational challenges. 


Before you begin, see whether sharding can be avoided or deferred.

1. Get a more expensive machine. Storage capacity is growing at the speed of Moore’s law. 
2. Vertically partition by functionality. Binary blobs tend to occupy large amounts of space and are isolated within your application.

Cross-partition queries are inefficient, and many sharding schemes attempt to minimize the number of cross-partition operations. On the other hand, partitions need to be granular enough to evenly distribute the load amongst nodes. Finding the right balance can be tricky.



## Legacy Distributed Index & Querying (Solr)



## SolrCloud

### How it is better than Legacy
