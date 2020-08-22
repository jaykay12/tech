---
layout: post
title: OOPs Concepts of Java
categories: [Java, Core]
---

`Object-Oriented Programming` is a methodology or paradigm to design a program using classes and objects. It simplifies software development and maintenance by providing various concepts.

The popular object-oriented languages are Java, C#, PHP, Python, C++, etc. The main aim is to implement real-world entities, for example, object, classes, abstraction, inheritance, polymorphism, etc.        

`Simula` is considered the first object-oriented programming language.      

The programming paradigm where everything is represented as an object is known as a truly object-oriented programming language.
`Smalltalk` is considered the first truly object-oriented programming language.

![OOPS](../assets/images/JO-1.png)

## Java OOPS

- Object
- Class
- Inheritance
- Polymorphism
- Abstraction
- Encapsulation
- Coupling
- Cohesion
- Association
- Aggregation
- Composition


**Object:**
- Any entity that has state and behavior
- For example, chair, pen, table, keyboard, bike, etc.
- It can be physical or logical.
- Object can be defined as an instance of a class.
- Object contains an address and takes up some space in memory
- Objects can communicate without knowing the details of each other's data or code. The only necessary thing is the type of message accepted and the type of response returned by the objects.

**Class:**
- Collection of objects
- It is a logical entity.
- Class can also be defined as a blueprint from which you can create an individual object.
- Class doesn't consume any space.

**Inheritance:**
- When one object acquires all the properties and behaviors of a parent object, it is known as inheritance.
- It provides code reusability.
- It is used to achieve runtime polymorphism.
- Represents the `is-a relationship`.

**Polymorphism:**
- If one task is performed in different ways, it is known as polymorphism.
- In Java, we use method overloading and method overriding to achieve polymorphism.

**Abstraction:**
- Hiding internal details and showing functionality is known as abstraction.
- For example: phone call, we don't know the internal processing.
- In Java, we use abstract class and interface to achieve abstraction.

**Encapsulation:**
- Binding (or wrapping) code and data together into a single unit are known as encapsulation.
- For example: a capsule, it is wrapped with different medicines.
- A java class is the example of encapsulation.
- Java bean is the fully encapsulated class because all the data members are private here.

**Coupling:**
- Coupling refers to the knowledge or information or dependency of another class.
- If a class has the details information of another class, there is `strong` coupling.
- In Java, we use private, protected, and public modifiers to display the visibility level of a class, method, and field
- You can use interfaces for the `weak` coupling because there is no concrete implementation.

**Cohesion:**
- Cohesion refers to the level of a component which performs a single well-defined task.
- A single well-defined task is done by a `highly` cohesive method. Eg: java.io
- The `weakly` cohesive method will split the task into separate parts. Eg: java.util

**Association:**
- Association represents the relationship between the objects.
- There can be four types of association between the objects:
   - <ins>One to One</ins> : One country can have one prime minister
   - <ins>One to Many</ins> : a prime minister can have many ministers
   - <ins>Many to One</ins> : many MP's can have one prime minister
   - <ins>Many to Many</ins> : many ministers can have many departments


1. **Aggregation:**
    - Way to achieve Association
    - Represents the relationship where one object contains other objects as a part of its state.
    - Represents the `weak` relationship between objects
    - Termed as a `has-a relationship`.
    - Another way to reuse objects.


2. **Composition:**
    - way to achieve Association
    - Represents the relationship where one object contains other objects as a part of its state.
    - There is a `strong` relationship between the containing object and the dependent object.
    - If you delete the parent object, all the child objects will be deleted automatically.
    - Containing objects do not have an independent existence

|Object-Oriented|Procedure-Oriented|
|---|---|
|Development and maintenance easier|Not easy to manage if code grows as project size increases|
|Java,C++|C|
|provides data hiding|global data can be accessed from anywhere|
|![](../assets/images/JO-4.png)|![](../assets/images/JO-3.png)|
|provides the ability to simulate real-world event much more effectively||

> Object-based programming language follows all the features of OOPs except Inheritance. JavaScript and VBScript are examples of object-based programming languages.

 ---

**Java Classes :**

**Java Inheritence :**

**Java Abstraction :**

**Java Polymorphism :**

**Java Encapsulation :**

**Java Key Terms :**
