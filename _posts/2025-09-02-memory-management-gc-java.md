---
layout: post
title: Java - Memory Management and GC Concepts
categories: [Java]
---

# Java Memory Management

This refers to how the JVM allocates, manages, and releases memory during the execution of Java applications. JVM uses GC to reclaim memory by removing unused objects, eliminating the need for manual memory management, thus relieving developers work.

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

Depending on the configuration of the system, the size of the heap can be dynamic or fixed. We use `-Xmx` for maximum heap size & `-Xms` for initial heap size.


If a computation requires more heap than can be made available by the automatic storage management system, then JVM throws an `OutOfMemoryError`


Heap space is the area where all the objects are allocated, whether 
- the object is created inside a thread’s local method
- or is a member variable of an object
- or is a referenced object.
- Static member variables are also allocated on the heap space. (PS: There is no concept of global scope variables in Java)

In Java, we have different types of references: strong, weak, soft, and phantom references. The difference between the types of references is that the objects on the heap they refer to are eligible for GC under different criteria.

Pending: Add table for all 4 reference types & their details

### Stack Memory

In the JVM, stack memory plays a crucial role in method execution. Unlike heap memory, which stores objects for longer-term use and is shared across threads, the stack is thread-local and exists independently for each thread.

When a thread is created, the JVM allocates a new Java stack for that thread. This stack consists of a series of stack frames, each representing a single method invocation. As methods are called and return, frames are pushed to and popped from the top of the stack in a strict last-in, first-out (LIFO) order.

#### Stack Frames

On any method invocation, JVM allocates a new stack frame on the thread’s stack. This frame is a self-contained unit of memory that holds all the necessary data for executing that method. Once the method returns, its frame is removed from the stack, and the memory is automatically reclaimed; no garbage collection is required for stack memory.

- are lightweight
- quick to allocate.
- are thread-local and do not require synchronization, so operations on the stack (method calls and returns) are extremely fast.

Only references to objects are stored on the stack. The actual objects, including their fields and internal state, reside in the heap.

Each thread’s stack is limited in size, which can be configured using the `-Xss` JVM option.

If a thread calls too many nested methods or recurses too deeply, the stack may exceed this limit and trigger a `StackOverflowError`.


### MetaSpace or Method Area

- Introduced in Java 8, Metaspace is a non-heap memory area that replaced the PermGen.
- It stores class metadata, such as the class structure, method information, and the constant pool.
- Static variables are stored in the heap. This is a change from older Java versions (pre-Java 8), where they were stored in a special area of the heap called the Permanent Generation (PermGen).
- In Java 8 and later, the actual data for static variables is allocated on the heap, even though the class metadata (which includes a reference to the static variable) is in Metaspace

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



