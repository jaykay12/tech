---
layout: post
title: Advanced Topics in Java I
categories: [Java, Core]
---

## Major Concepts

**Java Array**

  - Java array is an object which contains elements of a similar data type.

  - The elements are stored in a contiguous memory location.

  - It is index-based, the first element of the array is stored at the 0th index and so on.

  - In Java, we can get the array length using the length member. In C/C++, we need to use the sizeof operator.

  - Java array inherits the Object class, and implements the Serializable as well as Cloneable interfaces.

  - We can store primitive values or objects in an array in Java.

  - Like C/C++, we can also create single dimentional or multidimentional arrays in Java.

  - Java provides the feature of anonymous arrays which is not available in C/C++.

  - Advantages:
      - Optimised code. Retrieval and sorting is quite efficient.

      - Random access: Data can be accessed from any location using index.

  - Disadvantages:
      - Has fixed size and doesn't grow at runtime.

      - To tackle this, `Collections framework` is used.

  - Java array implements the Cloneable interface
      - clone() creates deep copy of the Java SD array which means it will copy the actual value.

      - clone() creates the shallow copy of the Java MD array which means it copies the references.

<ins>Single Dimensional Array</ins>

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

<ins>Anonymous Array</ins>

```java
    public static void main(String args[]) {  
        printArray(new int[]{10,22,44,66});          // anonymous array
    }  
```

<ins>Two Dimensional Array</ins>

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

<ins>Jagged Array</ins>
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


**Object Class**

  - The parent class of all the classes in java by default.

  - Is beneficial if we want to refer any object whose type is unknown. Using upcasting this is achieved.

  - Important methods of Object class:

     - public final Class getClass()

     - public int hashCode()

     - public boolean equals(Object obj)

     - protected Object clone() throws CloneNotSupportedException

     - public String toString()

     - protected void finalize()throws Throwable

     - public final void wait(long timeout)throws InterruptedException

     - public final void notify()

**Object Cloning**

   - Object cloning is a way to create exact copy of an object

   - The clone() method of Object class is used to clone an object.

   - The `java.lang.Cloneable` interface must be implemented by the class whose object clone we want to create. If we don't implement Cloneable interface, clone() method generates CloneNotSupportedException.

   - Advantages:

      - Saves the extra processing task for creating the exact copy of an object. If we perform it by using the new keyword, it will take a lot of processing time to be performed.

      - Clone() is the fastest way to copy array.

      - No need to write lengthy and repetitive codes. Just use an abstract class with a 4- or 5-line long clone() method.

   - Disadvantages:

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


**Wrapper Classes**

Provides the mechanism to convert primitive into object and object into primitive.

Since J2SE 5.0, `autoboxing` and `unboxing` feature convert primitives into objects and objects into primitives automatically.

Requirement of Wrapper class:
  - <ins>Changing the value in Method</ins>:

  Java supports only call by value. So, if we pass a primitive value, it will not change the original value. But, if we convert the primitive value in an object, it will change the original value.

  - <ins>Serialization</ins>: objects are converted into streams for performing serialization.

  - <ins>Synchronization</ins>: Java synchronization works with objects in Multithreading.

  - <ins>Collection Framework</ins>: Works with objects only. All classes of the collection framework (ArrayList, LinkedList, Vector, HashSet, LinkedHashSet, TreeSet, PriorityQueue, ArrayDeque, etc.) deal with objects only.

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


- **Command line Args**


- **Math Class**


---

## Java String

In Java, string is basically an object that represents sequence of char values. An array of characters works same as Java string.
```java
char[] ch = {'j','a','l','a','z'};  
String s1 = new String(ch);
String s2 = "jalaz";
```

`java.lang.String` class provides a lot of methods to perform operations on strings.
   - It implements Serializable, Comparable and CharSequence interfaces.
   - It is used to create a string object.
   - s1 is created using "new" keyword and s2 is created using "string literal".

String objects are stored in a special memory area known as the "string constant pool".

Each time a string literal is created, JVM checks the `string constant pool` first. If the string already exists in the pool, a reference to the pooled instance is returned. If the string doesn't exist in the pool, a new string instance is created and placed in the pool.

![String](../assets/images/JA-10.JPG)

**Immutable String**

The `CharSequence` interface is used to represent the sequence of characters. `String`, `StringBuffer` and `StringBuilder` classes implement it. We can create strings in java by using these three classes.
 - Java String is immutable.
 - For mutable strings, we use StringBuffer and StringBuilder classes.


 Immutable simply means unmodifiable or unchangeable.
 Once string object is created its data or state can't be changed but a new string object is created.

 This is for the reason that java uses the concept of string literal and stores them all in `string constant pool`. Suppose there are 5 reference variables, all refers to one object "jalaz". If one reference variable changes the value of the object, it will be affected to all the reference variables.

