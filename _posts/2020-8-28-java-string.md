---
layout: post
title: Advanced Java - II - Java String
categories: [Java]
---

`String` is an object that represents sequence of char values.

An array of characters works same as Java string.

```java
char[] ch = {'j','a','l','a','z'};  
String s1 = new String(ch);
String s2 = "jalaz";
```

`java.lang.String` class provides a lot of methods to perform operations on strings.
   - Implements `Serializable`, `Comparable` and `CharSequence` interfaces.
   - Used to create a string object.
   - s1 is created using "new" keyword and s2 is created using "string literal".

String objects are stored in a special memory area - `string constant pool`.

Each time a string literal is created, JVM checks the `string constant pool` first. If the string already exists in the pool, a reference to the pooled instance is returned. If the string doesn't exist in the pool, a new string instance is created and placed in the pool.

![String](../assets/images/JA-10.JPG)

## Immutable String

The `CharSequence` interface is used to represent the sequence of characters.

`String`, `StringBuffer` and `StringBuilder` classes implement it.
In Java, strings can be created using these three classes.

 - Java String is immutable.
 - For mutable strings, we use StringBuffer and StringBuilder classes.


Immutable simply means unmodifiable or unchangeable.
Once string object is created its data or state can't be changed but a new string object is created.

This is for the reason that java uses the concept of string literal and stores them all in `string constant pool`. Suppose there are 5 reference variables, all refers to one object "jalaz". If one reference variable changes the value of the object, it will be affected to all the reference variables.

## String Comparision

String in java is compared on the basis of content and reference. 3 prominent ways of achieving this:

- <ins>**equals() method**</ins>
    - Used in authentication.
    - Compares the original content of the string. It compares values of string for equality.
    - 2 variations are:
       - _public boolean equals(Object another)_
       - _public boolean equalsIgnoreCase(String another)_


- <ins>**== operator**</ins>
    - Used for reference matching.
    - Compares references not values.


- <ins>**compareTo() method**</ins>
    - Used in sorting.
    - Compares values lexicographically and returns an integer value that describes if first string is less than, equal to or greater than second string.


## String Concatenation

Forms a new string that is the combination of multiple strings. 2 ways for achieving this:

- <ins>**+ operator**</ins>
    - Also called string concatenation operator.
    - _String s = "Jalaz"+" Kumar";_ is converted into _String s = (new StringBuilder()).append("Jalaz").append(" Kumar).toString();_ by the JVM compiler.


- <ins>**concat() method**</ins>
    - Achieved using _String s3 = s1.concat(s2);_  


```java
  String s1 = "Jalaz";  
  System.out.println(s1.toUpperCase());          // JALAZ  
  System.out.println(s1.toLowerCase());          // jalaz
  String s2 = "    Jalaz  "
  System.out.println(s2.trim());                 // Jalaz
  System.out.println(s1.startsWith("Ja"));       // true  
  System.out.println(s1.endsWith("n"));          // false
  System.out.println(s1.charAt(0));              // J    
  System.out.println(s1.length());               // 5  
  String s3 = "Java is a programming language. Java is a platform. Java is an Island.";    
  String s3New = s3.replace("Java","Kava");    
  System.out.println(s3New);                     // Kava is a programming language. Kava is a platform. Kava is an Island.

  String s4 = "what do you know about me";  
  System.out.println(s4.contains("do you know"));    // true
  String s5 = "ABCDEFG";  
  byte[] barr = s5.getBytes();                       // 65 66 67 68 69 70 71

  String s6 = "";  
  String s7 = "jalazkumar";  
  System.out.println(s6.isEmpty());                  // true  
  System.out.println(s7.isEmpty());                  // false

  String s8 = String.join("-","this","is","jalaz");  
  System.out.println(s8);                            // this-is-jalaz

  String s9 = "jalaz is a very good developer";  
  String s10 = s9.replace('a','e');  
  System.out.println(s10);                           // jelez is e very good developer   

  String s11 = "Jalaz is good. Jalaz is in Noida. Jalaz is coding.";  
  String s12 = s11.replaceAll("is","was");
  System.out.println(s12);                          // Jalaz was good. Jalaz was in Noida. Jalaz was coding.

  String s13 = "java string split method";
  String[] words = s13.split("\\s");                // ['java','string','split','method']

  String s14 = "hello";  
  char[] ch = s14.toCharArray();                    // ['h','e','l','l','o']  

```

## StringBuffer class

  - Used to create mutable (modifiable) string.

  - StringBuffer is synchronized i.e. thread safe. Multiple threads cannot access it simultaneously. So it is safe and will result in an order.

  ```java
    class StringBufferExample {  
        public static void main(String args[]) {  
            StringBuffer s1 = new StringBuffer("Hello ");  
            s1.append("Java");
            System.out.println(s1);                           // Hello Java
            StringBuffer s2 = new StringBuffer("Hello ");  
            s2.insert(1,"Java");
            System.out.println(s2);                           // HJavaello
            StringBuffer s3 = new StringBuffer("Hello");  
            s3.replace(1,3,"Java");  
            System.out.println(sb);                           // HJavalo
            StringBuffer s4 = new StringBuffer("Hello");  
            s4.delete(1,3);  
            System.out.println(s4);                           // Hlo
            StringBuffer s5 = new StringBuffer("Hello");  
            s5.reverse();
            System.out.println(s5);                           // olleH
        }  
    }
  ```

## StringBuilder class

   - Used to create mutable (modifiable) string.
   - Is same as StringBuffer class except that it is non-synchronized i.e. not thread safe.
   - Available since JDK 1.5.
   - Major methods of StringBuffer are same here.

`StringBuilder` is more efficient than `StringBuffer`.

  |String|StringBuffer|
  |---|---|
  |Immutable|Mutable|
  |Slow and consumes more memory when you concat too many strings because every time it creates new instance|Fast and consumes less memory when you cancat strings|
  |Overrides the equals() method of Object class|Doesn't override the equals() method of Object class|


### Creating Immutable class

Examples of immutable classes are String, Boolean, Byte, Short, Integer, Long, Float, Double etc. In short, all the wrapper classes and String class is immutable.

We can also create immutable class by creating final class that have final data members. Also, having no setters.

```java
    public final class Employee{  
        final String pancardNumber;  

        public Employee(String pancardNumber) {  
            this.pancardNumber = pancardNumber;  
        }  

        public String getPancardNumber() {  
            return pancardNumber;  
        }  
    }
```

### toString() method

Returns the string representation of the object.
If we print any object, java compiler internally invokes the toString() method on the object.

If we don't override the toString() method, then hashcode of address is printed.
So overriding the toString() method, returns the desired output as we like.

```java
    class StudentWithOut{  
       String name;  

       StudentWithOut(String name) {  
          this.name = name;
       }  

       public static void main(String args[]) {  
           StudentWithOut s1 = new StudentWithOut("Jalaz");
           System.out.println(s1);                   // StudentWithOut@1fee6fc
       }  
    }

    class StudentWith{  
       String name;  

       StudentWith(String name) {  
          this.name = name;
       }

       public String toString() {                     //overriding the toString() method  
          return "Students's name is "+name;  
       }   

       public static void main(String args[]) {  
           StudentWith s1 = new StudentWith("Jalaz");
           System.out.println(s1);                   // Student's name is Jalaz
       }  
    }

```
