---
layout: post
title: Java 8 - Concepts
categories: [Design Patterns]
---

Java Launched multiple features with JDK 1.8, facilitating & making Java more closer to user as compared with languages like python etc.

## Lambda Expressions

`Inteface` basically defines the contracts. Any class which implements that interface needs to override those abstract methods with proper implementations.

```java
// Before Java 8
PersonInterface person = new PersonInterface() {  
    @Override 
    public void how() {
        System.out.println("Before Java 8 without lambda");
    }  
};

// Java 8: Using lambda expression
PersonInterface p2 = () -> { System.out.println("Lambda implemented"); };
```

Lambdas is an anonymous (no name) function where we do not need to define the data type of input parameters & does not have a return type. Lambda expression in java implements the functional interface and can be treated as any other java object. It can be used to create threads, comparators and can be used to add event Listeners.

Lambda Expressions implements only the abstract function of the interface.
We can't define a lambda expression over an interface having more than 1 abstract function. If we do so, it results in an error.

```java
interface FuncInterface {
    // An abstract function
    void abstractFun(int x);

    // A non-abstract function
    default void normalFun() {
       System.out.println("Hello Jalaz..!!");
    }
}

class Test {
    public static void main(String args[]) {
        
        FuncInterface fobj = (int x) -> System.out.println(2*x);
        fobj.abstractFun(5);
    }
}
```

Lambda expressions are just like functions and they accept parameters just like functions. 
3 Lambda Expression Parameters as mentioned below:

- Zero Parameter
- Single Parameter
- Multiple Parameters

```java
interface Welcomable {  
    public String welcomeMsg(String name);
}  
  
public class Runner{  
    public static void main(String[] args) {  
      
        Welcomable w1Admin = (name)-> {  return "Hello, Admin: "+name;  };  
        System.out.println(w1Admin.welcomeMsg("Jalaz"));  
          
        Welcomable w2User= name -> {  return "Hello, User: "+name;  };  
        System.out.println(w2User.welcomeMsg("Jalaz"));  
        
        System.out.println(guestLogin(w2User));  
    }  
    
    public static String guestLogin(Welcomable wImpl) {
        return wImpl.welcomeMsg("Guest");
    }
}
```
```bash
Hello, Admin: Jalaz
Hello, User: Jalaz
Hello, User: Guest
```

Benefits:
- provides the implementation of Functional interface.
- less coding.
- can be passed around as if it was an object and executed on demand.

Drawbacks:
- Java lambda functions can be only used with functional interfaces.
- they lack names and documentation, meaning that the only way to know what they do is to read the code.
- Be aware of the scope of variable you are using in the lambda expression.

## Optional


## Functional Interfaces

An interface which has only one abstract method is called functional interface. 
Java provides an anotation `@FunctionalInterface`, which is used to declare an interface as functional interface.

