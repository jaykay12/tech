---
layout: post
title: Advanced Java - V - Multithreading
categories: [Java, Core]
---

- Multiprocessing and multithreading, both are used to achieve multitasking.

- Multithreading in Java is a process of executing multiple threads simultaneously.

- Java Multithreading is mostly used in games, animation, etc.

### Multitasking

  Process of executing multiple tasks simultaneously.
  We use multitasking to utilize the CPU.

  - <ins>**Process-based Multitasking**</ins>
      - `Multiprocessing`

      - Each process has an address in memory. In other words, each process allocates a separate memory area.

      - A process is heavyweight.

      - Cost of communication between the process is high.

      - Switching from one process to another requires some time for saving and loading registers, memory maps, updating lists, etc.


  - <ins>**Thread-based Multitasking**</ins>
      - `Multithreading`

      - A thread is a lightweight sub-process, the smallest unit of processing.

      - Threads share the same address space.

      - Cost of communication between the thread is low.

      - It doesn't block the user because threads are independent and you can perform multiple operations at the same time.

      - Threads are independent, so it doesn't affect other threads if an exception occurs in a single thread.

![OS](../assets/images/JA-18.png)

## Java Multithreading

  - <ins>**Life cycle of a Thread**</ins>
    - The life cycle of the thread in java is controlled by JVM.
    - A thread can be in one of the five states:
       - `new`: Instance created of Thread class but start() not called

       - `runnable`: start() is called but thread scheduler hasn't selected it to be the running thread.

       - `running`: Once thread scheduler has selected it to be the running thread.

       - `non-runnable`: when the thread is still alive, but is currently not eligible to run

       - `terminated`: run() has exited.

  ![LifeCycle](../assets/images/JA-19.png)

  - <ins>**Thread Scheduler**</ins>
    - Part of the JVM that decides which thread should run.

    - No guarantee that which runnable thread will be chosen to run by the thread scheduler.

    - Only one thread at a time can run in a single process.

    - Mainly uses preemptive or time slicing scheduling to schedule the threads.
       - Preemptive scheduling: The highest priority task executes until it enters the waiting or dead states or a higher priority task comes into existence.

       - Time slicing scheduling: A task executes for a predefined slice of time and then reenters the pool of ready tasks. The scheduler then determines which task should execute next, based on priority and other factors.

  - <ins>**Creating Threads in Java**</ins>
    - `Thread class`
       - Implements Runnable interface
       - Useful methods:
          - _public void start()_
          - _public void run()_
          - _public void sleep(long miliseconds)_
          - _public void join(long miliseconds)_
          - _public Thread currentThread()_
          - _public Thread.State getState()_
          - _public void setDaemon(boolean b)_
          - _public void interrupt()_

    - `Runnable interface`
        - Should be implemented by any class whose instances are intended to be executed by a thread.
        - Only 1 method: _public void run()_

    - Starting a thread: start() method of Thread class is used to start a newly created thread. Following tasks are done:
       - New thread is started with new callstack.
       - thread state moves from new -> runnable.
       - On getting scheduled by Thread scheduler, JVM internally calls the run() method and state moves from runnable -> running.

    - Achieving Multithreading using `Thread class`

    ```java
    class SimpleThread1 extends Thread {  
       public void run() {  
          System.out.println("Task 1");  
       }  
    }  

    class SimpleThread2 extends Thread {  
       public void run(){  
          System.out.println("Task 2");  
       }  
    }  

    class ThreadClassUsage {  
       public static void main(String args[]) {  
        SimpleThread1 t1 = new SimpleThread1();  
        SimpleThread2 t2 = new SimpleThread2();  

        t1.start();  
        t2.start();  
       }  
    }
    ```

    - Creating a thread using `Runnable interface`

    ```java
    class RunnableUsage implements Runnable {  
        public void run() {  
            System.out.println("Thread Running");  
        }  

        public static void main(String args[]) {  
            RunnableUsage r1 = new RunnableUsage();  
            Thread t1 = new Thread(r1);  
            t1.start();  
        }  
    }
    ```

    - Important points:
        - After starting a thread, it can never be started again. If one does so, an `IllegalThreadStateException` is thrown.

        - If directly run() is called, instead of JVM invoking it implicitedly after start() then, the run() method goes onto the current call stack rather than at the beginning of a new call stack. There will be no context-switching as they will be treated as normal object not thread object.


  - <ins>**Sleeping, Joining, Naming and Setting priority of Threads**</ins>
    - Default priority of a thread is 5 (NORM_PRIORITY). The value of MIN_PRIORITY is 1 and the value of MAX_PRIORITY is 10.

    ```java
    class SampleThread extends Thread {  
       public void run() {  
          for(int i=1;i<=5;i++){  
             try{  
              Thread.sleep(500);  
             } catch(Exception e) {System.out.println(e); }  
          System.out.println(i);  
          }  
     }

        public static void main(String args[]) {  
           SampleThread t1 = new SampleThread();  
           SampleThread t2 = new SampleThread();  
           SampleThread t3 = new SampleThread();  
           t1.start();  
           try {  
             t1.join();  
           } catch(Exception e){ System.out.println(e); }

           t1.setName("MainThread");
           t2.setPriority(Thread.MIN_PRIORITY);  
           t3.setPriority(Thread.MAX_PRIORITY);

           t2.start();  
           t3.start();  
         }  
    }
    ```

  - <ins>**Daemon Threads**</ins>
    - Is a service provider thread that provides services to the user thread.

    - Its life depend on the mercy of user threads i.e. when all the user threads dies, JVM terminates this thread automatically.

    - There are many java daemon threads running automatically e.g. gc, finalizer etc.

    - It provides services to user threads for background supporting tasks. It has no role in life than to serve user threads.

    - It is a low priority thread.

    - _public void setDaemon(boolean status)_ & _public boolean isDaemon()_ are 2 methods.

  - <ins>**Thread Pool**</ins>
    - Java Thread pool represents a group of worker threads that are waiting for the job and reuse many times.

    - Improves performance as it saves time because there is no need to create new thread.

    - It is used in Servlet and JSP where container creates a thread pool to process the request.

    `WorkerThread.java`
    ```java
    class WorkerThread implements Runnable {  
        private String message;  
        public WorkerThread(String s) {  
            this.message = s;  
        }

         public void run() {  
            System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);  
            processmessage();
            System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name  
        }

        private void processmessage() {  
            try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }  
        }  
    }
    ```

    `ThreadPoolExample.java`
    ```java
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    public class TestThreadPool {  
         public static void main(String[] args) {  
            ExecutorService executor = Executors.newFixedThreadPool(5);  
            for (int i = 0; i < 10; i++) {
                Runnable worker = new WorkerThread("" + i);  
                executor.execute(worker);
              }  
            executor.shutdown();  
            while (!executor.isTerminated()) {   }  

            System.out.println("Finished all threads");  
        }  
    }  
    ```

    `OUTPUT`
    ```bash
    pool-1-thread-1 (Start) message = 0
    pool-1-thread-2 (Start) message = 1
    pool-1-thread-3 (Start) message = 2
    pool-1-thread-5 (Start) message = 4
    pool-1-thread-4 (Start) message = 3
    pool-1-thread-2 (End)
    pool-1-thread-2 (Start) message = 5
    pool-1-thread-1 (End)
    pool-1-thread-1 (Start) message = 6
    pool-1-thread-3 (End)
    pool-1-thread-3 (Start) message = 7
    pool-1-thread-4 (End)
    pool-1-thread-4 (Start) message = 8
    pool-1-thread-5 (End)
    pool-1-thread-5 (Start) message = 9
    pool-1-thread-2 (End)
    pool-1-thread-1 (End)
    pool-1-thread-4 (End)
    pool-1-thread-3 (End)
    pool-1-thread-5 (End)
    Finished all threads
    ```

  - <ins>**Thread Group**</ins>
    - Java provides a convenient way to group multiple threads in a single object.

    - Using this, we can suspend, resume or interrupt group of threads by a single method call.

    - Java thread group is implemented by `java.lang.ThreadGroup class`

    - A thread group can also include the other thread group.

    - 2 constructors for creating thread group: _ThreadGroup(String name)_ & _ThreadGroup(ThreadGroup parent, String name)_

    - Useful methods:
        - _void checkAccess()_
        - _int activeCount()_
        - _void destroy()_
        - _int getMaxPriority()_
        - _String getName()_
        - _void interrupt()_
        - _void list()_

    ```java
    public class ThreadGroupExample implements Runnable {  
        public void run() {  
              System.out.println(Thread.currentThread().getName());  
        }

        public static void main(String[] args) {  
            ThreadGroupExample runnable = new ThreadGroupExample();  
            ThreadGroup tg1 = new ThreadGroup("Jalaz thread group");  
            Thread t1 = new Thread(tg1, runnable,"first");  
            t1.start();  
            Thread t2 = new Thread(tg1, runnable,"second");  
            t2.start();

            System.out.println("Thread Group Name: "+tg1.getName());  
            tg1.list();  

        }  
    }
    ```

  - <ins>**Garbage Collection**</ins>
     - In java, garbage means unreferenced objects.

     - Garbage Collection is process of reclaiming the runtime unused memory automatically.

     - We use free() function in C and delete() in C++. But, in java it is performed automatically. So, java provides better memory management.

     - There are 3 ways of unreferencing objects:
        - By nulling the reference
        - By assigning a reference to another
        - By anonymous object etc.

     - we can use finalise() or gc() for garbage collection.

     - Neither finalization nor garbage collection is guaranteed.    

  - <ins>**Java Runtime class**</ins>
     - Is used to interact with java runtime environment.

     - There is only one instance of `java.lang.Runtime class` available for one java application.

     - Provides methods to execute a process, invoke GC, get total and free memory etc.

     ```java
     public class RunTimeExample {  
          public static void main(String args[]) throws Exception {  
               Runtime r = Runtime.getRuntime();
               System.out.println("Total Memory: " + r.totalMemory());                                         // 100139008
               System.out.println("Free Memory: " + r.freeMemory());                                           // 99474824

               for(int i=0;i<10000;i++)
                  new MemoryTest();  

               System.out.println("After creating 10000 instance, Free Memory: "+r.freeMemory());              // 99310552
               System.gc();  
               System.out.println("After gc(), Free Memory: "+r.freeMemory());                                 // 100182832
          }  
     }
     ```

## Synchronization

### Callable & FutureTask

---
