---
layout: post
title: Sharding & SolrCloud
categories: [Miscellaneous]
---

Deep dive into concepts of Sharding, Distributed Index management & SolrCloud

![fasttext](../assets/images/FT-1.png)

## Sharding

Sharding is a method of splitting and storing a single logical dataset in multiple databases.

   - This is necessary if a dataset is too large to be stored in a single database.

#### Partitioning vs Sharding

Partitioning is the process of splitting a database table into multiple groups. Partitioning is classified into two types: 

   - Vertical partitioning creates different partitions of the database columns.
   - Horizontal partitioning splits the database by rows.

Sharding is a kind of Horizontal partitioning, but involves multiple machines.
For eg: segments of solr index is a kind of horizontal partitoning, but shards is a different concept altogether.

### Driving Principles

### Types of Sharding

### Challenges of Sharding

### EndNotes


## Legacy Distributed Index & Querying (Solr)



## SolrCloud

### How it is better than Legacy
