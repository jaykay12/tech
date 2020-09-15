---
layout: post
title: Advanced Topics in Java - II
categories: [Java, Core]
---

## Java I/O

Java I/O (Input and Output) is used to process the input and produce the output.

Java uses the concept of a stream to make I/O operation fast. The `java.io package` contains all the classes required for input and output operations.

A stream is a sequence of data. 3 streams are created for us automatically.
  - System.out
  - System.in
  - System.err


<ins>Output Stream</ins>
  - Java application uses an output stream to write data to a destination; it may be a file, an array, peripheral device or socket.

  - OutputStream class is an abstract class. Following are the important methods:
     - _public void write(byte[])throws IOException_
     - _public void flush()throws IOException_
     - _public void close()throws IOException_


  ![OutputStream](../assets/images/JA-12.png)


<ins>Input Steam</ins>
  - Java application uses an input stream to read data from a source; it may be a file, an array, peripheral device or socket.

  - InputStream class is an abstract class. Following are the important methods:
     - _public abstract int read()throws IOException_
     - _public int available()throws IOException_
     - _public void close()throws IOException_


  ![InputStream](../assets/images/JA-13.png)

**Getting Inputs from Console**

`BufferedReader class`
  - java.io.BufferedReader

  ```java
      import java.io.*;

      public class BufferedReaderExample {    
          public static void main(String args[]) throws Exception {             
              InputStreamReader r = new InputStreamReader(System.in);    
              BufferedReader br = new BufferedReader(r);            
              System.out.println("Enter your name");    
              String name = br.readLine();    
              System.out.println("Welcome "+name);    
          }    
      }
  ```

`Scanner class`
   - java.util.Scanner
   - It is the simplest way to get input in Java.
   - Breaks the input into tokens using a delimiter which is whitespace by default.
   - Provides nextXXX() methods to return the type of value such as nextInt(), nextByte(), nextShort(), next(), nextLine(), nextDouble(), nextFloat(), nextBoolean(), etc.

   ```java
     import java.util.*;

     public class ScannerExample {    
           public static void main(String args[]) {                       
               String s = "Hello, This is Jalaz.";   
               Scanner scan = new Scanner(s);
               System.out.println("Boolean Result: " + scan.hasNext());   
               System.out.println("String: " +scan.nextLine());  
               scan.close();

               System.out.println("--------Enter Your Details-------- ");  
               Scanner in = new Scanner(System.in);  
               System.out.print("Enter your name: ");    
               String name = in.next();         
               System.out.print("Enter your age: ");  
               int i = in.nextInt();
               System.out.print("Enter your salary: ");  
               double d = in.nextDouble();  
               System.out.println("Name: " + name + "Age: " + i + "Salary: " + d);         
               in.close();           
               }    
     }
   ```

**Handling text/data files**

FileReader & FileWriter classes are prominently used for handling text files.

Unlike FileOutputStream class, we don't need to convert string into byte array because FileWriter provides method to write string directly.

`TextFileWriter.java`
```java
    import java.io.FileWriter;

    public class TextFileWriter {  
        public static void main(String args[]){    
             try {    
               FileWriter fw = new FileWriter("/home/jalaz/tech/java.txt");    
               fw.write("These are articles written by Jalaz");    
               fw.close();    
              } catch(Exception e){ System.out.println(e); }     
         }    
    }
```

`TextFileReader.java`
```java
    import java.io.FileReader;

    public class TextFileReader {  
        public static void main(String args[]) throws Exception {    
              FileReader fr = new FileReader("/home/jalaz/tech/java.txt");    
              int i;    
              while((i=fr.read())!=-1)    
                  System.out.print((char)i);    
              fr.close();  
        }    
    }
```

