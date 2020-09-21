---
layout: post
title: Advanced Java - VIII - Java Networking
categories: [Java]
---

Networking is a concept of connecting 2 or more computing devices together for resource sharing.

### Network Terminologies:

1. <ins>**IP Address**</ins>
  - A unique number assigned to a node of a network
  - A logical address that can be changed


2. <ins>**Protocol**</ins>
  - A set of rules for communication between devices.
  - Example: TCP, UDP, Telnet etc.


3. <ins>**Port Number**</ins>
  - For Uniquely identifying different applications
  - Acts as a communication endpoint between 2 applications.


4. <ins>**MAC Address**</ins>
  - A unique identifier of NIC (Network Interface Controller)
  - A network node can have multiple NIC with each having unique MAC.


5. <ins>**Connection-oriented and connection-less protocol**</ins>
  - In connection-oriented protocol, acknowledgement is sent by the receiver. So it is reliable but slow. The example of connection-oriented protocol is TCP.

  - In connection-less protocol, acknowledgement is not sent by the receiver. So it is not reliable but fast. The example of connection-less protocol is UDP.


6. <ins>**Socket**</ins>
  - An endpoint between two way communication.


`java.net` package provides many classes to deal with networking applications in Java.

## Connection-Oriented Socket Programming

 - <ins>**Socket class**</ins>
    - Used to create a socket.
    - Important methods:
        - _public InputStream getInputStream()_
        - _public OutputStream getOutputStream()_
        - _public synchronized void close()_


 - <ins>**ServerSocket class**</ins>
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


## Important Networking Classes:

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

   - Used to read and write data to the specified resource referred by the URL.

   - _openConnection()_ method of URL class returns the object of URLConnection class, which can then be used to download the source-code of url.

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
   - Represents http specific URLConnection. The `java.net.HttpURLConnection` is subclass of URLConnection class.

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

## Connection-less Socket Programming


- <ins>**DatagramSocket class**</ins>
   - Represents connection-less socket for sending and receiving datagram packets.

   - Most commonly used constructor:
    _DatagramSocket(int port, InetAddress address) throws SocketException_

    Creates a datagram socket and binds it with the specified port number and host address.


- <ins>**DatagramPacket class**</ins>
   - Datagram is an information but there is no guarantee of its content, arrival or arrival time.

   - Java DatagramPacket is a message that can be sent or received.

   - If multiple packet are sent, it may arrive in any order. Additionally, packet delivery is not guaranteed.

   - _DatagramPacket(byte[] barr, int length)_ : Used to receive the packets after creation.

   - _DatagramPacket(byte[] barr, int length, InetAddress address, int port)_ : Used to send the packets after creation.

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
