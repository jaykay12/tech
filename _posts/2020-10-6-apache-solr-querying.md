---
layout: post
title: Basics of Apache Solr - II
categories: [Search, Miscellaneous]
---

## Searching in Solr

- For searching anything on solr, userquery is processed by a `RequestHandler`. It handles all the incoming requests on solr like search requests, health check requests, index replication requests etc. For searching, default /select request handler is used.

- For further processing of query, `QueryParser` is invoked by the RequestHandler, which interprets the terms and parameters of query.

  QueryParsers are extended from the class: QParserPlugin. For creating custom query parser, this class needs to be extended.

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


- While creating response using `ResponseWriter`, the results can be grouped using 2 techniques:
    1. faceting
    2. clustering


![search-overview](../assets/images/SOLR-7.png)


### Query Syntax and Parsing

#### Common Query Parameters

- defType
  - Selects the query parser that Solr should use to process q in the request
  - http://localhost:8985/solr/mcat/select?q=jute%20bag&fl=name,id&defType=dismax
  - By default, defType="lucene" is used.
  - http://localhost:8985/solr/mcat/select?q=jute%20bag&fl=name,id

- sort
  - Arranges search results in either ascending (asc) or descending (desc) order.
  - Can be used with either numerical or alphabetical content.
  - http://localhost:8985/solr/mcat/select?q=bags&fl=name&wt=json&sort=total_prod_count%20asc
  - By default, sort=desc is used.

- start
  - Specifies an offset into the result set of query and instructs solr to begin displaying results from this offset.
  - By default: start=0
  - http://localhost:8985/solr/mcat/select?q=bags&fl=name&wt=json&start=100
  - Motive is to be used effectively for paging.

- rows
  - Specifies the maximum number of documents that solr should return.
  - Use to paginate results from a query
  - By default: rows=10
  - http://localhost:8985/solr/mcat/select?q=bags&fl=name&wt=json&rows=50

- fq
  - FilterQuery parameter
  - Defines a query that can be used to restrict the superset of documents that can be returned, without influencing score.
  - Analogous to "WHERE" clause of SQL.
  - Service MCATs: http://localhost:8985/solr/mcat/select?q=car%20wash&fl=name&wt=json&fq=type:S
  - Product MCATs of a fixed SubCat: http://localhost:8985/solr/mcat/select?q=car%20wash&fl=name&wt=json&fq=type:P&fq=catid:27
  - Motive: Very useful for speeding up complex queries, since the queries specified with fq are cached independently apart from the main query.

- fl
  - FieldList parameter
  - Limits the fields included in the query response.
  - By default: fl=*
  - http://localhost:8985/solr/mcat/select?q=bags&wt=json&fl=id,name,type
  - Use aliasing of field names like http://localhost:8985/solr/mcat/select?q=bags&wt=json&fl=id,name,products:total_prod_count

- debug
  - debug=query, debug=timing, debug=all etc are various variations as per the usecase of debugging.
  - By default: debug=false; Not to include debugging information

- timeAllowed
  - specifies the amount of time, in milliseconds, allowed for a search to complete.
  - If this time expires, any partial results will be returned, but values such as numFound, facet counts, and result stats may not be overall accurate.
  - http://localhost:8985/solr/mcat/select?q=bags&wt=json&fl=id,name&rows=10&timeAllowed=10


- omitHeader
  - If set to true, header is excluded from the response which contains info about requests, like time_taken, numFound etc.
  - By default: omitHeader=true

- wt
  - Selects the Response Writer that solr should use for formatting the query’s response.
  - By default: wt=xml
  - http://localhost:8985/solr/mcat/select?q=bags&fl=id,name,catid,catname,groupid,groupname&wt=json
  - http://localhost:8985/solr/mcat/select?q=bags&fl=id,name,catid,catname,groupid,groupname&wt=csv

- cache=false
  - Used for disabling result caching at solr's end.

- echoParams
  - Controls the info about request parameters that should be included in the response header.
  - By default: echoParams=explicit
  - http://localhost:8985/solr/mcat/select?q=bags&fl=id,name,score&wt=json&echoParams=all
  - http://localhost:8985/solr/mcat/select?q=bags&fl=id,name,score&wt=json&echoParams=none

### Standard Query Parser
In addition to the Common Query Parameters, Faceting Parameters, Highlighting Parameters etc, it supports these parameters as well:

- q
  - Defines a query using standard query syntax(tight syntax, gives syntactic errors)
  - Mandatory parameter.

- df
  - DefaultField parameter
  - Defines the by default field, inwhich q should be searched against.
  - Overidden by qf(QueryField)

- sow
  - Split on whitespace
  - Seeting to false, very useful for analysis of words without splitting like multi-word synonyms, shingles etc.
  - By default: true, analysis is invoked on each space separated word.

Query sent to StandarQueryParser is composed of terms and operators. Terms are of 2 types:
  - single term such as q=lg, q=washing machine, q=lg washing machine :-> Term Query
  - search phrases such as q=\"lg washer\", q=\"washing machine\" :-> Phrase Query


In setting up q, we can specify fields as well. In Schema, df is defined, if no field is provided in q like q=jute bags then, it will be searched in df. But a query like q=title:jute bags will search this in the field: title.


Various term modifiers are used for further encouraging the result set:
  - `Wildcard searches`
    - Can only be applied to single terms, but not to search phrases
    - q=te?t will match test, text, teit etc.
    - q=tes* will match test, testing, tesla, tesseract etc.


  - `Fuzzy searches`
    - Used to discover terms that are similar to a specified term without necessarily being an exact match
    - q=roam~ will match terms like rome, roam, roams, foam, & foams.
    - q=roam~1 will only match terms like roams, foam as edit distance is fixed at 1.


  - `Proximity searches`
    - Looks for terms that are within a specific distance from one another.
    - Used with search phrases
    - q="redmi cover"~5 will match all documents having redmi and cover at atmost distance of 5.
    - The distance referred to here is the number of term movements needed to match the specified phrase.


  - `Range searches`
    - Matches documents whose values for the specified field or fields fall within the range
    - q=rating:[8 TO 10] will match all rating documents having ratings 8,9,10.
    - q=rating:{0 TO 6} will match all rating documents having ratings 1,2,3,4,5.


  - `Boosting in searches`
    - Lucene/Solr provides the relevance level of all matching documents based on the terms found in them.
    - The relevancy can be controlled by using boosting on the basis of terms/pharses.
    - q=jakarta^4 apache in term query, will boost scores of all documents having jakarta by 4 & make them more relevant.
    - q="Apache Solr"^4 "Apache Lucene" in phrase query, will boost scores of all documents having "Apache Solr" by 4, making them more relevant.


### DisMax Query Parser


### EDisMax Query Parser


    Apart from these 3 important query parsers, there are few others as well namely:
    - Boost query parsers
    - Complex phrase query parsers
    - Field query parsers
    - Function query parser
    - Lucene query parser
    - Learning to Rank query parser etc.

## Request Handlers & Search Components in Solr

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

### Clustering in Solr

Clustering groups search results by similarities discovered when a search is executed, rather than when content is indexed. The results of clustering often lack the neat hierarchical organization found in faceted search results, but clustering can be useful nonetheless. It can reveal unexpected commonalities among search results, and it can help users rule out content that isn’t pertinent to what they’re really searching for.

#### Lucene Score Calculation
