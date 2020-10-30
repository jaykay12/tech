---
layout: post
title: Dive into Design Patterns
categories: [Java, Spring]
---

Design patterns are the solutions to some prominent & frequent problems, one faces while designing softwares.
  - These solutions are "evolved" rather than being "discovered".
  - Design patterns provides guidelines to solve a particular problem in a particular way in some particular context while keeping no check on implementation.
  - Using these, code can be made more flexible, reusable and maintainable.
  - The standard 23 Design Patterns are known as Gang of Four (GoF) Design Patterns.


3 different kinds of design patterns are there:
  - Creational Patterns : Deals with object creation.
  - Structural Patterns : Deals with the composition of objects & classes.
  - Behavioral Patterns : Focuses on interactions between objects.

There are also 4th category: J2EE Patterns which specifically deals with presentation tier.

## Creational Patterns

#### Singleton

This pattern puts restrictions on the instantiation of classes & ensures that only 1 instance of the class is available in the JVM at any point of time.
  - Used for logging, thread pools, driver objects and caching.
  - Used in core java classes like `java.lang.RunTime` etc.
  - Concepts:
    - Private constructor for restricting the object creation from outside class.
    - The only single instance of the class is a private static variable.
    - Public static method for exposing this only single instance to the outside world.


  ![](../assets/images/DP-1.png)

  ```java
public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }
}
  ```

But, this approach fails for multi-threading environment. There are chances that 2 or more threads will get the different instances of the same singleton class.

```java
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton(){}

    public static synchronized ThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
```  

#### Factory

#### Abstract factory

#### Builder

#### Prototype



## Structural Patterns

#### Adapter

#### Bridge

#### Composite

#### Decorator

#### Facade

#### Flyweight

#### Proxy

## Behavioral Patterns

#### Chain of responsibility

#### Command

#### Interpreter

#### Iterator

#### Mediator

#### Memento

#### Observer

#### State

#### Strategy

#### Template method

#### Visitor

https://howtodoinjava.com/gang-of-four-java-design-patterns/
https://www.journaldev.com/1827/java-design-patterns-example-tutorial
https://refactoring.guru/design-patterns/java

---

## Miscellaneous Design Patterns

#### DAO Design Pattern

#### Dependency Injection Pattern

#### MVC Pattern
