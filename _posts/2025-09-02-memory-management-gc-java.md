---
layout: post
title: Java - Memory Management and GC Concepts
categories: [Java]
---

# Java Memory Management

It refers to how the JVM allocates, manages, and releases memory during the execution of Java applications. JVM uses GC to reclaim memory by removing unused objects, eliminating the need for manual memory management, thus relieving developers work.

Efficient memory management is very critical to building reliable, high-performance Java applications. JVM handles memory allocation and garbage collection automatically, but deeper understanding of how the memory is structured and managed under the hood is essential for diagnosing issues, tuning performance, and writing optimized code.

## Java Memory Model

JVM divides the system memory into several logical runtime data areas, each serving a specific role during program execution. This structured memory model ensures isolation between different types of data, supports multithreading, and enables features like automatic GC.

At runtime, the JVM creates a set of runtime data areas. These include:

- Heap Memory
- Stack Memory
- Method Area (Implemented as Metaspace in Java 8+)
- Program Counter (PC) Register
- Native Method Stack

Each area plays a unique role in executing Java programs and managing resources.

![jmm-overview](../assets/images/JMM-1.png)

### Heap Memory

This part of memory stores the actual object in memory. Those are referenced by the variables from the stack.
There exists only one heap memory for each running JVM process & is shared among all Java Virtual Machine threads.

Depending on the configuration of the system, the size of the heap can be dynamic or fixed. We use -Xmx for maximum heap size & -Xms for initial heap size.

- If a computation requires more heap than can be made available by the automatic storage management system, then JVM throws an OutOfMemoryError.

Heap space is the area where all the objects are allocated, whether 
- the object is created inside a thread’s local method
- or is a member variable of an object
- or is a referenced object.
- Static member variables are also allocated on the heap space. (PS: There is not concept of global scope variables in Java)

In Java, we have different types of references: strong, weak, soft, and phantom references. The difference between the types of references is that the objects on the heap they refer to are eligible for GC under different criteria.

Pending: Add table for all 4 reference types & details


### Stack Memory

### MetaSpace

### Program Counter

### Native Method Stack

![heap-stack](../assets/images/JMM-2.png)


# Garbage Collection


### Details of Heap Memory Management

#### Young Generation

#### Old Generation


Good Reference Blogs for JMM:
- https://www.digitalocean.com/community/tutorials/java-jvm-memory-model-memory-management-in-java
- https://medium.com/@amitvsolutions/jvm-part-3-memory-management-89acca050442
- https://www.linkedin.com/pulse/memory-management-java-softyoi-llp-wxrsf


Good Reference Blogs for GC:
- https://www.digitalocean.com/community/tutorials/java-jvm-memory-model-memory-management-in-java
- https://dzone.com/articles/java-memory-management
- https://medium.com/@RamLakshmanan/how-to-achieve-high-gc-throughput-2a5405417d95


Interview Questions:
- https://www.javacodegeeks.com/2024/09/java-memory-management-key-interview-questions-and-expert-answers.html
- https://www.baeldung.com/java-memory-management-interview-questions



