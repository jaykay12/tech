---
layout: post
title: Java 8 - Concepts
categories: [Design Patterns]
---

Java Launched multiple features with JDK 1.8, facilitating & making Java more closer to user as compared with languages like python etc.

## Lambda Expressions

`Inteface` basically defines the contracts. Any class which implements that interface needs to override those abstract methods with proper implementations.

```java
// Before Java 8: (Using Inner Classes)
PersonInterface person = new PersonInterface() {  
    @Override 
    public void how() {
        System.out.println("Before Java 8 without lambda");
    }  
};

// Java 8: (Using lambda expression)
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

`Be aware of the scope of variable you are using in the lambda expression`

Inner class -> creates a new scope. We can hide local variables from the enclosing scope by instantiating new local variables with the same names. We can also use the keyword this inside our inner class as a reference to its instance.

Lambda expressions -> however, work with enclosing scope. We can’t hide variables from the enclosing scope inside the lambda’s body. In this case, the keyword this is a reference to an enclosing instance.

## Optional

Optional class in Java 8 is a container object which is used to contain a value that might or might not be present. It was introduced as a way to help reduce the number of NullPointerExceptions that occur in Java code.

One of the key benefits of using `Optional` is that it forces us to handle the case where the value is absent. This means that we are less likely to miss important checks in the code and reduces the risk of NullPointerException. If a value is not present, we can either provide a default value or throw an exception.

Optional comes along with a strong move towards `functional programming` in Java.

### Optional class methods

Creation Methods|Params
---|---
Optional.ofNullable()|
Optional.of()|
Optional.empty()|

Usage Methods|Params
---|---
Optional.get()|
Optional.isEmpty()|
Optional.isPresent()|
Optional.orElse()|
Optional.orElseGet()|
Optional.orElseThrow()|


#### Misuse of Optionals


## Functional Interfaces

An interface which has only one abstract method is called functional interface. 
Java provides an anotation `@FunctionalInterface`, which is used to declare an interface as functional interface.

