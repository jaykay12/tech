---
layout: post
title: Fundamentals of Java
categories: [Java, Core]
---

**Variables :**
- A container which holds the value while the Java program is executed.
- Basically a name of memory location.
- Name of reserved area allocated in memory
- Each variable has to be assigned with a data type.
- Java supports 3 types of variables:

   - local variable:
      - variable declared inside the body of a method
      - this variable can be used only inside that method & other methods of class aren't even aware about its existence.
      - this variable can never be declared `static`.

   - instance variable
      - variable declared inside the class but outside the body of the method
      - value is instance specific and is not shared among instances.

   - static variable
      - variable which is declared as `static`
      - Memory allocation for static variable happens only once when the class is loaded in the memory.
      - single copy can be created and shared among all the instances of the class.

```java
    class Tech {  
        int data = 50;            //instance variable  
        static int m = 100;       //static variable  
        void method() {  
            int n = 90;           //local variable  
        }  
    }
```

`int a = 10; float f = a; sout(f);` provides 10.0 signifies <ins>Widening</ins>
`float f = 10.0; int a = (int)f; sout(a);` provides 10 signifies <ins>Narrowing/Typecasting</ins>     

**Data Types :**
Specify the different sizes and values that can be stored in the variable.

> Java is a statically-typed programming language. It means, all variables must be declared completely with name & data type before its use.

![Java Data Types](../assets/images/JF-1.png)

> Java uses Unicode system not ASCII code system.
> The \u0000 is the lowest range of Unicode system

**Operators :**

##Control Statements

**Conditionals :**

**Iterations :**
