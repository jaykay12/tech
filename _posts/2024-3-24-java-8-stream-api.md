---
layout: post
title: Java 8 - Stream API & Parallel Streams
categories: [Java]
---

Major Concepts of Java 8:
- Functional Interfaces
- CompletetableFutures
- Stream API & ParallelStreams
- Lambda functions

# Stream API

Introduced in Java 8 (JDK 1.8), Stream API is used to 
- perform bulk operations
- process collections of objects.



Different operations on Stream API:
1. Intermediate Operations
2. Terminal Operations

Intermediate Operations are the types of operations in which multiple methods are chained in a row. They take Stream as argument & returns Stream. Eg: map(), filter(), sorted()
Terminal Operations are the type of Operations that return the result. These Operations are not processed further just return a final result value. eg: collect(), reduce(), forEach()

Important Points:
1. A stream comprises of a source followed by pipeline of zero or more intermediate methods and a final terminal method which obtains the results as per the pipeline.
2. Stream is used to compute elements as per the pipelined methods without altering the original value of the input object.

Stream once used, can't be reused.

```java
List<String> names = Arrays.asList("Jalaz", "Narendra", "Saurabh");
Stream<String> streamedNames = names.stream();
streamedNames.forEach(y -> System.out.println(y));
streamedNames.forEach(y -> System.out.println(y));
```
```bash
Jalaz
Narendra
Saurabh
ERROR!
Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
```



# ParallelStream



