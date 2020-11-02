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

This pattern is used in cases when we have a superclass with multiple sub-classes and based on input, we need to return one of the sub-class. This pattern takes out the responsibility of the instantiation of a class from the client program to the factory class.

![](../assets/images/DP-2.png)

- Used when the exact types and dependencies of the objects your code should work with is not known beforehand.
- Factory pattern makes our code more robust, less coupled and easy to extend
- Concepts:
  - Factory class for creation of object of desired type.

Real-life example is a Logistic App, initially, a single transport mechanism was of land so Trucks were used & all logistics function were dependent on Truck class but later on while addition of sea & air transport, this poses problems in scaling the app.

![](../assets/images/DP-3.png)

![](../assets/images/DP-4.png)

![](../assets/images/DP-5.png)

`Button.java`

```java
public interface Button {
    void render();
    void onClick();
}
```

`WindowsButton.java`

```java
public class WindowsButton implements Button {
    public void render() {}
    public void onClick() {}
}
```

`HTMLButton.java`

```java
public class HtmlButton implements Button {
    public void render() {}
    public void onClick() {}
}
```

`Dialog.java` : Factory class

```java
public abstract class Dialog {
    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }

    public abstract Button createButton();
}

```

`WindowsDialog.java`

```java
public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
```

`HtmlDialog.java`

```java
public class HtmlDialog extends Dialog {
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
```

`Runner.java`

```java
public class Runner {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (System.getProperty("usage.property").equals("browser"))
            dialog = new HtmlDialog();
        else
            dialog = new WindowsDialog();
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
```

#### Abstract factory

This pattern lets us produce families of related objects without specifying their concrete classes.

Real-life example is a Furniture making store, which sells Chair, Sofa & Table of 2 styles: Modern & Wooden.

- Concepts
  - Abstract product basically an abstract class for the product like Chair, Sofa, Table etc.
  - Concrete product is inherited from abstract product like WoodenChair, WoodenSofa, ModernChair etc.
  - Abstract factory basically an interface with a list of creation methods for all products like createSofa, createChair, createTable etc. Eg: FurnitureFactory. These methods must return abstract products.
  - Concrete factory is inherited from Abstract factory & returns concrete products from the implementations of methods of abstract factory.  Eg: WoodenFurnitureFactory must implement createSofa & return WoodenSofa etc.

![](../assets/images/DP-6.png)

#### Builder

- Use the Builder pattern to get rid of a “telescopic constructor”.

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
