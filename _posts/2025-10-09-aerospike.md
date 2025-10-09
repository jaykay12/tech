---
layout: post
title: Aerospike, Realtime NoSQL Database
categories: [NoSQL Databases, Database]
---

![aero](../assets/images/AERO-1.jpeg)

`The realtime database built for massive scale, speed and savings`

Aerospike is a
- Distributed NoSQL database
- Has Diverse data model which support the scale to PetaBytes of data
- Deliver's Industries lowest latency with the highest throughput
- Excels at processing millions of TPS with the smallest infrastructure footprint

![aero-benefits](../assets/images/AERO-5.jpeg)

It is a multi-model DB that supports both Key-Value Store and Document Store models (and Graph) through a flexible, underlying record structure.
- **Key-Value Store**: Low-latency, & fast key-based lookups (the original & primary strength).
- **Document Store**: Storing, indexing, and querying flexible JSON documents using its Map and List Collection Data Types.
- **Graph Database**: Offering a dedicated Graph database component with recent releases.

# Introduction

![aero-intro](../assets/images/AERO-2.jpeg)

These are the 3 core saliant features:
1. Scalability - Flash and Hybrid Memory Architectures allows Aerospike DB to scale PBs of data
2. Speed - Low Latency is maintained at a high scale
3. Low TCO - Due to hybrid memory architecture and compression, Aerospike provides significantly lower (~20%) total cost of operations


# Components of Aerospike

## CAP Theorem & Aerospike

In distributed systems, we can only guarantee two out of three properties: Consistency, Availability, and Partition tolerance.

Aerospike handles the CAP theorem by providing a tunable consistency model that allows us to choose which two properties to prioritize on a per-namespace basis:

- **Available and Partition-tolerant (AP) Mode**
  - Provides Eventual Consistency
  - Is the default mode
  - The cluster continues to accept reads and writes on all sides of the partition. This ensures high availability, but clients may read stale data or lose writes until the partition is resolved and the data converges.

- **Strongly Consistent and Partition-tolerant (CP) Mode**
  - Provides Strong Consistency
  - Aerospike uses a roster-based strong consistency scheme.
  - To uphold SC, the minority side (the smaller part of the partitioned cluster) or a partitioned node will stop accepting writes and may become temporarily unavailable for that data. This enforces consistency by sacrificing availability on the affected nodes/partitions.

`Aerospike prioritizes either of consistency and availability while maintaining strong partition tolerance`



## Benefits

`Aerospike is Capable of real-time transactional workloads that support mission-critical applications.`

Hybrid Memory Architecture™ delivers RAM-like performance with SSDs that scale from GBs to PBs with sub-millisecond latency.

- Lightning-fast performance
  - Realtime transactions with low latency

- Highly Scalable & Distributed
  - Database can be scaled as storage needs increases
  - Distributed architecture allows horizontal scalability with ease
 
- Transactional Workloads
  - Best for applications that desire flexibility of NoSQL with ACID guarantees.

## Industry Usecases

![aero-database](../assets/images/AERO-3.jpeg)

![aero-business](../assets/images/AERO-4.jpeg)

## Head on Head Comparision with Redis

If we are building mission-critical applications where data consistency is a must, then Redis is not likely the right choice. Redis has not passed the Jepsen test for strong consistency.

To scale Redis, more nodes and DRAM are needed because it’s a single-threaded system designed for in-memory processing. But DRAM is expensive, and thus managing increasingly large clusters isn’t easy. 

### Challenges in Redis

1. One Master, multiple Slaves — i.e. the ‘write’ throughput is limited by the one machine on which the master is running on.
2. Redis is single-threaded - no vertical scalability in terms of CPU.
3. Real-time master-slave synchronization issues — huge amount of writes on the master requires that all the changes had to be synchronized with the slaves. This can only be done by taking slaves offline for synchronization because huge chunks of data sync and serve incoming requests at the same time is not possible
4. No handy way of storing multiple different types of data in the same database - had to store different entities in different Redis instances
5. Redis is in-memory only, which means it becomes very costly at scale

### How Aerospike Helps

1. Partitioning — 4096 partitions by default, spreaded across the cluster nodes. This helps us with the ‘write’ throughput.
2. Aerospike is multithreaded - making the most of the resources
3. 0 downtime for master-replica synchronizations — ‘write’ policy can be configured such that the write-request is considered ‘finished’ only after the replica creation confirmation.
4. Namespaces - different types of data can be stored in the same cluster under different namespaces
5. Aerospike has two modes: SSD versus in-memory - Offers competitive performance with SSDs.

# Usage

## Setting up in Local

## AQL Commands

# Demo
