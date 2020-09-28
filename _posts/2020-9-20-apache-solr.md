---
layout: post
title: Basics of Apache Solr
categories: [Search, Miscellaneous]
---

`Apache Solr` is a Scalable & Portable search/storage engine used for searching large volumes of text-centric data.

  - Open Source (search/storage) platform

  - Enterprise Search server

  - Used for building Search Applications and Search APIs

  - Created by Yonik Seely in 2004

  - Apache Solr is built on top of Apache Lucene.

  - Along with search purpose, Solr can be used for storage purpose as well just like No-SQL Databases.

  - Provides capabilities for full text search such as tokens, phrases, spell-check etc.

  - Solr exposes Lucene Java APIs as a Rest-full API services.

  - Also used as a document-based NoSQL database with transactional support that can be used for storage purposes and even a key-value store.

  - Solr takes in structured, semi-structured and unstructured data from various sources, stores and indexes it, and makes it available for search in near real-time.

![solr](../assets/images/SOLR-4.png)

`Apache Lucene` is Java-based search library. Used to index and search voluminous amount of text. Basically, we store the index using Lucene & exposes searching on this index through SolR Rest APIs.

  - Written by Dough Cutting in 1999

  - Powerful, Scalable & Efficient Search Algorithm

![lucene](../assets/images/SOLR-3.png)

<ins>**Typical Search Flow**</ins>

![search-engine](../assets/images/SOLR-1.png)

There are 3 primary processes in Search:
1. Indexing
2. Querying
3. Ranking

All 3 should be in real-time for humungous amount of data.


`Indexing`
3 approaches for indexing documents:
- Files in json, xml, xlxs, csv format are indexed directly using index handlers
- Rich-text documents like pdf files are indexed using support from Apache Tika & ExtractingRequestHandler plugin.
- Data from databases, emails, RSS feeds are imported using the DataImportHandler plugin.


Solr uses Lucene for creating an inverted index as it inverts a page-centric data structure (documents => words) to a keyword-centric data structure (words => documents)
This resembles the index at the end of the book.

Faster response is achieved as keyword search is done in index instead of the directly scanning the text.


`Querying`
Search can be done for various terms such as keywords, images or geolocation data etc. Solr processes this using query request handlers.

`Ranking`
Solr matches the indexed documents to any query, & ranks the results by their relevance scores.


## Terminology

- <ins>**Document**</ins>
  - Basic unit of information in Solr which can be stored and indexed.
  - Documents are stored in collections.
  - Can be added, deleted, and updated, typically through request handlers(index handlers).


- <ins>**Field**</ins>
  - Field stores the data in a Document holding a key-value pair, where key states the field name and value the actual field data.
  - Solr supports different field types: float, date, text, integer, boolean, etc.


- <ins>**Collection**</ins>
  - Group of shards/cores that form a single logical index.
  - Each collection has its own set of configuration and schema definition, which can be different than other collections.
  - Only has meaning in the context of a Solr cluster in which a single index is distributed across multiple servers.


- <ins>**Core**</ins>
  - Running instance of a lucene index.
  - Uniquely named, managed, and configured index running in a Solr server
  - Composed of a set of configuration files, Lucene index files, and Solr’s transaction log
  - Collection and Core are same if search is not distributed.


- <ins>**Shard**</ins>
  - Allows to split and store index into one or more pieces, thus a shard is a slice of a collection.
  - Each shard lives on a node and is hosted in a core.


- <ins>**Replica**</ins>
  - Physical copy of a shard that runs as a core in a node


- <ins>**Cluster**</ins>
  - Specific to SolrCloud, cluster is made up of one or more nodes that store all the data, providing distributed indexing and search capabilities across all nodes


- <ins>**Node**</ins>
  - Single instance of Solr.
  - Can have multiple cores that can be part of any number of collections.


## Base Concepts

<ins>**Apache SolR Architecture**</ins>

![solr-architecture](../assets/images/SOLR-2.png)

Building blocks of Apache Solr:
  - <ins>**Request Handler**</ins>
  Used to process requests on Solr end, can be query request or index update requests

  - <ins>**Search Component**</ins>
  Feature provided by Solr, example: spellchecking, query, faceting etc.

  - <ins>**Query Parser**</ins>
  Parses the query passed to Solr, checks for syntactic error & translates them into a format lucene understands

  - <ins>**Response Writer**</ins>
  Generates formatted output for the queries. Supports xml, json, csv etc.

  - <ins>**Analyzer/Tokenizer**</ins>
  Lucene recognises data in form of tokens which are passed from Solr end. Analyzer examines the text of fields and generates token stream which is then broken into tokens by the Tokenizer

