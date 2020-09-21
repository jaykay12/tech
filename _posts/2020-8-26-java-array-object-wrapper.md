---
layout: post
title: Advanced Java - I - Major Concepts
categories: [Java]
---

## Java Array

  - <ins>**Introduction**</ins>
    - Contains elements of similar data type which are stored in a contiguous memory location.

    - Index-based, the first element of the array is stored at the 0th index and so on.

    - In Java, we can get the array length using the **length** member. In C/C++, we need to use the **sizeof** operator.

    - Java array inherits the `Object class`, and implements the `Serializable interface` as well as `Cloneable interfaces`.

    - We can store primitive values or objects in an array in Java.

    - Like C/C++, we can also create single dimentional or multidimentional arrays in Java.

    - Java provides the feature of anonymous arrays which is not available in C/C++.

    - <ins>**Advantages**</ins>:
        - Optimised code.

        - Retrieval and sorting is quite efficient.

        - Random access: Data can be accessed from any location using index.

    - <ins>**Disadvantages**</ins>:
        - Has fixed size and doesn't grow at runtime.

        - To tackle this, `Collections framework` is used.

    - Java array implements the `Cloneable interface`
        - clone() creates deep copy of the Java SD array which means it will copy the actual value.

        - clone() creates the shallow copy of the Java MD array which means it copies the references.

  - <ins>**Single Dimensional Array**</ins>

  ```java
    class SDArray {  
        public static void main(String args[]){  
            int a1[] = new int[3];                    // declaration and instantiation  
            a1[0]=10;                                 // initialization  
            a1[1]=20;  
            a1[2]=70;  

            int a2[] = {33,3,4,5};                    //declaration, instantiation and initialization

            for(int i=0;i<a1.length;i++)              // traversal using for loop
                System.out.println(a1[i]);

            for(int i:a2)                             // traversal using for-each loop
                System.out.println(i);  

        }
    }
  ```

  - <ins>**Anonymous Array**</ins>

  ```java
    public static void main(String args[]) {  
        printArray(new int[]{10,22,44,66});          // anonymous array
    }  
  ```

  - <ins>**Two Dimensional Array**</ins>

  ```java
    class DDArray {  
        public static void main(String args[]){  

            for(int i=0;i<3;i++) {                            // traversal
               for(int j=0;j<3;j++) {  
                  System.out.print(arr[i][j]+" ");  
               }  
               System.out.println();  
            }  
        }
    }  
  ```

  - <ins>Jagged Array</ins>

    If we are creating odd number of columns in a 2D array, it is known as a jagged array.

    ```java
      class JaggedArray {  
          public static void main(String[] args) {  

              int arr[][] = new int[3][];
              arr[0] = new int[3];  
              arr[1] = new int[4];  
              arr[2] = new int[2];  

              for (int i=0; i<arr.length; i++){  
                  for (int j=0; j<arr[i].length; j++){  
                      System.out.print(arr[i][j]+" ");  
                  }  
                  System.out.println();
              }  
          }  
      }  
    ```

### Object Class


  - The parent class of all the classes in java by default.

  - Beneficial if we want to refer any object whose type is unknown. Using upcasting this is achieved.

  - Important methods of Object class:

     - _public final Class getClass()_

     - _public int hashCode()_

     - _public boolean equals(Object obj)_

     - _protected Object clone() throws CloneNotSupportedException_

     - _public String toString()_

     - _protected void finalize()throws Throwable_

     - _public final void wait(long timeout)throws InterruptedException_

     - _public final void notify()_

### Object Cloning

   - Object cloning is a way to create exact copy of an object

   - The clone() method of Object class is used to clone an object.

   - The `java.lang.Cloneable` interface must be implemented by the class whose object clone we want to create. If we don't implement Cloneable interface, clone() method generates CloneNotSupportedException.

   - <ins>**Advantages**</ins>:

      - Saves the extra processing task for creating the exact copy of an object. `new` keyword will take a lot of processing time for the same.

      - Clone() is the fastest way to copy array.

      - No need to write lengthy and repetitive codes. Just use an abstract class with a 4- or 5-line long clone() method.

   - <ins>**Disadvantages**</ins>:

      - Object.clone() has some design issues.

      - For using the Object.clone() method, we have to change a lot of syntaxes to our code, like implementing a Cloneable interface, defining the clone() method and handling CloneNotSupportedException, and finally, calling Object.clone() etc.

      - Object.clone() is protected, so we have to provide our own clone() and indirectly call Object.clone() from it.

      - Object.clone() supports only shallow copying but we will need to override it if we need deep cloning.


  ```java
  class Student implements Cloneable {   
      String name;  

      Student(String name) {  
          this.name=name;  
      }  

      public Object clone() throws CloneNotSupportedException {  
          return super.clone();  
      }  

      public static void main(String args[]) {  
          try {  
          Student s1 = new Student("jalaz");  

          Student s2 = (Student)s1.clone();  

          System.out.println(s1.name);               // jalaz
          System.out.println(s2.name);               // jalaz

          } catch(CloneNotSupportedException c){}  

      }  
  }
  ```

### Wrapper Classes

  Provides the mechanism to convert primitive into object and object into primitive.

  Since J2SE 5.0, `autoboxing` and `unboxing` feature convert primitives into objects and objects into primitives automatically.

  Requirement of Wrapper class:

   - <ins>**Changing the value in Method**</ins>:

  Java supports only call by value. So, if we pass a primitive value, it will not change the original value. But, if we convert the primitive value in an object, it will change the original value.

   - <ins>**Serialization**</ins>:

  Objects are converted into streams for performing serialization.

   - <ins>**Synchronization**</ins>:

  Java synchronization works with objects in Multithreading.

   - <ins>**Collection Framework**</ins>:

  Works with objects only. All classes of the collection framework (ArrayList, LinkedList, Vector, HashSet, LinkedHashSet, TreeSet, PriorityQueue, ArrayDeque, etc.) deal with objects only.


  There are 8 wrapper classes in Java defined in `java.lang` package.
   - boolean - Boolean
   - char - Character
   - byte - Byte
   - short - Short
   - int - Integer
   - long - Long
   - float - Float
   - double - Double


   ```java
  public class WrapperExample {  
     public static void main(String args[]) {  

     int a = 20;  
     Integer i = Integer.valueOf(a);                     // converting int into Integer explicitly  
     Integer j = a;                                      // autoboxing, compiler handles rest
     System.out.println(a+" "+i+" "+j);                  // 20 20 20

     Integer a = new Integer(3);    
     int i = a.intValue();                               // converting Integer to int explicitly  
     int j = a;                                          // unboxing, compiler handles implictedly
     System.out.println(a+" "+i+" "+j);                  // 3 3 3

     }
  }
   ```

### Command line Args

  Java command-line argument is an argument i.e. passed at the time of running the java program.

  ```java
  class CommandLineExample {  
      public static void main(String args[]) {  
          System.out.println("Passed CLA is: "+args[0]);  
      }  
  }  
  ```

  `OUTPUT`

  ```bash
  > Compile using: javac CommandLineExample.java
  > Run using: java CommandLineExample Jalaz
  > prints: "Passed CLA is: Jalaz"
  ```

### Math Class

  `java.lang.Math` class contains various methods for performing basic numeric operations such as the logarithm, cube root, and trigonometric functions etc.
