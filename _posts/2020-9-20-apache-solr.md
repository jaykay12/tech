---
layout: post
title: Basics of Apache SolR
categories: [Search, Miscellaneous]
---

`Apache SolR` is a Scalable & Portable search/storage engine used for searching large volumes of text-centric data.

  - Open Source (search/storage) platform

  - Enterprise Search server

  - Used for building Search Applications and Search APIs

  - Created by Yonik Seely in 2004

  - Apache SolR is built on top of Apache Lucene.

  - Along with Search purpose, SolR can be used for storage purpose as well just like No-SQL Databases.

  - Provides capabilities for full text search such as tokens, phrases, spell-check etc.

  - SolR exposes Lucene Java APIs as a Rest-full API services.

![solr](../assets/images/SOLR-4.png)

`Apache Lucene` is Java-based search library. Used to index and search voluminous amount of text. Basically, we store the index using Lucene & exposes searching on this index through SolR Rest APIs.

  - Written by Dough Cutting in 1999

  - Powerful, Scalable & Efficient Search Algorithm

![lucene](../assets/images/SOLR-3.png)

<ins>**Typical Search Flow**</ins>

![search-engine](../assets/images/SOLR-1.png)

<ins>**Apache SolR Architecture**</ins>

![solr-architecture](../assets/images/SOLR-2.png)

Building blocks of Apache SolR:
  - <ins>Request Handler</ins>
  Used to process requests on SolR end, can be query request or index update requests

  - <ins>Search Component</ins>
  Feature provided by SolR, example: spellchecking, query, faceting etc.

  - <ins>Query Parser</ins>
  Parses the query passed to SolR, checks for syntactic error & translates them intoa format lucene understands

  - <ins>Response Writer</ins>
  Generates formatted output for the queries. Supports xml, json, csv etc.

  - <ins>Analyzer/Tokenizer</ins>
  Lucene recognises data in form of tokens which are passed from SolR end. Analyzer examines the text of fields and generates token stream which is then broken into tokens by the Tokenizer

<ins>**Apache SolR VS ElasticSearch**</ins>

|Apache SolR|ElasticSearch|
|---|---|
|||
|||
|||
|||

## Terminology



## Base Concepts

SolR core is a running instance of a lucene index. We create solr core for performing operations like indexing and analyzing.

## Setting up SolR
_<Coming soon . . .>_

## Directory structure in SolR
_<Coming soon . . .>_

## Important files in SolR

`Solr.xml`
Contains SolR information. For loading cores, this file is referred.

`solrconfig.xml`
Contains core-specific configurations related to request handlers, indexing, managing memory, managing cache etc.

`schema.xml`
Contains the entire schema including field & field types

`core.properties`
Contains core configuration properties. Is used for core discovery during SolR start.


## Indexing in SolR
_<Coming soon . . .>_

## Querying in SolR
_<Coming soon . . .>_

## Request Handlers & Search Components in SolR
_<Coming soon . . .>_

## Clustering in SolR
_<Coming soon . . .>_

## SolrJ