**Serialization & Deserialization**
   - A mechanism of writing the state of an object into a byte-stream or vice-versa.

   - It is mainly used in Hibernate, RMI, JPA, EJB and JMS technologies.

   - Both are platform-independent, it means we can serialize an object in a platform and deserialize in different platform.

   - For serializing the object, we call the writeObject() method of `ObjectOutputStream class`.

   - For deserialization, we call the readObject() method of `ObjectInputStream class`.

   - We must have to implement the `Serializable interface` for serializing the object.

   - Is mainly used to travel object's state on the network (which is known as marshaling).

   ![Serialization](../assets/images/JA-14.png)

   - `java.io.Serializable` is a marker interface just like Clonable and Remote. Marker interfaces has no data member and method.

   - The String class and all the wrapper classes implement the java.io.Serializable interface by default.

   `Employee.java`
   ```java
     import java.io.Serializable;

     public class Employee implements Serializable{  
        int id;  
        String name;  
        public Employee(int id, String name) {  
           this.id = id;  
           this.name = name;  
        }  
     }
   ```

   `Serializer.java`
   ```java
     import java.io.*;

      class Serializer {  
          public static void main(String args[]) {  
              try {  
                 Employee e1 = new Employee(75655,"Jalaz Kumar");
                 FileOutputStream fout = new FileOutputStream("indiamart.txt");  
                 ObjectOutputStream out = new ObjectOutputStream(fout);  
                 out.writeObject(e1);  
                 out.flush();  
                 out.close();  
              } catch(Exception e) { System.out.println(e); }  
          }  
      }
   ```

   `Deserializer.java`
   ```java
      import java.io.*;

      class Deserializer {  
          public static void main(String args[]) {  
              try {  
                  ObjectInputStream in = new ObjectInputStream(new FileInputStream("indiamart.txt"));  
                  Employee e = (Employee)in.readObject();   
                  System.out.println(e.id+" "+e.name);
                  in.close();  
              } catch(Exception e) { System.out.println(e); }  
          }  
      }
   ```

**Handling CSV files**

**Handling JSON files**

**Handling XML files**

## Java Networking

Networking is a concept of connecting two or more computing devices together for resource sharing.

Network Terminologies:

 - <ins>IP Address</ins>
    - A unique number assigned to a node of a network
    - A logical address that can be changed


 - <ins>Protocol</ins>
    - A set of rules basically that is followed for communication
    - Example: TCP, UDP, Telnet etc.


 - <ins>Port Number</ins>
    - Uniquely identify different applications
    - Acts as a communication endpoint between applications


 - <ins>MAC Address</ins>
    - A unique identifier of NIC (Network Interface Controller)
    - A network node can have multiple NIC with each having unique MAC.


 - <ins>Connection-oriented and connection-less protocol</ins>
    - In connection-oriented protocol, acknowledgement is sent by the receiver. So it is reliable but slow. The example of connection-oriented protocol is TCP.
    - In connection-less protocol, acknowledgement is not sent by the receiver. So it is not reliable but fast. The example of connection-less protocol is UDP.


 - <ins>Socket</ins>
    - An endpoint between two way communication.

`java.net` package provides many classes to deal with networking applications in Java. Few important ones are:
  - Authenticator
  - CacheRequest
  - CacheResponse
  - CookieHandler
  - DatagramPacket
  - DatagramSocket
  - InetAddress
  - Inet6Address
  - HttpURLConnection
  - PasswordAuthentication
  - ServerSocket
  - Socket
  - SocketAddress
  - URI
  - URL
  - URLConnection
  - URLDecoder
  - URLEncoder
  - URLStreamHandler

**Connection-Oriented Socket Programming**
   - <ins>Socket class</ins>
      - Used to create a socket.
      - Important methods:
          - _public InputStream getInputStream()_
          - _public OutputStream getOutputStream()_
          - _public synchronized void close()_


   - <ins>ServerSocket class</ins>
      - Used to create a server socket.
      - Used to establish communication with the clients.
      - Important methods:
         - _public Socket accept()_
         - _public synchronized void close()_

   `Client.java`
   ```java
       import java.io.*;  
       import java.net.*;

       public class Client {  
           public static void main(String[] args) {  
               try {      
                 Socket s = new Socket("localhost", 6666);  
                 DataOutputStream dout = new DataOutputStream(s.getOutputStream());  
                 dout.writeUTF("Hello Server");  
                 dout.flush();  
                 dout.close();  
                 s.close();  
               } catch(Exception e) { System.out.println(e); }  
           }  
       }  
   ```

   `Server.java`
   ```java
       import java.io.*;  
       import java.net.*;

       public class Server {  
           public static void main(String[] args) {  
               try {  
                 ServerSocket ss = new ServerSocket(6666);  
                 Socket s = ss.accept();                       //establishes connection   
                 DataInputStream dis = new DataInputStream(s.getInputStream());  
                 String  str = (String)dis.readUTF();  
                 System.out.println("message= "+str);  
                 ss.close();  
               } catch(Exception e) { System.out.println(e); }  
           }  
       }  
   ```

   ![Socket-API](../assets/images/JA-11.png)

