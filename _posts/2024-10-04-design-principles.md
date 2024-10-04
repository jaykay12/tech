---
layout: post
title: Software Design Principles
categories: [Software Engineering, System Design]
---

SOLID is an acronym for 5 design principles that help software developers create object-oriented software that is maintainable, scalable, and flexible: 
- Single responsibility principle: A class should have only one reason to change. 
- Open-closed principle: Software entities should be open for extension but closed for modification. (Using Inheritance)
- Liskov substitution principle: Functions that use pointers or references to base classes must be able to use objects of derived classes. 
- Interface segregation principle: Clients should not be forced to depend upon interfaces that they do not use. 
- Dependency inversion principle: Depend upon abstractions, not concretes. It states that the high-level module must not depend on the low-level module, but they should depend on abstractions. (Allows for decoupling).


Robert C. Martin introduced SOLID principles in the early 2000s.

The SOLID principle helps in reducing tight coupling. Tight coupling means a group of classes are highly dependent on one another which should be avoided. Code is considered as a good code when it has loosely-coupled classes.
Loosely coupled classes minimize changes in your code, helps in making code more reusable, maintainable, flexible and stable.

## Single Responsibility Principle

“A class should have only one reason to change” which means every class should have a single responsibility or single job or single purpose in the system.
Furthermore, it should only have one reason to change.

**Benefits:**
1. Testing – A class with one responsibility will have far fewer test cases.
2. Lower coupling – Less functionality in a single class will have fewer dependencies.
3. Organization – Smaller, well-organized classes are easier to search than monolithic ones.

**General Example:**
In a bakery, baker is responsible for baking bread. The baker’s role is to focus on the task of baking bread, ensuring that the bread is of high quality, properly baked, and meets the bakery’s standards.
However, if the baker is also responsible for managing the inventory, ordering supplies, serving customers, and cleaning the bakery, this would violate the SRP.

**Practical Example:**

```java
public class Book {

    private String name;
    private String author;
    private String text;

    //constructor, getters and setters

    // methods that directly relate to the book properties
    public String replaceWordInText(String word, String replacementWord){
        return text.replaceAll(word, replacementWord);
    }

    public boolean isWordInText(String word){
        return text.contains(word);
    }
}

public class BookPrinter {

    // methods for outputting text
    void printTextToConsole(String text){
        //our code for formatting and printing the text
    }

    void printTextToAnotherMedium(String text){
        // code for writing to any other location..
    }
}
```

Here we developed a class that relieves the Book of its printing duties, but we can also leverage our BookPrinter class to send our text to other media.
Whether it’s email, logging, or anything else, we have a separate class dedicated to this one concern.


## Open/Closed Principle

“Software entities (classes, modules, functions, etc.) should be open for extension, but closed for modification” which means you should be able to extend a class behavior, without modifying it.

**General Example:**
Imagine we have class called PaymentProcessor that processes payments for an online store. Initially, the PaymentProcessor class only supports processing payments using credit cards. However, we want to extend its functionality to also support processing payments using PayPal.

Instead of modifying the existing PaymentProcessor class to add PayPal support, we create a new class called PayPalPaymentProcessor that extends the PaymentProcessor class. This way, the PaymentProcessor class remains closed for modification but open for extension, adhering to the Open-Closed Principle.

**Practical Example:**

```java
public class Guitar {
    private String make;
    private String model;
    private int volume;

    //Constructors, getters & setters
}

public class CoolGuitarWithFlames extends Guitar {
    private String flameColor;

    //constructor, getters + setters
}
```

By extending the Guitar class, we can be sure that our existing application won’t be affected.

## Liskov’s Substitution Principle

“Derived or child classes must be substitutable for their base or parent classes“. This principle ensures that any class that is the child of a parent class should be usable in place of its parent without any unexpected behavior.

If class A is a subtype of class B, we should be able to replace B with A without disrupting the behavior of our program.

**General Example:**
Rectangle have four sides. A rectangle’s height can be any value and width can be any value. A square is a rectangle with equal width and height. so, all properties of Rectangle class can be extended into the Square class.

**Practical Example:**
<Pending>

## Interface Segregation Principle

This is the first principle that applies to Interfaces instead of classes in SOLID and it is similar to the single responsibility principle. It states that “do not force any client to implement an interface which is irrelevant to them“. 

Focus on avoiding fat interface and give preference to many small client-specific interfaces. We should prefer many client interfaces rather than one general interface and each interface should have a specific responsibility.

**General Example:**

Suppose if you enter a restaurant and you are pure vegetarian. The waiter in that restaurant gave you the menu card which includes vegetarian items, non-vegetarian items, drinks, and sweets. 
In this case, as a customer, you should have a menu card which includes only vegetarian items, not everything which you don’t eat in your food. Here the menu should be different for different types of customers.

**Practical Example:**

Bad Case:
```java
public interface BearKeeper {
    void washTheBear();
    void feedTheBear();
    void petTheBear();
}
```

Happy Case:
```java
public interface BearCleaner {
    void washTheBear();
}

public interface BearFeeder {
    void feedTheBear();
}

public interface BearPetter {
    void petTheBear();
}

public class BearCarer implements BearCleaner, BearFeeder {
    public void washTheBear() {
        //I think we missed a spot...
    }

    public void feedTheBear() {
        //Tuna Tuesdays...
    }
}

public class CrazyPerson implements BearPetter {
    public void petTheBear() {
        //Good luck with that!
    }
}
```

## Dependency Inversion Principle

**General Example:**

**Practical Example:**
