---
layout: post
title: NoSQL Databases
categories: [Database]
---

NoSQL databases are non tabular, and store data differently than relational tables.

- Provides flexible schemas.
- Scale easily with large amounts of data and high user loads.

### Need for NoSQL

- <ins>**Relationship data**</ins>
  - NoSQL DBs store relationship data differently than relational databases do.
  - Modeling relationship data is easier than in SQL databases.


- <ins>**Storage Cost**</ins>
  - Drastic drop in the storage cost after 90s, developer cost became major one. For easing developer's effort NoSQL DBs were introduced & optimised.
  - Software engineers used to normalize their databases in order to reduce data duplication during earlier days.


- <ins>**Randomness & Quanity of data**</ins>
  - Data started coming in varied shapes & sizes.
  - Amount of data generated & fed also increased manifold.
  - NoSQL DBs allow developers to store huge amounts of unstructured data, giving them a lot of flexibility.


- <ins>**Introduction of cloud-computing**</ins>
  - Enterprises desired their applications & data hosted on cloud. This data was required to be distributed on multiple servers & regions for application resilience.
  - Also helped in better geo-placing their data.
  - NoSQL DBs like Mongo provided these capabilities

![no-sql](../assets/images/NOSQL-1.png)

#### Types of NoSQL Databases

<ins>**Document Databases:**</ins>
- Stores data in documents similar to JSON objects.
- Each document contains pairs of fields and values.
- Due to presence of variety of field types & powerful query languages, used as general purpose databases.
- Can easily scale-out for storing humongous amount of data.
- Example: MongoDB, MariaDB, Apache Solr


<ins>**Key-value Databases:**</ins>
- Store data in the form of keys and values.
- Simpler type of database
- Great when large amounts of data is stored but no need of performing complex queries to retrieve that data.
- Great for caching purposes or storing user preferences.
- Example: Redis, DyanoDB


<ins>**Wide-column Stores:**</ins>
- Store data in tables, rows, and dynamic columns.
- Provides lot of flexibility over relational databases as each row is not required to have the same columns.
- Great when large amounts of data is stored and the future query patterns can be preicted.
- Commonly used for storing IoT data and user profile data. (Clickstream data)
- Example: Cassandra, HBase

<ins>**Graph Databases:**</ins>
- Store data in nodes and edges
- Nodes store information about entities(users, company, products) while edges store information about the relationships between these nodes.
- Great for usecases where we need to traverse relationships to look for patterns such as social networks, fraud detection, and recommendation engines.
- Example: Neo4j, JanusGraph

#### When should NoSQL be used over SQL

- **Development Pace**
  - Is Higher for NoSQL as compared to SQL
  - Due to sprints & frequent code changes, NoSQL supports quick changes as opposed to SQL, where schema needs to be updated by DB Admin, Data unload & Data load is required for each change.


- **Mutli-form Data**
  - NoSQL DBs can better handle & are evolved for varied forms of data.
  - NoSQL DBs are better suited for storing & modeling structured, semi-structured & unstructured data together.
  - NoSQL DBs store data in a form that is similar to the objects used in application thus, reducing the efforts & cost in translation.


- **Large Amount of data**
  - Data generated from various applications can't be served affordably by SQL DBs.
  - Handling Bigdata is the fundamental architectural requisite.
  - NoSQL DBs use scale-out mechanism which is much better than the scale-up method of SQL DBs.
  - Scale-out architecture is one of the most affordable ways to handle large volumes of traffic
  - Scale-out architectures also provide benefits such as being able to upgrade a database or change its structure with zero downtime.


- **Application paradigm support**
  - Scalability of NoSQL databases allows one DB to serve both transactional and analytical workloads from the same database. In SQL databases, usually, a separate data warehouse is used to support analytics.
  - NoSQL databases often have superior integration with real-time streaming technologies.

### Drawbacks

### SQL vs NoSQL Databases
