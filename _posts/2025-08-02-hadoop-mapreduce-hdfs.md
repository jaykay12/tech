---
layout: post
title: Apache Hadoop - Map Reduce & HDFS
categories: [Data Engineering, BigData]
---

![hadoop-version](../assets/images/HADOOP-7.png)

# Introduction

A highly scalable, open-source and distributed computing platform developed by Apache for storing, processing, and analysing large amounts of data.
- Big Data technology
- primarily Java-based
- batch/offline processing system

Its core components: 
- HDFS for storage
- MapReduce for processing
- YARN for resource management, and
- Hadoop Common for essential utilities 

continue to shape how data workflows are designed and scaled.

![hadoop-evolution](../assets/images/HADOOP-9.png)

![hadoop-version](../assets/images/HADOOP-5.png)

---

# Hadoop Ecosystem

Hadoop is not a single tool. It’s an ecosystem that includes multiple modules that work together to manage data storage, processing, and resource coordination.

HDFS, MapReduce, Yarn & Hadoop Commons are the 4 primary pillars of Hadoop ecosystem.

![hadoop-components](../assets/images/HADOOP-6.png)

![hadoop-other-interactions](../assets/images/HADOOP-8.png)

## Benefits of Hadoop

Hadoop was designed to deal with large amounts of data, so here are the five primary benefits:

Benefits|Details
---|---
**Speed**|Hadoop's concurrent processing using MapReduce model, and HDFS enables this
**Diversity**|HDFS can hold a variety of data forms, including structured, semi-structured, and unstructured data.
**Cost-Effective**|Open-source and stores data on commodity technology
**Resilient**|HDFS has the ability to replicate data over the network & provides replication factor of 3 by default
**Scalable**|Distributed in nature, we can quickly add extra servers to Hadoop & horizontally scale it

## Challenges in Hadoop

Challenges|Details
---|---
**Security**|Hadoop still has to provide suitable identity, data encryption, provisioning, and auditing procedures.
**High Learning Curve**|For running query in Hadoop, it requires developing MapReduce functions in Java. In addition, the ecosystem is made up of many other components.
**Not All Datasets can be Handled the Same**|Hadoop does not provide a “one-size-fits-all” benefit. Different components operate in different ways, lot trial and error needed
**MapReduce is Limited**|Although MapReduce is a fantastic programming model, it relies on a file-intensive technique which is tough to handle

## Components of Hadoop

|Component|Details
---|---
HDFS|![hdfs-intro](../assets/images/HADOOP-1.png)
MapReduce|![mr-intro](../assets/images/HADOOP-2.png)
Yarn|![yarn-intro](../assets/images/HADOOP-3.png)

## Key Features of Hadoop

These are mainly:
- **Distributed Storage and Processing** allow it to store extremely large datasets and query them.
- **Horizontal scalability** without requiring high-end machines.
- **Data locality optimization**, moving computation to where the data resides.
- **Resilience to failure** via replication and task re-execution.

`These 4 features make Hadoop well-suited for batch data processing, log analysis, and ETL pipelines.`

Hadoop is a classic case study for distributed computing, where computation happens across multiple nodes to increase the overall efficiency and scale.

---

# Hadoop Distributed File System (HDFS)

HDFS is Hadoop’s primary storage system. It is designed to reliably store the vast amounts of data across a cluster of machines.
HDFS architecture is highly optimized for fault tolerance, scalability, and data locality.

## Architecture & Components

HDFS follows a master-slave model. 
- At the top sits the **NameNode**
  - which manages metadata like the file system’s directory tree and information about each file’s location.
  - It doesn’t store the actual data.
  - Serves as the Master, guiding the Datanode.

- The **DataNodes** are the workhorses or slaves.
  - They manage storage attached to the nodes and serve read/write requests from clients.
  - Each DataNode regularly reports back to the NameNode with a heartbeat and block reports to ensure consistent state tracking.

![hdfs-architecture](../assets/images/HDFS-1.png)

## Data Storage & Fault Tolerance

When a file is written to HDFS, it’s broken into fixed-size 64MB blocks. Each block is then distributed across different DataNodes.
Data in HDFS isn’t just stored once. Each block is replicated (the default is three copies) and spread across the cluster.

- During a read operation, the system pulls data from the nearest available replica to maximize throughput and minimize latency.
- Writes go to one replica first and then propagate to the others, ensuring durability without immediate bottlenecks.

HDFS is built with failure in mind. HDFS ensures data availability through its replication mechanism. 
- **DataNode Failure**
  NameNode detects the failure via missed heartbeats and schedules the replication of lost blocks to healthy nodes.

