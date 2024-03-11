---
layout: post
title: Advanced Java - V - Multithreading & Concurrency - Part 2
categories: [Java]
---

### Synchronous-Asynchronous & Blocking-NonBlocking

### Concepts related to Asychronous Programming

We can create threads using 2 methods in Java:
 - Extending `Thread class` which implements `Runnable interface`
 - Implementing `Runnable interface`

<ins>**Callable**</ins>

 - One feature lacking in  `Runnable interface` is that a thread can't return result when it terminates, i.e. when run() completes. For supporting this feature, the `Callable interface` is present in Java.

 - For implementing `Runnable`, the run() method needs to be implemented which does not return anything, while for a `Callable`, the call() method needs to be implemented which returns a result on completion.

 - A thread can’t be created with a Callable, it can only be created with a Runnable.

<ins>**Future**</ins>

 - When the call() method completes, answer must be stored in an object known to the main thread, so that the main thread can know about the result that the thread returned.
For this, a Future object is used.

 - Future is an object that holds the result – it may not hold it right now, but it will do so in the future (once the Callable returns).

 - Useful methods:
    - _public boolean cancel(boolean mayInterrupt)_
    - _public Object get() throws InterruptedException, ExecutionException_
    - _public boolean isDone()_
  
    - 

`Callable` is similar to Runnable, in that it encapsulates a task that is meant to run on another thread, whereas a `Future` is used to store a result obtained from a different thread.

To create the thread, a `Runnable` is required. To obtain the result, a `Future` is required.

</ins>**FutureTask**</ins>

The Java library has the concrete type `FutureTask`, which implements `Runnable` and `Future`, combining both functionality conveniently.

```java
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class CallableExample implements Callable {
    public Object call() throws Exception {
        Integer randomNumber = new Random().nextInt(5);
        Thread.sleep(randomNumber * 1000);
        return randomNumber;
    }
}

public class CallableFutureTest{
    public static void main(String[] args) throws Exception {
        FutureTask[] randomNumberTasks = new FutureTask[5];

        for (int i = 0; i < 5; i++) {
            Callable callable = new CallableExample();
            randomNumberTasks[i] = new FutureTask(callable);
            Thread t = new Thread(randomNumberTasks[i]);
            t.start();
        }

        for (int i = 0; i < 5; i++)
            System.out.println(randomNumberTasks[i].get());
    }
}
```


</ins>**Completable Future**</ins>

Implementation of `Future` that provides method for defining & composing asynchronous tasks.

- `thenApply()` -> for chaining multiple tasks
- `allof()` -> wait for multiple tasks to complete

## Executor Framework in Java

Executor Framework (Introduced from Java 5) is used to run the Runnable objects without creating new threads every time and mostly re-using the already created threads.
This framework have bunch of components that are used for managing worker threads efficiently.
Implementation of the `Producer-Consumer` pattern.

<img src="../assets/images/JM-6.png" width="50%">

For using executor framework, we require some thread pool, which executes the task when submitted to it.

<img src="../assets/images/JM-7.png" width="80%">

Few important factory methods provided in the Executors Class that are used to create ThreadPools of worker threads:

```java
// thread pool of single thread.
// used to execute tasks sequentially.
ExecutorService executor = Executors.newSingleThreadExecutor();

// thread pool of a fixed number of threads.
// uses Blocking Queue for handling additional tasks.
ExecutorService fixedPool = Executors.newFixedThreadPool(2);

// thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
// uses SynchronousQueue queue.
ExecutorService executorService = Executors.newCachedThreadPool();

// used when we have a task that needs to be run at regular intervals or if we wish to delay a certain task.
ScheduledExecutorService scheduledExecService = Executors.newScheduledThreadPool(1);
```

</ins>**ThreadPoolExecutor**</ins>

</ins>**ForkJoinPool**</ins>

</ins>**WorkStealingPool**</ins>

</ins>**FixedThreadPool**</ins>

</ins>**CachedThreadPool**</ins>

</ins>**SingleThreadPool**</ins>


