---
layout: post
title: Java - Memory Management and GC Concepts
categories: [Java]
---

# Java Memory Management

This refers to how the JVM allocates, manages, and releases memory during the execution of Java applications. JVM uses GC to reclaim memory by removing unused objects, eliminating the need for manual memory management, thus relieving developers work & headache.

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

In JVM, stack memory plays a crucial role in method execution. Unlike heap memory, which stores objects for longer-term use and is shared across threads, the stack is thread-local and exists independently for each thread.

When a thread is created, the JVM allocates a new Java stack for that thread. This stack consists of a series of stack frames, each representing a single method invocation. As methods are called and return, frames are pushed to and popped from the top of the stack in a strict last-in, first-out (LIFO) order.

#### Stack Frames

On any method invocation, JVM allocates a new stack frame on the thread’s stack. This frame is a self-contained unit of memory that holds all the necessary data for executing that method. Once the method returns, its frame is removed from the stack, and the memory is automatically reclaimed; no garbage collection is required for stack memory.

- are lightweight
- quick to allocate.
- are thread-local and do not require synchronization, so operations on the stack (method calls and returns) are extremely fast.

Only references to objects are stored on the stack. The actual objects, including their fields and internal state, reside in the heap.

Each thread’s stack is limited in size, which can be configured using the `-Xss` JVM option.

If a thread calls too many nested methods or recurses too deeply, the stack may exceed this limit and trigger a `StackOverflowError`.


### Method Area (MetaSpace in Java8+)

Critical part of the JMM which stores per-class metadata necessary for the JVM to execute Java applications. Unlike the heap, which holds individual object instances, the method area is used to store class-level information that is shared across all instances of a class and all threads within the application.

It's actual implementation has evolved over different versions of the JVM, most notably with the transition from Permanent Generation (PermGen) to Metaspace in Java 8.

- It stores class metadata, such as the class structure, method information, and the constant pool.
- Static variables are now stored in the heap. This is a change from older Java versions (pre-Java 8), where they were stored in a special area of the heap called the Permanent Generation (PermGen).
- In Java 8 and later, the actual data for static variables is allocated on the heap, even though the class metadata (which includes a reference to the static variable) is in Metaspace

#### PermGen (Prior to Java 8)

Method area was physically implemented in a fixed-size memory region called the Permanent Generation (PermGen). PermGen resided in the heap and had to be explicitly sized using JVM flags like, `-XX:PermSize` & `-XX:MaxPermSize`

Challenges with fixed nature of PermGen:
- Class metadata exceeding PermGen space, leading to `java.lang.OutOfMemoryError: PermGen space`, especially in applications that dynamically load many classes.
- Since PermGen was part of heap, its memory management was limited and difficult to tune correctly.

#### MetaSpace (Post Java 8)

JVM replaced PermGen with a new memory region called Metaspace. Unlike PermGen, Metaspace is not part of the Java heap; it is allocated from native memory, which allows it to grow dynamically as needed (limited by system capability)

Improvements:
- No longer need to worry about sizing PermGen manually.
- Reduced risk of OutOfMemoryError: PermGen space
- Class metadata is handled more flexibly and efficiently.

While Metaspace can grow automatically, it is still constrained by available system memory. If too many classes are loaded (or not properly unloaded), Metaspace can still overflow, leading to a new error: `java.lang.OutOfMemoryError: Metaspace`

To control Metaspace usage, the JVM provides the following flags: `-XX:MetaspaceSize` & `-XX:MaxMetaspaceSize`

![heap-stack](../assets/images/JMM-2.png)

### Program Counter (PC Register)

Very small but essential component of the JMM. While other memory areas like the heap and stack handle data and objects, the PC register is concerned with instruction-level control: keeping track of which bytecode instruction a thread should execute next.

Each thread in the JVM has its own private Program Counter register. This isolation is necessary because the JVM supports multithreaded execution, where multiple threads can run independently and simultaneously. By maintaining a separate PC for each thread, the JVM ensures that threads do not interfere with each other’s execution flow.

This allows the JVM to resume execution from the correct point after:
 - A method call
 - A branch (e.g., if/else, loop)
 - An exception handler
 - A thread context switch

Internally, the PC register helps drive the **JVM execution engine**, which fetches the bytecode instruction pointed to by the PC, decodes it, and then executes it.

PC register is not something developers interact with directly, but it becomes visible in several situations:
- Stacktraces during Exception Handling logs
- Debugging tools, profiler & breakpoints

PC Register is so low-level and lightweight, so does not require GC or tuning, and it has no configurable size or visibility at the language level. However, it is essential for:
- enabling Java’s method execution model
- thread isolation
- exception reporting.

### Native Method Stack

Dedicated memory region in the JVM that supports the execution of native methods, methods written in languages other than Java, such as C or C++. These methods are typically called through the **Java Native Interface (JNI)**, which acts as a bridge between the JVM and native libraries.

When a native method is invoked, the JVM hands control over to the host operating system, which executes the method using the machine’s native call stack rather than the Java call stack. 

Each thread in the JVM has its own native method stack, separate from the standard Java stack used for executing bytecode. The JVM doesn’t manage the internals of this stack in the same way it manages Java method execution. Instead, it delegates the execution entirely to the native system runtime, allowing native code to execute as if it were part of a regular C/C++ program.

While Java handles heap and stack memory automatically, native methods executed via JNI operate in native memory, which is outside the JVM’s control. Although often overlooked, native memory leaks are one of the hardest issues to detect in Java applications.

These risks make it essential to use JNI and native code sparingly, and only when absolutely necessary.




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