- **NameNode Failures**
  HDFS handles NameNode failure through the High Availability (HA) feature, which provides a redundant pair of NameNodes in an Active/Standby configuration, preventing the NameNode from being a single point of failure (SPOF).

### Important HDFS Commands

Purpose|Command
---|---
Checking a file content | `hadoop fs -text <FILE_PATH> \| head -n 1`
Listing files|`hdfs dfs -ls <FILE_PATH>`
Creating Directory|`hadoop fs -mkdir -p <PATH>`
Checking storage|`hdfs dfs -du -h -s <FILE_PATH>`
Merging files|`hdfs dfs -getmerge <HDFS_FILE_PATH> <LOCAL_FILE_NAME>`
Moving data|`hdfs dfs -mv <HDFS_SOURCE>/* <HDFS_DESTINATION>`
Copying data|`hadoop fs -cp <HDFS_SOURCE>/* <HDFS_DESTINATION>`
Copying data to local|`hdfs dfs -copyToLocal <HDFS_FILE_PATH> <LOCAL_FILE_NAME>`

# MapReduce

MapReduce is the Hadoop’s processing engine. 

It allows for distributed computation across large datasets by breaking down tasks into smaller, independent operations that can be executed in parallel.

## Stages and Components of MapReduce

MapReduce follows a two-phase programming model: the Map and Reduce phases:

- The **Map() function** converts DataBlocks into Tuples, which are just key-value pairs.
- The **Reduce() function** then joins these broken Tuples or key-value pairs based on their Key value and creates a set of Tuples, and performs operations such as sorting, summing, and so on, before sending them to the final Output Node.

![mapreduce-stages](../assets/images/MR-2.png)

![mapreduce-components](../assets/images/MR-4.png)

![mapreduce-stage-components](../assets/images/MR-5.png)

![MR-Job-workflow](../assets/images/MR-7.png)

Cascading is a very popular java framework for writing MR Jobs. In recent times, Spark jobs have replaced MR/Cascading Jobs in production.

![mr-benefits](../assets/images/MR-6.png)

![mr-usage](../assets/images/MR-10.png)

![mr-developer-interaction](../assets/images/MR-13.png)

---

# Yet Another Resource Negotiator (YARN)

YARN serves as Hadoop’s resource management layer. 

It separates job scheduling and resource allocation from the processing model, helping Hadoop support multiple data processing engines beyond MapReduce like Apache Spark, Hive, and Tez.

There are 2 primary tasks for YARN:
- **Job Scheduling**
  - Breaking down a large work into smaller jobs so that each one may be assigned to different slaves in a Hadoop cluster and processing is optimized.
  - It also maintains track of which jobs are more vital, which jobs have higher priority, job dependencies, and other information such as job timing.
- **Resource Management**
  - Managing all of the resources that are made available for a Hadoop cluster to run.

## Components of YARN

![yarn-entities](../assets/images/YARN-11.png)

### Component Details

#### ResourceManager

The master daemon that manages all resources and schedules applications

![r-m](../assets/images/YARN-6.png)

#### NodeManager

per-node agent that monitors resource usage and reports back to the RM

![n-m](../assets/images/YARN-7.png)

#### ApplicationMaster

A job-specific operation that uses resources from the RM and coordinates execution with NMs

![a-m](../assets/images/YARN-8.png)

Job Scheduling is done by RM & Task Monitoring is done by AM.

![workflow-management](../assets/images/YARN-1.png)

![application-processing](../assets/images/YARN-2.png)


## Internals of NameNode

Containers are also called as Executors in normal tech language.

![namenode-container](../assets/images/YARN-9.png)

![container-clc](../assets/images/YARN-10.png)

![yarn-benefits](../assets/images/YARN-5.png)

---

# Hadoop Common

The glue that pulls all Hadoop’s components together is Hadoop Common. It’s a collection of java libraries, configuration files, and utilities required by all the Hadoop cluster components. HDFS, YARN, and MapReduce

- Provides the foundational code and tools that allow HDFS, YARN, and MapReduce to communicate and coordinate.
- This includes Java libraries, file system clients, and APIs used across the ecosystem.
- Ensures consistency and compatibility between modules, allowing developers to build on a shared set of primitives.

- Proper configuration of Hadoop is critical. Contains configuration files like core-site.xml, which define default behaviors such as filesystem URIs and I/O settings.
- Contains CLI tools and scripts for tasks like starting services, checking node health, and managing jobs.

