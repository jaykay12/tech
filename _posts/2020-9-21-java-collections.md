---
layout: post
title: Advanced Java - X - Java Collection
categories: [Java]
---

A framework which provides the suitable architecture for storing & manipulating group of objects. `java.util` package contains all classes and interfaces.

Provides interfaces like: Set, List, Queue, Map etc. and classes like: ArrayList, Vector, HashSet, HashMap, TreeSet etc.

![collections](../assets/images/JA-25.png)

<ins>**Collection interface**</ins>

Here, all the major methods which all collections will be having are defined, notable ones are as follows:

- _public boolean add(Object element)_
- _public boolean addAll(Collection<?> c)_
- _public boolean remove(Object element)_
- _public boolean removeAll(Collection<?> c)_
- _public int size()_
- _public void clear()_
- _public boolean contains(Object element)_
- _public boolean containsAll(Collection<?> c)_
- _public Iterator iterator()_
- _public Object[] toArray()_
- _public boolean isEmpty()_
- _public boolean equals(Object element)_
- _public int hashCode()_

<ins>**Iterator interface**</ins>

Used for iterating the elements in forward direction. 2 important methods:

- _public boolean hasNext()_
- _public Object next()_


### Lists, ArrayLists & LinkedLists

<ins>**List interface**</ins>
  - Child interface of `Collection` interface.
  - For storing the ordered collection of objects.
  - It can have duplicate values.
  - Implemented by the classes ArrayList, LinkedList, Vector, and Stack.

```java
List<String> fruits = new ArrayList<String>();
fruits.add("Mango");  
fruits.add("Apple");  
fruits.add("Banana");
for(String fruitname: fruits)                       // For-Each Loop
    System.out.println(fruitname);

for(int i=0;i<fruits.size();i++)
    System.out.println(fruits[i]);                  // For Loop

fruits.get(0);                                      // get() is used for accessing
fruits.set(1,"Orange");                             // set() is used for modifying

Collections.sort(fruits);                           // sorting the list

Iterator itr = fruits.iterator();                   // Iterator
while(itr.hasNext()){                               // Forward iterator
    System.out.println(itr.next());    
}

while(itr.hasPrevious()){                           // Backward iterator
    System.out.println(itr.previous());    
}
```

<ins>**Interconversion between Java Array & List**</ins>

```java
String[] languageArray1 = {"Java","Python","PHP","C++"};  
List<String> languageList = new ArrayList<String>();  
for(String languageName: languageArray1){  
    languageList.add(languageName);
}

String[] languageArray2 = languageList.toArray(new String[languageList.size()]);
```

<ins>**ArrayList vs LinkedList**</ins>

|ArrayList|LinkedList|
|---|---|
|Implements `List` interface and maintains insertion order.|implements `List` interface and maintains insertion order.|
|Non synchronized|Non synchronized|
|For storing elements, internally dynamic array is used|For storing elements, internally doubly linked-list is used|
|Manipulation with ArrayList is slow because it internally uses an array. If any element is removed from the array, all the bits are shifted in memory.|Manipulation with LinkedList is faster than ArrayList because it uses a doubly linked list, so no bit shifting is required in memory.|
|Can act as a list only because it implements List only|Can act as a list and queue both because it implements List and Deque interfaces|
|Better for storing and accessing data|Better for manipulating data|

<ins>**Using Objects with List**</ins>

```java
import java.util.*;

class Book {  
    int id;  
    String name;

    public Book(int id, String name) {  
        this.id = id;  
        this.name = name;
    }  
}

public class ObjectWithList {  
    public static void main(String[] args) {  
        List<Book> books = new ArrayList<Book>();
        Book b1 = new Book(101,"Let us C");
        list.add(b1);  
        list.add(new Book(102, "Cormen"));

        for(Book book : books){  
            System.out.println(b.id+" "+b.name);  
        }  
    }  
}
```

### Sets, HashSets & TreeSets

<ins>**Set interface**</ins>
  - Child interface of `Collection` interface
  - Represents the unordered set of elements which doesn't allow us to store the duplicate items.
  - We can store at most one null value in Set.
  - Set is implemented by HashSet, LinkedHashSet, and TreeSet.

```java
HashSet<String> numbers = new HashSet();  
numbers.add("One");    
numbers.add("Two");    
numbers.add("Three");   
numbers.add("One");

Iterator<String> i = numbers.iterator();  
while(i.hasNext()) {  
    System.out.println(i.next());  
}

numbers.remove("Two");
numbers.contains("Three");                                    // true
numbers.contains("Two");                                      // false

ArrayList<String> namesList = new ArrayList<String>();  
namesList.add("Jalaz");  
namesList.add("Atul");

HashSet<String> namesSet = new HashSet(namesList);  
namesSet.add("Gaurav");
```