## Setting up Solr

```bash
jalaz@jalaz-personal:~/Downloads/solr-6.5.0$ ./bin/solr start
Waiting up to 180 seconds to see Solr running on port 8983
Started Solr server on port 8983 (pid=28500). Happy searching!
```

## Directory structure in Solr
_<Coming soon . . .>_

## Important files in Solr

`Solr.xml`
Contains Solr information. For loading cores, this file is referred.

`solrconfig.xml`
Contains core-specific configurations related to request handlers, indexing, managing memory, managing cache etc.

`schema.xml`
Contains the entire schema including field & field types

`core.properties`
Contains core configuration properties. Is used for core discovery during SolR start.


## Indexing in Solr

Systematically arranging documents in the Solr core.
  - Indexing collects, parses, and stores documents.
  - Indexing is done to increase the speed and performance of a search query while finding a required document.

In Apache Solr, we can index (add, delete, modify) various document formats such as xml, csv, pdf, etc.

Primarily 3 Ways:

  1. <ins>**Solr Web Interface**</ins>

      ![solr-ui](../assets/images/SOLR-5.png)

  2. <ins>**post tool**</ins>

      ```bash
      jalaz@jalaz-personal:~$ ./bin/post -c mcats similar-mcats.json
      jalaz@jalaz-personal:~$ ./bin/post -c intent -host fts-master.intermesh.net -p 8020 brands.csv
      jalaz@jalaz-personal:~$ ./bin/post -c keyword-mapping -host fts-master2.intermesh.net -p 8020 buyleads/bltransacted-*.xml -commit yes
      ```

  3. <ins>**Client APIs : SolrJ**</ins>

      ```java
      import java.io.IOException;  

      import org.apache.Solr.client.Solrj.SolrClient;
      import org.apache.Solr.client.Solrj.SolrServerException;
      import org.apache.Solr.client.Solrj.impl.HttpSolrClient;
      import org.apache.Solr.common.SolrInputDocument;

      public class DocumentIndexer {
         public static void main(String args[]) throws Exception {

            String urlString = "http://localhost:8983/Solr/mcat";
            SolrClient Solr = new HttpSolrClient.Builder(urlString).build();   

            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("mcatid", "003");
            doc.addField("mcatname", "Paper cutting machine");
            Solr.add(doc);
            Solr.commit();

            System.out.println("Documents indexed");
         }
      }
      ```

<ins>**Deletion/Modification/Addition formats**</ins>


## Querying in Solr
_<Coming soon . . .>_

## Request Handlers & Search Components in Solr
_<Coming soon . . .>_

## Faceting in Solr
_<Coming soon . . .>_

## Clustering in Solr
_<Coming soon . . .>_

#### SolrJ: Client API for Java

#### Apache Solr VS ElasticSearch

![solr-elastic](../assets/images/SOLR-6.jpg)

||Apache Solr|ElasticSearch|
|---|---|---|
|Installation and Configuration|Easy & have supportive documentation|Easy & have supportive documentation|
|Configuration files|XML-based configuration files|YML format|
|Indexing: data sources|Supports data ingestion from XML, CSV, JSONs, databases, PDFs etc. Apache Tika library allows over one thousand file types|Completely JSON-based|
|Searching|Support NRT (near real-time) searches and take advantage of all of Lucene’s search capabilities|Support NRT (near real-time) searches and take advantage of all of Lucene’s search capabilities|
|Indexing|Earlier Solr versions required a defined schema before indexing data. However, Solr now supports a schemaless mode|Elasticsearch is schemaless, it's easy to index unstructured data and dynamic fields without defining the schema of the index in advance|
|Use-Cases|Focuses on enterprise-directed text searches with advanced information retrieval. More suited for search applications that use massive amounts of static data|Focuses more on scaling, data analytics, and processing time series data to obtain meaningful insights and patterns. Large-scale log analytics performance makes it quite popular|
|Scalability and Clustering|Requires Support from Solr Cloud and Apache Zookeeper for cluster coordination|Have better inherent scalability & optimal design for cloud deployments|
|Shard/Node failure|Rebalancing is complex and hard to manage|Does cluster rebalancing itself and rarely requires a manual intervention|
|Community|Large ecosystem but now losing touch|Thriving ecosystem for ELK stack backed by Elastic Inc.|
|Pros|Offers great functionalities in the field of information retrieval|Much easier to take into production and scale|
