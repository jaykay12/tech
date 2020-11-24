---
layout: post
title: Dive into Design Patterns
categories: [Java, Spring]
---

Design patterns - Solutions to some prominent & frequent problems, one faces while designing softwares.
  - "Evolved" not "Discovered".
  - Provides guidelines for solving a particular problem in particular way in some particular context. No strictness on implementation.
  - Using DPs, code becomes more flexible, reusable and maintainable.
  - Gang of Four (GoF) Design Patterns - standard 23 DPs.


3 different kinds of design patterns are there:
  - <ins>Creational Patterns</ins> : Deals with object creation.
  - <ins>Structural Patterns</ins> : Deals with the composition of objects & classes.
  - <ins>Behavioral Patterns</ins> : Focuses on interactions between objects.

Also a 4th category: <ins>J2EE Patterns</ins> which specifically deals with presentation tier.

## Creational Patterns

![creational-patterns](../assets/images/DP-8.png)

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

Produces families of related objects without specifying their concrete classes.

Real-life example is a Furniture making store, which sells Chair, Sofa & Table of 2 styles: Modern & Wooden.

- Concepts
  - Abstract product basically an abstract class for the product like Chair, Sofa, Table etc.
  - Concrete product is inherited from abstract product like WoodenChair, WoodenSofa, ModernChair etc.
  - Abstract factory basically an interface with a list of creation methods for all products like createSofa, createChair, createTable etc. Eg: FurnitureFactory. These methods must return abstract products.
  - Concrete factory is inherited from Abstract factory & returns concrete products from the implementations of methods of abstract factory.  Eg: WoodenFurnitureFactory must implement createSofa & return WoodenSofa etc.

![](../assets/images/DP-6.png)

#### Builder

Helps constructing complex objects in step-by-step manner, where all these objects differ in representations. Objects are having lot many attributes & having separate constructors for invoking these attributes for object creation is really cumbersome.

Basically solves the issues of Factory & Abstract Factory DP.

![builder-dp](../assets/images/DP-7.png)

Concepts
- Builder class is a static nested class inside the main object class.
- Builder class must have a public parameterized constructor with all mandatory data members.
- Builder class should have setters for all optional data members & should return Builder object.
- build() method is required which returns the final object to the runner.

`Mobile.java`
```java
public class Mobile {
  private String RAM;
  private String Size;
  private String Camera;
  boolean is4GEnabled;

  // GETTERS for these 5 private members

  private Mobile(MobileBuilder builder) {
    this.RAM = builder.RAM;
    this.Size = builder.Size;
    this.Camera = builder.Camera;
    this.is4GEnabled = builder.is4GEnabled;
  }

  public static class MobileBuilder {
    private String RAM;
    private String Size;
    private String Camera;
    boolean is4GEnabled;

    public MobileBuilder(String RAM, String Size) {
      this.RAM = RAM; this.Size = Size;
    }

    public MobileBuilder setCamera(String Camera) {
      this.Camera = Camera;
      return this;
    }

    public MobileBuilder setIs4GEnabled(boolean is4GEnabled) {
      this.is4GEnabled = is4GEnabled;
      return this;
    }

    public Mobile build() {
      return new Mobile(this);
    }
  }
}
```

`Runner.java`
```java
public class Runner {
    public static void main(String[] args) {
        Mobile mobile1 = new Mobile.MobileBuilder("4 GB", "6 inch").setCamera("12px").setIs4GEnabled(true).build();
        Mobile mobile2 = new Mobile.MobileBuilder("2 GB", "4.2 inch").setIs4GEnabled(false).build();
	}
}
```

#### Prototype

Generally, Prototype is a template of the actual object to be constructed.
- Useful when large number of instances of classes are required, which are all nearly same.
- Main advantage is the minimal instance creation process which is costly than the cloning process.
- This DP also supplements cases when we require exact copies of existing objects but don't want to be dependent on their classes.

**Issues with normal approach**
If we have to exactly copy an existing object natively, we create a new object of the same class, traverse all fields of existing object & copy values to the new object. Here, catch is that maybe some fields are private & couldn't be accessed/copied.

Concepts
- An interface/class implementing Cloneable having abstract clone() method declared.
- Prototype registry for holding the prominet prototypes.
- Separate Prototype models which inherits the base interface & implements clone() method accordingly.

`SolrCore.java `
```java
package tech.jaykay12.designpatterns.prototype;

public interface SolrCore extends Cloneable {
    public SolrCore clone() throws CloneNotSupportedException;
}
```

`Intent.java `
```java
package tech.jaykay12.designpatterns.prototype.model;

public class Intent implements SolrCore {
    private String name = null;

    @Override
    public Intent clone() throws CloneNotSupportedException {
        return (Intent) super.clone();
    }
}
```

`Mapping.java`
```java
package tech.jaykay12.designpatterns.prototype.model;

public class Mapping implements SolrCore {
    private String name = null;

    @Override
    public Mapping clone() throws CloneNotSupportedException {
        return (Mapping) super.clone();
    }
}
```

`Mcat.java`
```java
package tech.jaykay12.designpatterns.prototype.model;

public class Mcat implements SolrCore {
    private String name = null;

    @Override
    public Mcat clone() throws CloneNotSupportedException {
        return (Mcat) super.clone();
    }
}
```

`CoreDirectory.java`
```java
package tech.jaykay12.designpatterns.prototype;

public class CoreDirectory {
    public static class CoreName {
        public static final String INTENT = "intent";
        public static final String MCAT = "mcat";
        public static final String MAPPING = "kw-mapping";
    }

    private static java.util.Map<String , SolrCore> cores = new java.util.HashMap<>();

    static {
        cores.put(CoreName.INTENT, new Intent());
        cores.put(CoreName.MCAT, new Mcat());
        cores.put(CoreName.MAPPING, new Mapping());
    }

    public static SolrCore getInstance(final String s) throws CloneNotSupportedException {
        return ((SolrCore) cores.get(s)).clone();
    }
}
```

`Runner.java`
```java
public class Runner {
    public static void main(String[] args) {
        try {
            String mappingCore1  = SolrCore.getInstance(CoreName.MAPPING).toString();
            String intentCore1  = SolrCore.getInstance(CoreName.INTENT).toString();
            String mappingCore2  = SolrCore.getInstance(CoreName.MAPPING).toString();
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
```
---


## Structural Patterns

#### Adapter

#### Bridge

#### Composite

#### Decorator

#### Facade

#### Flyweight

#### Proxy

---

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