|HashSet|LinkedHashSet|TreeSet|
|---|---|---|
|Used to create a collection that uses a hash table for storage|Hashtable and Linked list implementation of the set interface|Uses a tree for storage|
|Inherits the `AbstractSet` class and implements `Set` interface|Inherits `HashSet` class and implements `Set` interface|Inherits `AbstractSet` class and implements the `NavigableSet` interface|
|Stores the elements using hashing||objects of the TreeSet class are stored in ascending order.|
|||Access and Retrieval times are quiet fast|
|Contains only unique elements|contains only unique elements|contains only unique elements|
|Allows null value|Permits null elements|Doesn't allow null element|
|Non synchronized|Non synchronized|Non synchronized|
|Doesn't maintain the insertion order. Here, elements are inserted on the basis of their hashcode|Maintains the insertion order|Maintains ascending order|
|Best approach for search operations|||


### Stacks & Queues

<ins>**Stack class**</ins>
  - linear data structure that is used to store the collection of objects.
  - Based on Last-In-First-Out (LIFO)

  ```java
    import java.util.Stack;
    import java.util.Iterator;  

    public class StackDemo {  
        public static void main(String[] args) {  
            Stack<Integer> stk = new Stack<>();  

            System.out.println("Is the stack empty? " + stk.empty());  

            stk.push(78);                                         // [78]
            stk.push(113);                                        // [113, 78]
            stk.push(90);                                         // [90, 113, 78]

            Integer valueRemoved = (Integer) stk.pop();           // 90, [113, 78]
            Integer valueNotRemoved = (Integer) stk.peek();       // 113, [113, 78]

            int location = stk.search(113);                       // 1
            System.out.println("Stack Size: "+ stk.size());       // 2  

            System.out.println(stk);                              // [113, 78]

            Iterator iterator = stk.iterator();                   // Using Iterator
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }

            stk.forEach(n ->  {System.out.println(n);});          // Using ForEach
        }  
    }  
  ```

<ins>**Queue class**</ins>
  - Orders the element in FIFO
  - Important methods:
       - _boolean add(Object element)_
       - _boolean remove()_
       - _boolean peek()_

<ins>**PriorityQueue class**</ins>
  - Holds the elements by their priorities.
  - Doesn't allow null values to be stored.

### Maps, HashMaps, LinkedHashMaps & TreeMap

<ins>**Map interface**</ins>
  - Contains values on the basis of key, i.e. key and value pair.
  - Each key and value pair is known as an entry.
  - Contains unique keys.
  - Useful if you have to search, update or delete elements on the basis of a key.
  - Map doesn't allow duplicate keys, but you can have duplicate values
  - Map can't be traversed, conversion into Set using keySet() or entrySet() method, if required.

`HashMap`: Implementation of Map, but it doesn't maintain any order

`LinkedHashMap`:	Implementation of Map. Inherits HashMap class. Maintains insertion order.

`TreeMap`:	Implementation of Map and SortedMap. Maintains ascending order.

  ![map](../assets/images/JA-26.png)

```java
import java.util.*;

public class MapDemo {  
    public static void main(String[] args) {  
        Map map = new HashMap();
        map.put("Jalaz","14MI528");  
        map.put("Saurabh","14MI539");  
        map.put("Sukhbir","14MI535");

        for(Map.Entry m : map.entrySet()) {  
            System.out.println(m.getKey()+" "+m.getValue());  
        }  
    }  
}
```

Important methods of Map interface:
- _put(Object key, Object value)_
- _void putAll(Map map)_
- _putIfAbsent(K key, V value)_
- _remove(Object key)_
- _Set keySet()_
- _Set<Map.Entry<K,V>> entrySet()_
- _void clear()_
- _boolean containsValue(Object value)_
- _boolean containsKey(Object key)_
- _boolean equals(Object o)_
- _get(Object key)_
- _int hashCode()_
- _boolean isEmpty()_
- _int size()_


|HashMap|HashTable|
|---|---|
|Non synchronized. It is not-thread safe|Synchronized. It is thread-safe |
|Allows one null key and multiple null values.|Doesn't allow any null key or value|
|fast|slow|
|new class introduced in JDK 1.2|legacy class|
|can be made synchronized using `Map m = Collections.synchronizedMap(hashMap)`|Internally synchronized and can't be unsynchronized anyhow|
|Traversed by Iterator|Traversed by Enumerator and Iterator|
|Inherits AbstractMap class|Inherits Dictionary class|


## Collections class

  - `java.util.Collections` class

  - Is different from Collection interface from which Set, List, Map, Set are extended.

  - Used exclusively with static methods that operate on or return collections

  - Important methods are:
    - _static <T> boolean addAll()_
    - _static <T> int binarySearch()_
    - _static <T> void copy()_
    - _static boolean disjoint()_
    - _static <T> void fill()_
    - _static void reverse()_
    - _static void shuffle()_
    - _static <T extends Comparable<>> void sort()_
    - _static void swap()_

    ```java
    import java.util.*;

    public class CollectionsClassDemo {  
        public static void main(String a[]) {      
            List<Integer> numbers = new ArrayList<Integer>();

            numbers.add(1208);                                 // [1208]
            numbers.add(0103);                                 // [1208, 0103]
            numbers.add(2209);                                 // [1208, 0103, 2209]
            Collections.addAll(numbers, 1111, 2204);           // [1208, 0103, 2209, 1111, 2204]
            Integer[] intArr = {1501, 2206};  
            Collections.addAll(numbers, intArr);               // [1208, 0103, 2209, 1111, 2204, 1501, 2206]

            Collections.sort(numbers);                         // [0103, 1111, 1208, 1501, 2204, 2206, 2209]

            Collections.max(list);                             // 2209
            Collections.min(list);                             // 0103
        }  
    }
    ```

