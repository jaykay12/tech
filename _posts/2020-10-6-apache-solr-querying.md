---
layout: post
title: Basics of Apache Solr - II
categories: [Search, Miscellaneous]
---

## Searching in Solr

1. For searching anything on solr, userquery is processed by a `RequestHandler`. It handles all the incoming requests on solr like search requests, health check requests, index replication requests etc. For searching, default /select request handler is used.

2. For further processing of query, `QueryParser` is invoked by the RequestHandler, which interprets the terms and parameters of query.

  3 main query parsers in solr:
  - Standard
    - Default one
    - Also called Lucene Query Parser
    - Allows greater precision in search

  - DisMax
    - More tolerant to syntax errors
    - Provides similar experience as normal seach engines like google.

  - EDisMax (Extended DisMax)
    - Improved version of DisMax.
    - Handles full lucene query syntax while still tolerating syntax errors.

  Input to Query Parsers comprises of:
    1. Search strings for querying in index
    2. Parameters for fine tuning this search like boosting, query-fields, query-slopes, filter queries etc.
    3. Parameters for controlling the response presentation like fl, wt etc.


3. While creating response using `ResponseWriter`, the results can be grouped using 2 techniques:
    1. faceting
    2. clustering


![search-overview](../assets/images/SOLR-7.png)


#### Query Parsers

#### Querying in Solr

#### Request Handlers & Search Components in Solr

  - `Request Handler` is a Solr plug-in which defines the logic that should be used for processing any request at Solr's end.

  - Is defined with a name and a class.

  - http://localhost:8983/solr/gettingstarted/select?q=solr
  This query is processed by the request handler with the name `/select`, which is the by default handler for query request in solr.

  - Solr supports a variety of request handlers. Some are designed for processing search queries, while others manage tasks such as index replication.

  - Mainly 4 types of RequestHandlers:
    1. QueryHandler/SearchHandler
    2. UpdateRequestHandler/IndexHandler
    3. ShardHandler
    4. Implicit Handler


  - **QueryHandler**
    - Primary request handler, handles the search queries.
    - Have 4 sections: defaults, invariants, appends and components.


  - **UpdateRequestHandler** or **IndexHandler**
    - Process updates to the solr index.
    - `/update` - UpdateRequestHandler - Add, delete and update indexed documents


  - **ShardHandler**
    - Configured for searching across the shards of a cluster
    - Used only with distributed search.


  - **Implicit Handler**
    - Implicit -> As they need not be configured in solrconfig.xml explicitly.
    - Few notable ones are:
        1. /replication
        2. /config - SolrConfigHandler - Retrieve/modify Solr configuration.
        3. /admin/ping - PingRequestHandler - Health check.
        4. /admin/properties - PropertiesRequestHandler - Return JRE system properties.
        5. /schema - SchemaHandler - Retrieve/modify Solr schema.


<ins>**Defining a SearchHandler & Usage**</ins>

Search Handlers can be configured with three sets of Query Params:

- `defaults`
  - provides default values to parameters which are used when no value is provided to these parameters during querying.


- `appends`
  - provides param values which are used additionally to the values specified at request time (or as defaults)
  - These might be filter queries, or other query rules that should be added to each query.
  - No mechanism in Solr for overriding these additions at client end. (Use cautiously)


- `invariants`
  - provides param values that will be used in spite of any values provided at request time.
  - Used by Solr maintainer to lock down the options available to Solr clients.
  - Any params values specified here are used regardless of the "defaults", or the "appends" params.
  - Allows definition of parameters that cannot be overridden by a client.

```bash


```



#### Faceting in Solr

Faceting is the arrangement of search results into categories (which are based on indexed terms). Within each category, Solr reports on the number of hits for relevant term, which is called a facet constraint. Faceting makes it easy for users to explore search results on sites such as movie sites and product review sites, where there are many categories and many items within a category.

Faceting makes use of fields defined when the search applications were indexed

#### Clustering in Solr

Clustering groups search results by similarities discovered when a search is executed, rather than when content is indexed. The results of clustering often lack the neat hierarchical organization found in faceted search results, but clustering can be useful nonetheless. It can reveal unexpected commonalities among search results, and it can help users rule out content that isn’t pertinent to what they’re really searching for.

#### Lucene Score Calculation
