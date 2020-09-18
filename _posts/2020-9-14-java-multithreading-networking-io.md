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

  - Reading using java.util.Scanner
    ```java
      import java.io.*;  
      import java.util.Scanner;

      public class ReadCSVScanner {  
          public static void main(String[] args) throws Exception {  
              Scanner sc = new Scanner(new File("/home/jalaz/tech/check.csv"));  
              sc.useDelimiter(",");
              while (sc.hasNext())
                  System.out.print(sc.next());
              sc.close();  
          }  
      }
    ```
  - `com.opencsv` (Recommended Way)
      - A CSV parser library for Java. OpenCSV supports all the basic CSV-type operations you are want to do.

      - Java 7 is currently the minimum supported version for OpenCSV.

      - The library can also read TDF (Tab-Delimited File) file format.

      - Setting up:

          - In Maven, add the following to `pom.xml`:

          ```bash  
          <dependency>
                <groupId>com.opencsv</groupId>
                <artifactId>opencsv</artifactId>
                <version>4.1</version>
            </dependency>
          ```        

          - In Gradle, add the following in build.gradle:

          ```bash
          compile group: 'com.opencsv', name: 'opencsv', version: '4.1'
          ```

          - In normal run, download the jar from [Link](https://repo1.maven.org/maven2/com/opencsv/opencsv/3.8/opencsv-3.8.jar). Either add this jar to project path or create a directory named lib, and add this jar to lib.

          - <ins>Reading using OpenCSV API</ins>

          ```java
          import java.io.FileReader;
          import java.util.List;
          import com.opencsv.*;

          public class ReadCSVData {
              public static void main(String[] args) {
                try {
                    FileReader filereader = new FileReader("/home/jalaz/tech/onebyone.csv");
                    CSVReader csvReader = new CSVReader(filereader);
                    String[] nextRecord;

                    while ((nextRecord = csvReader.readNext()) != null) {
                        for (String cell:nextRecord) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }

                    //------------------------------------------------------------------------

                    FileReader filereader = new FileReader("/home/jalaz/tech/allatonce.csv");
                    CSVReader csvReader = new CSVReaderBuilder(filereader)
                                              .withSkipLines(1)
                                              .build();
                    List<String[]> allData = csvReader.readAll();

                    for (String[] row : allData) {
                        for (String cell : row) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }

                    //------------------------------------------------------------------------

                    FileReader filereader = new FileReader("/home/jalaz/tech/colonseparated.csv");
                    CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
                    CSVReader csvReader = new CSVReaderBuilder(filereader)
                                              .withCSVParser(parser)
                                              .build();

                    List<String[]> allData = csvReader.readAll();

                    for (String[] row : allData) {
                        for (String cell : row) {
                            System.out.print(cell + "\t");
                        }
                        System.out.println();
                    }

                    //------------------------------------------------------------------------   
                }  
                catch (Exception e) {
                    e.printStackTrace();
                }
              }
          }
          ```

          - <ins>Writing using OpenCSV API</ins>

          ```java
          import java.io.*;
          import java.util.*;
          import com.opencsv.CSVWriter;

          public class WriteCSVData {
              public static void main(String[] args) {
                  try {
                      FileWriter outputfile = new FileWriter("/home/jalaz/tech/write.csv");
                      CSVWriter writer = new CSVWriter(outputfile);

                      List<String[]> data = new ArrayList<String[]>();
                      data.add(new String[] { "Name", "RollNo", "Branch" });
                      data.add(new String[] { "Jalaz", "14MI528", "CSE" });
                      data.add(new String[] { "Sukhbir", "14MI535", "CSE" });
                      writer.writeAll(data);
                      writer.writeNext({"Saurabh", "14MI539", "CSE" });
                      writer.close();
                  }
                  catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
          ```

**Handling JSON files**
  - `json.simple`
     - A lightweight JSON processing library that can be used to read and write JSON files.

     - It can be used to encode or decode JSON text and fully compliant with JSON specification.

     - The `JSON` is one of the widely used data-interchange formats and is a lightweight and language independent.

     - In Maven, add the following to `pom.xml`:

     ```bash
      <dependency>
          <groupId>com.googlecode.json-simple</groupId>
          <artifactId>json-simple</artifactId>
          <version>1.1.1</version>
      </dependency>
     ```

     - <ins>Writing JSON to a file</ins>

     ```java
     import java.io.FileWriter;
     import java.io.IOException;

     import org.json.simple.JSONArray;
     import org.json.simple.JSONObject;

     public class WriteJSONFile {
         public static void main( String[] args ) {
             JSONObject employeeDetails1 = new JSONObject();
             employeeDetails1.put("name", "Jalaz Kumar");
             employeeDetails1.put("email", "jalaz.kumar@indiamart.com");
             JSONObject employeeObject1 = new JSONObject();
             employeeObject1.put("employee", employeeDetails1);

             JSONObject employeeDetails2 = new JSONObject();
             employeeDetails2.put("name", "Narendra Dodwaria");
             employeeDetails2.put("email", "narendra.dodwaria@indiamart.com");
             JSONObject employeeObject2 = new JSONObject();
             employeeObject2.put("employee", employeeDetails2);

             JSONArray employeeList = new JSONArray();
             employeeList.add(employeeObject1);
             employeeList.add(employeeObject2);

             try (FileWriter file = new FileWriter("/home/jalaz/tech/employees.json")) {
                 file.write(employeeList.toJSONString());
                 file.flush();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     ```

     - <ins>Reading JSON from a file</ins>

     `employees.json`
     ```json
     [
         {
             "employee": {
                 "name": "Jalaz Kumar",
                 "email": "jalaz.kumar@indiamart.com"
             }
         },
         {
             "employee": {
                 "name": "Narendra Dodwaria",
                 "email": "narendra.dodwaria@indiamart.com"
             }
         }
    ]
     ```

     `ReadJSONFile.java`
     ```java
     import java.io.FileNotFoundException;
     import java.io.FileReader;
     import java.io.IOException;

     import org.json.simple.JSONArray;
     import org.json.simple.JSONObject;
     import org.json.simple.parser.JSONParser;
     import org.json.simple.parser.ParseException;

     public class ReadJSONFile {
         public static void main(String[] args) {
             JSONParser jsonParser = new JSONParser();
             try (FileReader reader = new FileReader("employees.json")){
                 Object obj = jsonParser.parse(reader);
                 JSONArray employeeList = (JSONArray) obj;

                 employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));

             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             } catch (ParseException e) {
                 e.printStackTrace();
             }
         }

         private static void parseEmployeeObject(JSONObject employee) {
             JSONObject employeeObject = (JSONObject) employee.get("employee");

             String name = (String) employeeObject.get("name");    
             System.out.println(name);
             String email = (String) employeeObject.get("email");    
             System.out.println(email);
         }
     }
     ```

**Handling XML files**
   - Reading XML file in Java is much different from reading other files like .docx and .txt because XML file contains data between the tags.

   - 2 parsers in Java which parses an XML file:
       - <ins>DOM Parser</ins>
          - DOM API provides the classes to read and write an XML file.
          - DOM parser parses the entire XML file and creates a DOM object in the memory.
          - In DOM everything in an XML file is a node. The node represents a component of an XML file.
          - DOM parser is slow in process and occupies lots of memory when it loads an XML file into memory.

       - <ins>SAX Parser</ins>
          - Stands for Simple API for XML
          - Parses an XML file line by line. It triggers events when it encounters the opening tag, closing tag, and character data in an xml file. Also called the event-based parser.
          - Does not load any XML file into memory.
          - It does not create any object representation of the XML document.
          - It is faster and uses less memory than DOM parser.

   - <ins>Reading XML from a file using DOM API</ins>

   `sample.xml`
   ```XML
   <?xml version="1.0"?>  
   <indiamart>  
       <employee>  
           <id>75655</id>  
           <name>Jalaz Kumar</name>
           <email>jalaz.kumar@indiamart.com</email>
       </employee>  

       <employee>  
           <id>75656</id>  
           <name>Atul Agarwal</name>
           <email>agarwal.atul1@indiamart.com</email>
       </employee>
   </indiamart>
   ```

   `ReadXMLFile.java`
   ```java
   import javax.xml.parsers.DocumentBuilderFactory;  
   import javax.xml.parsers.DocumentBuilder;

   import org.w3c.dom.Document;  
   import org.w3c.dom.NodeList;  
   import org.w3c.dom.Node;  
   import org.w3c.dom.Element;  
   import java.io.File;

   public class ReadXMLFile {  
       public static void main(String argv[]) {  
           try {  
               File file = new File("/home/jalaz/tech/sample.xml");
               DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
               DocumentBuilder db = dbf.newDocumentBuilder();  
               Document doc = db.parse(file);  
               doc.getDocumentElement().normalize();

               System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  
               NodeList nodeList = doc.getElementsByTagName("employee");
               for(int itr = 0; itr < nodeList.getLength(); itr++) {  
                   Node node = nodeList.item(itr);  
                   System.out.println("\nNode Name :" + node.getNodeName());  
                   if (node.getNodeType() == Node.ELEMENT_NODE) {  
                       Element eElement = (Element) node;  
                       System.out.println("Student id: "+ eElement.getElementsByTagName("id").item(0).getTextContent());  
                       System.out.println("Name: "+ eElement.getElementsByTagName("name").item(0).getTextContent());
                       System.out.println("EMail: "+ eElement.getElementsByTagName("email").item(0).getTextContent());
                   }  
               }  
           } catch (Exception e) {  
                e.printStackTrace();  
           }  
       }  
   }
   ```

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

**Introduction**
 - JDBC stands for Java Database Connectivity

 - Java API to connect and execute the query with the database.

 - Is a part of JavaSE.

 - API uses JDBC drivers to connect with the database. We can save, update, delete and fetch data from the database. 4 types of drivers:
    - JDBC-ODBC Bridge Driver (Discontinued)
    - Native Driver (vendor client library)
    - Network Protocol Driver (Uses middleware)
    - Thin Driver (Mostly recommended) (fully written in java)


 ![](../assets/images/JA-15.png)

 - `java.sql package` contains classes and interfaces for JDBC API. Few popular ones are:
    - Interfaces:
       - Driver
       - Connection
       - Statement
       - PreparedStatement
       - CallableStatement
       - ResultSet
       - ResultSetMetaData
       - DatabaseMetaData
       - RowSet
    - Classes:
       - DriverManager
       - Blob
       - Types


**Connectivity**

![](../assets/images/JA-16.jpg)

 - <ins>**MySQL**</ins>

   `mysqlconnector.jar` is required.
   ```java
   import java.sql.*;

   class MySQLConnection {  
       public static void main(String args[]) {  
           try {  
               Class.forName("com.mysql.jdbc.Driver");  
               Connection con = DriverManager.getConnection("jdbc:mysql://<SERVER>:<PORT>/<DATABASE>","<USERNAME>","<PASSWORD>");

               Statement stmt = con.createStatement();  
               ResultSet rs = stmt.executeQuery("SELECT * from XYZ");  
               while(rs.next())  
                  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
               con.close();

           } catch(Exception e) { System.out.println(e); }  
       }  
   }  
   ```

 - <ins>**Oracle**</ins>

   `ojdbc14.jar` is required.
   ```java
   import java.sql.*;

   class OracleConnection {  
       public static void main(String args[]) {  
           try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
               Connection con = DriverManager.getConnection("jdbc:oracle:thin:@<SERVER>:<PORT>:<DATABASE>","<USERNAME>","<PASSWORD>");  

               Statement stmt = con.createStatement();
               ResultSet rs = stmt.executeQuery("SELECT * from XYZ");  
               while(rs.next())  
                  System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));   
               con.close();  

           } catch(Exception e) { System.out.println(e); }  
       }  
   }
   ```

 - <ins>**PostGreSQL**</ins>

   Download the latest jar from [Official Website](https://jdbc.postgresql.org/download.html), `postgresql-9.2-1002.jdbc.jar` type is required.
   ```java
   import java.sql.*;

   public class PostgreSQLConnection {
      public static void main( String args[] ) {
         try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://<SERVER>:<PORT>/<DATABASE>","<USERNAME>", "<PASSWORD>");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from XYZ");  
            while(rs.next())  
               System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));   
            con.close();

         } catch ( Exception e ) { System.err.println(e); }
      }
   }
   ```

**Concepts**
  - <ins>DriverManager class</ins>
     - Acts as an interface between user and drivers.
     - Keeps track of the drivers that are available and handles establishing a connection between a database and the appropriate driver.
     - Useful methods:
        - _public static void registerDriver(Driver driver)_
        - _public static void deregisterDriver(Driver driver)_
        - _public static Connection getConnection(String url,String userName,String password)_


  - <ins>Connection interface</ins>
     - Denotes the session between java application and database.
     - Is a factory of Statement, PreparedStatement, and DatabaseMetaData i.e. object of Connection can be used to get the object of Statement and DatabaseMetaData.
     - Provide many methods for transaction management.
     - Useful methods:
        - _public Statement createStatement()_
        - _public Statement createStatement(int resultSetType, int resultSetConcurrency)_
        - _public void setAutoCommit(boolean status)_
        - _public void commit()_
        - _public void rollback()_
        - _public void close()_


  - <ins>Statment interface</ins>
     - Provides methods to execute queries with the database
     - Useful methods:
        - _public ResultSet executeQuery(String sql)_
        - _public int executeUpdate(String sql)_
        - _public boolean execute(String sql)_
        - _public int[] executeBatch()_


  - <ins>ResultSet interface</ins>
     - Maintains a cursor pointing to a row of a table. Initially, cursor points to before the first row.
     - By default, ResultSet object can be moved forward only and it is not updatable.
     - Useful methods:
        - _public boolean next()_
        - _public boolean previous()_
        - _public boolean first()_
        - _public boolean last()_
        - _public boolean absolute(int row)_
        - _public boolean relative(int row)_
        - _public int getInt(int columnIndex)_
        - _public int getInt(String columnName)_
        - _public String getString(int columnIndex)_
        - _public String getString(String columnName)_
        - _public ResultSetMetaData getMetaData()_


  - <ins>PreparedStatement interface</ins>
     - Is a subinterface of Statement. It is used to execute parameterized query.
     ```java
     PreparedStatement stmt = con.prepareStatement("INSERT into TABLE_NAME values(?,?)");  
     stmt.setInt(1,101);
     stmt.setString(2,"Ratan");  
     int i=stmt.executeUpdate();
     ```

  - <ins>CallableStatement Interface</ins>
     - Used to call the stored procedures and functions.
     `Stored Procedure on DB`
     ```bash
     CREATE or REPLACE procedure "insertR"  
     (id IN NUMBER, name IN VARCHAR2)  
     IS  
     BEGIN  
     INSERT INTO USER_TABLE values(id,name);  
     END;  
     /
     ```

     `Usage`
     ```java
     CallableStatement stmt = con.prepareCall("{CALL insertR(?,?)}");  
     stmt.setInt(1,1011);  
     stmt.setString(2,"Amit");  
     stmt.execute();
     ```


  - <ins>ResultSetMetaData interface</ins>
     - metadata means data about data i.e. we can get further information from the data.
     - metadata of a table like total number of column, column name, column type etc.
     - Useful methods:
        - _public int getColumnCount()throws SQLException_
        - _public String getColumnName(int index)throws SQLException_
        - _public String getColumnTypeName(int index)throws SQLException_
        - _public String getTableName(int index)throws SQLException_


  - <ins>DatabaseMetaData interface</ins>
     - Provides methods to get meta data of a database such as database product name, database product version, driver name, name of total number of tables, name of total number of views etc.
     - Useful methods:
        - _public String getDriverName()throws SQLException_
        - _public String getDriverVersion()throws SQLException_
        - _public String getUserName()throws SQLException_
        - _public String getDatabaseProductName()throws SQLException_
        - _public String getDatabaseProductVersion()throws SQLException_
        - _public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)throws SQLException_


  - Storing & Retrieving Images
  - Storing & Retrieving Files


- **Transaction Management**

- **Batch Processing**


---