#### Sorting in Collections

We can sort the elements of:

  - String objects
  - Wrapper class objects
  - User-defined class objects

  <ins>**String object**</ins>
  ```java
  ArrayList<String> al = new ArrayList<String>();             // String implements Comparable interface
  al.add("Jalaz");  
  al.add("Atul");  
  al.add("Narendra");  

  Collections.sort(al);                                      // [Atul, Jalaz, Narendra]
  Collections.sort(al, Collections.reverseOrder());          // [Narendra, Jalaz, Atul]
  ```

  <ins>**Wrapper class object**</ins> : Same

  <ins>**User-defined class objects**</ins>
      - Using Comparable interface
      - Using Comparator interface


<ins>**Comparable interface**</ins>
  - Used to order the objects of the user-defined class

  - `java.lang` package

  - Contains only one method: _compareTo(Object)_ which needs to be overridden.

  - Provides a single sorting sequence only, i.e., sort the elements on the basis of single data member only.

  `Student.java`
  ```java
    class Student implements Comparable<Student> {
        String name;  
        int age;

        Student(String name, int age) {  
            this.name = name;  
            this.age = age;  
        }  

        public int compareTo(Student student) {  
            if(age == student.age)  
                return 0;  
            else if(age>student.age)  
                return 1;  
            else  
                return -1;  
        }  
    }
  ```

  `SortUDCComparable.java`
  ```java
    import java.util.*;

    public class SortUDCComparable {  
        public static void main(String args[]) {  
            ArrayList<Student> students = new ArrayList<Student>();  
            students.add(new Student("Jalaz",24));  
            students.add(new Student("Deepu",29));  
            students.add(new Student("Disha",18));  

            Collections.sort(students);  
            for(Student student: students){  
                System.out.println(student.name+" "+student.age);  
            }  
        }  
    }
  ```

  `OUTPUT`
  ```bash
  Disha 18
  Jalaz 24
  Deepu 29
  ```

  Just tweaking the comparisions inside _compareTo()_  will sort the list in reverse order.

<ins>**Comparator interface**</ins>

  - Used to order the objects of a user-defined class

  - `java.util` package

  - Contains 2 methods _compare(Object obj1,Object obj2)_ and _equals(Object element)_

  - Provides multiple sorting sequences, i.e., sorting the elements on the basis of any data member

  `Student.java`
  ```java
    class Student {
        String name;  
        int age;  

        Student(String name, int age) {  
            this.name = name;  
            this.age = age;  
        }  
    }
  ```

  `NameComparator.java`
  ```java
    import java.util.*;

    class NameComparator implements Comparator<Student> {  
        public int compare(Student s1, Student s2){  
            return s1.name.compareTo(s2.name);
        }  
    }
  ```

  `SortUDCComparator.java`
  ```java
    import java.util.*;  
    import java.io.*;

    class SortUDCComparator {
        public static void main(String args[]) {  
            ArrayList<Student> students = new ArrayList<Student>();  
            students.add(new Student("Jalaz",24));  
            students.add(new Student("Deepu",29));  
            students.add(new Student("Disha",18));   

            Collections.sort(students, new NameComparator());  
            for(Student student: students){  
                System.out.println(student.name+" "+student.age);  
            }    
        }  
    }
  ```

  `OUTPUT`
  ```bash
  Deepu 29
  Disha 18
  Jalaz 24
  ```

  <ins>**2-level sorting using Comparator interface**</ins>

  `Student.java`
  ```java
    class Student {
        String name;  
        int age;  

        Student(String name, int age) {  
            this.name = name;  
            this.age = age;  
        }

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        public int getAge() { return this.age; }

        public void setAge(int age) { this.age = age; }

    }
  ```

  `SortUDCComparator2Way.java`
  ```java
    class SortUDCComparator2Way {
        public static void main(String args[]) {
            ArrayList<Student> students = new ArrayList<Student>();  
            students.add(new Student("Jalaz",24));  
            students.add(new Student("Deepu",29));  
            students.add(new Student("Jalaz",18));

            Collections.sort(students, new Comparator() {

                public int compare(Object o1, Object o2) {

                    String x1 = ((Student) o1).getName();
                    String x2 = ((Student) o2).getName();
                    int sComp = x1.compareTo(x2);

                    if (sComp != 0) {
                       return sComp;
                    }

                    Integer x1 = ((Student) o1).getAge();
                    Integer x2 = ((Student) o2).getAge();
                    return x1.compareTo(x2);
            }});

            for(Student student: students){  
                System.out.println(student.name+" "+student.age);  
            }
        }
    }
  ```

  `OUTPUT`
  ```bash
  Deepu 29
  Jalaz 18
  Jalaz 24
  ```
