---
layout: post
title: Basics of Apache Spark
categories: [Data Engineering]
---

> Unified analytics engine for large-scale real-time & batch data processing.

![spark](../assets/images/SPARK-1.png)

Main strengths of Apache Spark:
- **Speed**:
  - Run workloads 100x faster as compared to Hadoop.
  - Achieves high performance for both batch and streaming data.
- **Ease of Use**:
  - Write applications quickly in Java, Scala, Python, R, and SQL.
  - Can be used interactively from the Scala, Python, R, and SQL shells too.
- **Generality**:
  - Combine SQL, streaming, and complex analytics together.
  - It powers a stack of libraries including SQL and DataFrames, MLlib for machine learning, GraphX, and Spark Streaming.
- **Runs Everywhere**:
  - Spark runs on Hadoop, Apache Mesos, Kubernetes, standalone, or in the cloud.
  - It can access diverse data sources like HDFS, Alluxio, Apache Cassandra, Apache HBase, Apache Hive etc.


# Introduction to Apache Spark

Apache Spark is an Open-Source, distributed processing system suitable for big data workloads. It utilizes in-memory caching, and optimized query execution for fast analytic queries of any size.

This data processing framework can quickly perform processing tasks on very large data sets, and can also distribute tasks across multiple computers, either on its own or in tandem with other distributed computing tools.

Apache Spark started off in 2009, at the University of California, Berkley Lab. In 2013, it became one of the top projects under Apache Foundation.

The goal of Spark was to create a new framework, which is optimized for real-time data processing and interactive data analysis, while retaining the scalability, and fault tolerance of Hadoop MapReduce.

![spark-history](../assets/images/SPARK-2.png)

### Features of Apache Spark
![spark-features](../assets/images/SPARK-4.png)

### Need for Apache Spark over Hadoop

**Hadoop MapReduce**
- Programming model for processing big data sets (batch processes) with a parallel, distributed algorithm.
- Entire job is a sequential multi-step process which poses as a challenge for Hadooop MapReduce.
- With each step, MapReduce reads data from the cluster, performs operations, and writes the results back to HDFS.
- Because each step requires a disk read, and write, jobs are slower due to the disk I/O latency.

**Apache Spark**
- Was created to address the limitations of MapReduce
    - by doing in-memory processing
    - reducing the number of steps in a job
    - by reusing data across multiple parallel operations.
- With Spark, only one-step is needed where data is read into memory, operations are performed, and the results are written back — resulting in a much faster execution.
- Spark also reuses data by using an in-memory cache to greatly speed up machine learning algorithms that repeatedly call a function on the same dataset.
- Data re-use is accomplished through Resilient Distributed Dataset (RDD). This lowers the overall latency making Spark 100X times faster than MapReduce, especially when doing machine learning, and interactive analytics.

![spark-hadoop-1](../assets/images/SPARK-3.png)

In Hadoop, it was not possible to process data in real-time. There is a time-lag associated after which batches of data can be processed. Apache Spark extended this batch-processing capability to real-time stream processing.

![hadoop](../assets/images/SPARK-15.png)
![spark](../assets/images/SPARK-16.png)

<ins>Apache Hadoop:</ins> An open source framework that has
- HDFS as storage
- YARN as a way of managing computing resources used by different applications
- MapReduce programming model's implemented as an execution engine.

<ins>Apache Spark:</ins> An open source framework
- Focused on interactive query, machine learning, and real-time workloads.
- Does not have its own storage system
- Runs analytics on other storage systems like HDFS, Amazon Redshift, Amazon S3, Couchbase, Cassandra, etc.
- Spark on Hadoop leverages YARN to share a common cluster and dataset.

![spark-hadoop-2](../assets/images/SPARK-17.png)

## Components of Apache Spark

The Spark framework includes:
- Spark Core as the foundation for the platform.
- Spark SQL for interactive queries.
- Spark Streaming for real-time analytics in orgs like Amazon, Flipkart etc.
- Spark MLlib for machine learning.
- Spark GraphX for graph processing in orgs like LinkedIn, Facebook etc.

![spark-components](../assets/images/SPARK-5.png)

#### Spark Core:

Spark Core is exposed through an application programming interface (APIs) built for Java, Scala, Python and R. These APIs hide the complexity of distributed processing behind simple, high-level operators.

![spark-core](../assets/images/SPARK-6.png)

#### Spark SQL:

- Distributed query engine that provides low-latency, interactive queries up to 100x faster than MapReduce.
- Business analysts can use standard SQL or the Hive Query Language for querying data.
- Developers can use APIs, available in Scala, Java, Python, and R.
- Supports various data sources including JDBC, ODBC, JSON, HDFS, Hive, ORC, and Parquet. Popular stores — Amazon Redshift, Amazon S3, Couchbase, Cassandra, MongoDB, Elasticsearch, Solr etc are also supported.

![spark-sql](../assets/images/SPARK-8.png)

#### Spark MLlib:

- Library of algorithms to do machine learning on data at scale.
- ML models can be trained by data scientists with R or Python on any Hadoop data source, saved using MLlib, and imported into a Java or Scala-based pipeline.
- Spark was designed for fast, interactive computation that runs in memory, enabling fast model training.
- The algorithms inside library include the ability to do classification, regression, clustering, collaborative filtering, and pattern mining.

![spark-ml](../assets/images/SPARK-9.png)

#### Spark Streaming:

- Real-time solution that makes use of Spark Core’s fast scheduling capability to do analytics on streaming data.
- It ingests data in mini-batches, and enables analytics on that data with the same application code written for batch analytics. This improves developer productivity, because they can use the same code for batch processing, and for real-time streaming applications.
- Spark Streaming supports data from Twitter, Kafka, Flume, HDFS, RabbitMQ etc.

![spark-streaming](../assets/images/SPARK-10.png)

#### Spark GraphX:

- Distributed graph processing framework built on top of Spark Core.
- Provides ETL, exploratory analysis, and iterative graph computation which enables the users to interactively build, and transform a graph data structure at scale.

![spark-graph](../assets/images/SPARK-11.png)

# Apache Spark Architecture

![rdd](../assets/images/SPARK-7.png)
![spark-architecture-1](../assets/images/SPARK-12.png)
![spark-architecture-2](../assets/images/SPARK-13.png)

#### Cluster Manager

![spark-clustermanager](../assets/images/SPARK-14.png)