<ins>**String Comparision**</ins>

String in java is compared on the basis of content and reference. 3 prominent ways of achieving this:

- <ins>equals() method</ins>
    - Used in authentication.
    - Compares the original content of the string. It compares values of string for equality.
    - 2 variations are:
       - _public boolean equals(Object another)_
       - _public boolean equalsIgnoreCase(String another)_


- <ins>== operator</ins>
    - Used for reference matching.
    - Compares references not values.


- <ins>compareTo() method</ins>
    - Used in sorting.
    - Compares values lexicographically and returns an integer value that describes if first string is less than, equal to or greater than second string.


<ins>**String Concatenation**</ins>

Forms a new string that is the combination of multiple strings. 2 ways for achieving this:

- <ins>+ operator</ins>
    - Also called string concatenation operator.
    - _String s = "Jalaz"+" Kumar";_ is converted into _String s = (new StringBuilder()).append("Jalaz").append(" Kumar).toString();_ by the JVM compiler.


- <ins>concat() method</ins>
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

<ins>**StringBuffer class**</ins>

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

<ins>**StringBuilder class**</ins>

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


**Creating Immutable class**
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

<ins>**toString() method**</ins>

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

---

## Java Exception Handling
In Java, an exception is an event that disrupts the normal flow of the program.
Exception Handling is a mechanism to handle runtime errors such as ClassNotFoundException, IOException, SQLException etc.

**Introduction**

The `java.lang.Throwable` class is the root class of Java Exception hierarchy which is inherited by two subclasses: Exception and Error.

![Hierarchy Structure](../assets/images/JA-1.png)

There are 3 types of Exceptions in general way:
1. Checked Exception
2. Unchecked Excepetion
3. Errors

Errors and Unchecked exceptions are treated as same by Oracle definitons.


<ins>Checked Exception:</ins>
  - All classes which directly inherit the Throwable class except RuntimeException and Error.
  - Examples: IOException, ClassNotFoundException etc.
  - Can be checked at compile-time.
  - Compiler checks whether these are handled or not. If these exceptions are not handled/declared in the program, compilation error is thrown.


<ins>Unchecked Exception:</ins>
  - All classes which directly inherit the RuntimeException class.
  - Examples: NullPointerException, ArithmeticException etc.
  - These can only be checked at run-time.

<ins>Error:</ins>
  - These are irrecoverable.
  - They indicate that something severe enough has gone wrong, the application should crash rather than try to handle the error.
  - Examples: OutOfMemoryError, StackOverflowError etc.

**Important Terminology (Keywords):**

  - `try`
     - Used to specify a block where we should place exception code.
     - Must be followed by either catch or finally. We can't use try block alone.


  - `catch`
     - Used to handle the exception.
     - Must be preceded by try block which means we can't use catch block alone.
     - Can be followed by finally block later.


  - `finally`
     - Used to execute the important code of the program.
     - Always executed whether an exception is handled or not.
     - Examples: closing connection, stream etc.
     - Can be used to put "cleanup" code such as closing a file, closing connection etc.


  - `throw`: Used to throw an exception.

  - `throws`
     - Used to declare exceptions.
     - Doesn't throw an exception, rather specifies that there may occur an exception in the method.
     - Always used with method signature.

  ```java
      public class JavaException {  
          public static void main(String args[]) {  
             try {   
                    int a1 = 50/0;                      //ArithmeticException
                    String s1 = null;  
                    int len = s1.length();              //NullPointerException
                    String s2 = "abc";
                    int i = Integer.parseInt(s);        //NumberFormatException
                    int a2[] = new int[5];
                    a2[10] = 50;                        //ArrayIndexOutOfBoundsException

             } catch(ArithmeticException e) {System.out.println(e);
             } catch(NullPointerException e) {System.out.println(e);
             } catch(NumberFormatException e) {System.out.println(e);
             } catch(ArrayIndexOutOfBoundsException e) {System.out.println(e);
             }
             finally {
                  System.out.println("finally block is always executed");
             }  
             System.out.println("Rest code ...");  
          }  
      }  
  ```

![](../assets/images/JA-2.JPG)

`try` block can be followed by one or more `catch` blocks. Each catch block must contain a different exception handler.
   - At a time only one exception occurs and at a time only one catch block is executed.

   - All catch blocks must be ordered from most specific to most general, i.e. catch for ArithmeticException must come before catch for Exception. (If not done, then compile-time error is generated)

`finally` Block

![Finally](../assets/images/JA-3.JPG)

`finally` block will not be executed if program exits (either by calling System.exit() or by causing a fatal error that causes the process to abort).

> For each try block there can be zero or more catch blocks, but only one finally block.

<ins>**Exception Propagation**</ins>

An exception is first thrown from the top of the stack and if it is not caught, it drops down the call stack to the previous method, if not caught there, the exception again drops down to the previous method, and so on until they are caught or until they reach the very bottom of the call stack.


- By default, Unchecked Exceptions are forwarded in calling chain (propagated).
- By default, Checked Exceptions are not forwarded in calling chain (propagated).

![Finally](../assets/images/JA-4.JPG)

**Difference between throw & throws**

  |throw|throws|
  |---|---|
  |Used for explicitly throwing an exception|Used for declaring an exception|
  |Used within the method|Used with the method signature|
  |Used mainly with unchecked exceptions|Used mainly with checked exceptions|
  |Can't throw multiple exceptions|Can declare multiple exceptions like `public void method()throws IOException, SQLException`|
  |![throw](../assets/images/JA-5.png)|![throws](../assets/images/JA-6.png)|

**Difference between final, finally & finalise**

  |final|finally|finalize|
  |---|---|---|
  |Applying restrictions on class, method and variable|Placing important code, for execution whether exception is handled or not|Performing clean up processing just before object is garbage collected|
  |is keyword|is Block|is Method|
  |![final](../assets/images/JA-7.png)|![finally](../assets/images/JA-8.png)|![finalise](../assets/images/JA-9.png)|

<ins>**Custom Exceptions**</ins>

Creating your own Exception that is known as custom exception or user-defined exception. By the help of custom exception, we can have own exception and message.

`InvalidAgeException.java`
```java
  class InvalidAgeException extends Exception {  
      InvalidAgeException(String s){  
          super(s);
      }  
  }  
```

`Runner.java`
```java
  class Runner {  
       static void validate(int age) throws InvalidAgeException {  
           if(age<18)  
              throw new InvalidAgeException("Not valid");  
           else  
              System.out.println("Welcome to vote");  
       }  

       public static void main(String args[]) {  
          try {  
              validate(13);  
          } catch(Exception m) { System.out.println("Exception occured: "+m); }  

          System.out.println("Rest of the code ...");  
       }  
  }
```


---

## Java Regex
This is an API to define a pattern for searching or manipulating strings.
It is widely used to define the constraint on strings such as password and email validation.

Java Regex API provides 1 interface and 3 classes in `java.util.regex` package.
   - MatchResult interface
   - Matcher class
   - Pattern class
   - PatternSyntaxException class

<ins>**Matcher class**</ins>
   - Implements the MatchResult interface
   - It is a regex engine which is used to perform match operations on a character sequence.
   - Following are the important methods:
      - boolean matches()
      - boolean find()
      - boolean find(int start)
      - String group()
      - int start()
      - int end()
      - int groupCount()


<ins>**Pattern class**</ins>
  - It is the compiled version of a regular expression.
  - It is used to define a pattern for the regex engine.
  - Following are the important methods:
      - static Pattern compile(String regex)
      - Matcher matcher(CharSequence input)
      - static boolean matches(String regex, CharSequence input)
      - String[] split(CharSequence input)
      - String pattern()

  ```java
      import java.util.regex.*;

      public class RegexExample {  
          public static void main(String args[]){  

              Pattern p = Pattern.compile(".s");  
              Matcher m = p.matcher("as");  
              boolean b1 = m.matches();  

              boolean b2=Pattern.compile(".s").matcher("as").matches();  

              boolean b3 = Pattern.matches(".s", "as");  

              System.out.println(b+" "+b2+" "+b3);               // true true true
          }
      }
  ```

<ins>**RegEx Essentials**</ins>

|RegEx Character/Symbol|Usage & Meaning|
|---|---|
|^regex|match at the beginning of the line|
|regex$|match at the end of the line|
|[abc]|a, b, or c|
|[abc][vz]|can match a or b or c followed by either v or z|
|[^abc]|Any character except a, b, or c (negation)|
|[a-zA-Z]|a through z or A through Z, inclusive (range)|
|X\|Z|Finds X or Z.
|XZ|Finds X directly followed by Z|
|X?|X occurs once or not at all|
|X+|X occurs once or more times|
|X*|X occurs zero or more times|
|X{n}|X occurs n times only|
|.|Any character (may or may not match terminator)|
|\d|Any digits, short of [0-9]|
|\D|Any non-digit, short for [^0-9]|
|\s|Any whitespace character, short for [\t\n\x0B\f\r]|
|\S|Any non-whitespace character, short for [^\s]|
|\S+|Several non-whitespace characters|
|\w|Any word character, short for [a-zA-Z_0-9]|
|\W|Any non-word character, short for [^\w]|
|a(?!b)|(Negative look ahead) match "a" if "a" is not followed by "b".|

> The regex is applied on the text from left to right.

---