**Important Networking Classes:**
  - `URL`
     - Java URL class represents an URL.
     - URL is an acronym for Uniform Resource Locator which points to a resource on the World Wide Web.
     ```java
       import java.net.*;  

       public class URLExample {  
           public static void main(String[] args) {  
               try {  
               URL url = new URL("https://jaykay12.github.io/tech/java-basics.md");  

               System.out.println("Protocol: " + url.getProtocol());         // https
               System.out.println("Host Name: " + url.getHost());            // jaykay12.github.io
               System.out.println("Port Number: " + url.getPort());          // -1
               System.out.println("File Name: " + url.getFile());            // /tech/java-basics.md

               } catch(Exception e) { System.out.println(e); }  
           }  
       }
     ```

  - `URLConnection`
     - Represents a communication link between the URL and the application.
     - This class can be used to read and write data to the specified resource referred by the URL.
     - openConnection() method of URL class returns the object of URLConnection class, which can then be used to download the source-code of url.
     ```java
       import java.io.*;  
       import java.net.*;  

       public class URLConnectionExample {  
           public static void main(String[] args) {  
               try{  
                   URL url = new URL("https://jaykay12.github.io/tech/java-basics.md");  
                   URLConnection urlcon = url.openConnection();  
                   InputStream stream = urlcon.getInputStream();  
                   int i;  
                   while((i=stream.read())!=-1){  
                      System.out.print((char)i);  
                   }  
               }catch(Exception e){System.out.println(e);}  
           }  
       }
     ```

  - `HttpUrlConnection`
     - Represents http specific URLConnection. The java.net.HttpURLConnection is subclass of URLConnection class.
     - It works for HTTP protocol only.
     - We can information of any HTTP URL such as header information, status code, response code etc. using this.
     ```java
       import java.io.*;    
       import java.net.*;  

       public class HttpURLConnectionExample {    
           public static void main(String[] args) {    
               try {    
                   URL url = new URL("https://jaykay12.github.io/tech/java-basics.md");    
                   HttpURLConnection huc = (HttpURLConnection)url.openConnection();  
                   for(int i=1;i<=8;i++) {
                      System.out.println(huc.getHeaderFieldKey(i)+" = "+huc.getHeaderField(i));  
                   }
                   huc.disconnect();
               } catch(Exception e) { System.out.println(e); }    
           }    
       }   
     ```

  - `InetAddress`
     - Represents an IP address.
     - java.net.InetAddress class provides methods to get the IP of any host name for example www.jaykay12.github.io
     - InetAddress has a cache mechanism to store successful and unsuccessful host name resolutions.
     ```java
         import java.io.*;  
         import java.net.*;

         public class InetExample {  
             public static void main(String[] args){  
                 try{  
                     InetAddress ip = InetAddress.getByName("www.jaykay12.github.io");  

                     System.out.println("Host Name: " + ip.getHostName());         // jaykay12.github.io
                     System.out.println("IP Address: " + ip.getHostAddress());     // 35.206.76.226
                 } catch(Exception e) { System.out.println(e); }  
             }  
         }  
     ```


**Connection-less Socket Programming**
  - <ins>DatagramSocket class</ins>
     - Represents a connection-less socket for sending and receiving datagram packets.
     - Most commonly used constructor, _DatagramSocket(int port, InetAddress address) throws SocketException_ which creates a datagram socket and binds it with the specified port number and host address.


  - <ins>DatagramPacket class</ins>
     - A datagram generally is an information but there is no guarantee of its content, arrival or arrival time.
     - Java DatagramPacket is a message that can be sent or received.
     - If multiple packet are sent, it may arrive in any order. Additionally, packet delivery is not guaranteed.
     - _DatagramPacket(byte[] barr, int length)_ which is used to receive the packets after creation.
     - _DatagramPacket(byte[] barr, int length, InetAddress address, int port)_, whichis used to send the packets after creation.

    `DGSender.java`
    ```java
      import java.net.*;

      public class DGSender {  
          public static void main(String[] args) throws Exception {  
              DatagramSocket ds = new DatagramSocket();  
              String str = "Welcome java";  
              InetAddress ip = InetAddress.getByName("127.0.0.1");  

              DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);  
              ds.send(dp);  
              ds.close();  
          }  
      }
    ```

    `DGReceiver.java`

    ```java
      import java.net.*;

      public class DGReceiver {  
          public static void main(String[] args) throws Exception {  
              DatagramSocket ds = new DatagramSocket(3000);  
              byte[] buf = new byte[1024];  
              DatagramPacket dp = new DatagramPacket(buf, 1024);  
              ds.receive(dp);  
              String str = new String(dp.getData(), 0, dp.getLength());  
              System.out.println(str);  
              ds.close();  
          }  
      }
    ```

## Java Multithreading

## Java Logging

## Java JDBC

- **Introduction**


- **Connectivity**
   - MySQL
   - Oracle
   - PostGreSQL
   - MongoDB


- **Concepts**
   - Connection
   - ResultSet
   - DriverManager
   - Statementtatement
   - CallableStatement
   - Storing & Retrieving Images
   - Storing & Retrieving Files


- **Transaction Management**

- **Batch Processing**


---
