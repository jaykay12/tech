---
layout: post
title: Basics of REST APIs
categories: [Web Development]
---

It stands for `REpresentational State Transfer`. It is a set of rules or architectural style that developers should follow while creating APIs or Web Services. Presented by Roy Fielding in 2000 as doctoral dissertation.       
Web services that conform to the REST architectural style, are called RESTful Web services, provide interoperability between computer systems on the internet.

An **API** is an application programming interface. It is a set of rules that allow programs to talk to each other. The developer creates the API on the server and allows the client to talk to it. **REST** determines how the API looks like.

Each URL is called a request while the data sent back to you is called a response.
Each request comprises of 4 components:

- <ins>**Endpoint:**</ins> Also called route, is the url you request for. Comprises of (root-endpoint + path)

- <ins>**Methods:**</ins> GET/POST/PUT/DELETE/PATCH

- <ins>**Headers:**</ins> Used to provide information to both the client and server. It can be used for many purposes, such as authentication and providing information about the body content.

- <ins>**Body:**</ins> Contains information which is to be sent to the server. This option is only used with POST, PUT, PATCH or DELETE requests. Also called "data" or "message".

_REST got its name as it's based upon creating an object of the data requested by the client on server-side and send the values of the object (state of object) as the response to the user_

## Guiding Principles of REST

- Client–server
- Stateless
- Cacheable
- Uniform interface
- Layered system
- Code on demand (optional)

**Client–server :**
Enables a uniform interface through-out and separates clients from the servers.  This enhances the portability across multiple platforms/clients as well as the scalability of the server components.

**Stateless :**
Each request from client to server must contain all of the information necessary to understand the request, and cannot take advantage of any stored context on the server. Session state is therefore kept entirely on the client.        

In other words, it should be possible to make two or more HTTP requests in any order and the same responses will be received.

**Cacheable :**
In order to provide a better performance, the applications are often made cacheable. Cache constraints require that the data within a response to a request be implicitly or explicitly labeled as cacheable or non-cacheable. If a response is cacheable, then a client cache is given the right to reuse that response data for later & equivalent requests.

**Uniform interface :**
To obtain the uniformity throughout the application, REST has the following four interface constraints:
- Resource identification
- Resource Manipulation using representations
- Self-descriptive messages
- Hypermedia as the engine of application state

**Layered system :**
The layered system style allows an architecture to be composed of hierarchical layers which allows an application to be more stable by limiting component behavior. Enhances the application’s security as components in each layer cannot interact beyond the next immediate layer they are in. Also, it enables load balancing and provides shared caches for promoting scalability.       

The requesting client need not know whether it’s communicating with the actual server, a proxy, or any other intermediary.

**Code on demand :**
This is an optional constraint and is used the least. Permits client functionality to be extended by downloading and executing code in the form of applets or scripts. This simplifies clients by reducing the number of features required to be pre-implemented.


**_Till the time, we are honoring the 6 guiding principles of REST, we can easily call our interface RESTful._**

> All these principles help RESTful applications to be simple, lightweight, and fast.

#### Request Methods

- **GET** request to _/user/_ returns a list of registered users on a system.

- **POST** request to _/user/123_ creates a user with the ID 123 using the body data.

- **PUT** request to _/user/123_ updates user 123 with the body data.

- **GET** request to _/user/123_ returns the details of user 123.

- **DELETE** request to _/user/123_ deletes user 123.

![REST API](../assets/images/RA-1.png)

## REST Challenges

- Endpoint Consensus
- API Versioning
- Authentication
- Security
- Multiple Requests and Unnecessary Data

These REST conundrums led Facebook to create GraphQL — a web service query language.
GraphQL addresses many of the challenges posed by RESTful APIs. It’s worth considering GraphQL once your RESTful API evolves beyond its simple starting point.

## Extra information

`Resource`:

The key abstraction of information in REST is **Resource**. Any information that can be named can be a resource: a document or image, a temporal service, a collection of other resources, a non-virtual object (e.g. a person), and so on.

REST uses a **Resource Identifier** to identify the particular resource involved in an interaction between components.

The state of the resource at any particular timestamp is known as **Resource Representation**. A representation consists of
- Data
- Metadata describing the data
- Hypermedia links which can help the clients in transition to the next desired state.

Resource representations shall be self-descriptive.

`REST vs HTTP`:

In the **REST** architectural style, data and functionality are considered resources and are accessed using Uniform Resource Identifiers (URIs). The resources are acted upon by using a set of simple, well-defined operations. The clients and servers exchange representations of resources by using a standardized interface and protocol – typically **HTTP**.

> REST != HTTP

`SWAGGER`:

`POSTMAN & POSTWOMAN`:

`SOAP APIs`:
