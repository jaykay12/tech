---
layout: post
title: In-Memory Caching
categories: [Web Development]
---

# Introduction

Caching has a significant impact on system design, particularly in systems that take excessive user requests. Improved performances & Scalability are 2 main key motives for using caching in designing of system alongside the cost factor.

---INSERT IMAGE HERE---


When system stores the data in a RAM, it is called in-memory caching. It is the simplest cache as compared to the other cache forms. It sits between applications and databases to deliver responses at high speeds by storing data from earlier requests or by copying directly from databases.

`Caching operates on the principle of locality of reference - recently & frequently accesses data is likedly to be needed again`

In memory caching avoids latency and improves application performance as RAM has an extremely faster speed of retrieval as compared to hard disks or making network calls.

## Concepts

Components of any in-memory caching solution:

- Cache Store: The primary storage component where data is stored in memory.
- Cache Entries: Individual items of data stored in the cache, typically consisting of key-value pairs.
- Cache Eviction Policy: Determines which cache entries are removed when the cache reaches its maximum capacity.
- Cache Expiration Policy: Specifies how long cache entries are valid before they expire and need to be refreshed or removed.
- Concurrency Control: Mechanisms to ensure safe and efficient access to the cache in multi-threaded or distributed environments.
- Monitoring and Management: Tools for monitoring cache performance, usage, and health, and managing cache configurations.

Types of Cache:

1. Single-Level Cache
2. Multi-Level Cache
3. Distributed Cache
4. Near Cache
5. Transactional Cache
6. In-Memory Data Grid (IMDG)

### Caching Strategies

1. Cache Pre-loading
- Proactive caching technique where cache is populated with data even before the actual request comes either during app startup or during low business hours
- reduces the likelihood of cache misses & improves system performance
- Useful when the pattern of access is known & for smaller data size

2. Cache-Aside
- Here, any request is firstly checked in cache, in case of cache hit it is served from cache, in case of miss, the response is gathered from the database or API & returned back, this data is asynchronously written to the cache also for future requests.
- Useful for system where fine grain control over the caching logic is required

3. Read-through (Lazy loading)
- Here we defer caching until the data is actually being requested for.
- This treat the cache as the main database. When looking for an entry, the application will hit the cache first; if the data is missing, then the cache will be responsible for pulling the data from the main database and updating it.
- Reduced Memory Usage
- Useful for system when we want to keep the application start-up time lesser & for bigger datasets.

4. Write-through
- Used for write opertaions, not for read/retrievals.
- Application writes to the cache & cache writes synchronously to the data store & this impacts the overall write latency.
- Main advantage is Data Consistency: as data in cache & data store will always be in sync.
- Useful for systme where Data consistency is a must & in-frequent writes happen.

5. Write-behind
- Similar to write-through, This optimises write operations by first writing to cache & then asynchronously propagating these changes to the data store in background.
- Improved write performance & reduced write latencies
- Useful for system where Eventual Consistency is agreed upon & frequent writes happen.

### Eviction Policies

1. Least recently used (LRU): Evicts the data that was least recently used, first.

2. Least frequently used (LFU): Evicts data that was least frequently used. Meaning that the data that is set to be removed might have been used recently, but wasn’t used as often as the rest of the data.

3. Most recently used (MRU): In contrast to LRU, MRU removes data that was most recently used.

4. First in first out (FIFO): This eviction policy removes data that was added first, or least recently. It is unrelated to usage and is based solely on when the data was entered into the cache.

## Java In-Memory Caching



# Prominent Libraries

### JCS (Java Caching System)

### Guava

### Caffeine

### EhCache

### InfiniSpan

### Coherence

### Ignite

### Geode

### Hazelcast

# Sample Project
