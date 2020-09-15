---
layout: post
title: Advanced Topics in Java - II
categories: [Java, Core]
---

## Java I/O

- **Getting Inputs from Users**

- **Handling text/data files**

- **Handling CSV files**

- **Handling JSON files**

- **Handling XML files**

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



  - `InetAddress`
  - `HttpUrlConnection`

**Connection-less Socket Programming**
    - <ins>DatagramSocket class</ins>
    - <ins>DatagramPacket class</ins>

    `DGSender.java`
    ```java

    ```

    `DGReceiver.java`
    ```java

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
