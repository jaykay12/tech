---
layout: post
title: Advanced Java - IX - Java Logging
categories: [Java, Core]
---

Logging is the process of writing log messages during the execution of a program to a central place.
This logging allows you to report and persist `error` and `warning` messages as well as `info` messages (e.g., runtime statistics) so that the messages can later be retrieved and analyzed.

## JUL logging

  Java contains the Java Logging API. This logging API allows you to configure which message types are written.
  The `java.util.logging package` provides the logging capabilities via the `Logger class`.

  The basic logging levels are:

  |Level|Value|Usage|
  |---|---|---|
  |SEVERE|1000|Indicates some serious failure|
  |WARNING|900|Potential Problem|
  |INFO|800|General Info|
  |CONFIG|700|Configuration Info|
  |FINE|500|General developer info|
  |FINER|400|Detailed developer info|
  |FINEST|300|Specialized Developer Info|
  |-|-|-|
  |OFF|Integer.MAX_VALUE|Capturing nothing|
  |ALL|Integer.MIN_VALUE|Capturing Everything|

  <ins>**LogManager class**</ins>
   - Provides a single global instance to interact with log files.

   - Has a static method which is named _getLogManager()_

   - The log system is centrally managed. There is only one application wide log manager which manages both the configuration of the log system and the objects that do the actual logging.


  <ins>**Logger class**</ins>
   - Provides methods for logging

   - LogManager is the one doing actual logging, its instances are accessed using the _getLogger()_ method of LogManager class.

   - Has a static field `GLOBAL_LOGGER_NAME`, which accesses global logger instance.

  ```java
  import java.io.IOException;
  import java.util.logging.Level;
  import java.util.logging.Logger;
  import java.util.logging.*;

  class DemoLogger {
      private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

      public void makeSomeLog() {
          LOGGER.log(Level.INFO, "Log generated from the logger class");
      }
  }

  public class LoggerExample {
      public static void main(String[] args)
      {
          DemoLogger obj = new DemoLogger();
          obj.makeSomeLog();

          LogManager lgmngr = LogManager.getLogManager();
          Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
          log.log(Level.INFO, "Log generated from the main class");
      }
  }
  ```

  `OUTPUT`
  ```bash
  Sep 19, 2020 7:56:33 PM DemoLogger makeSomeLog
  INFO: Log generated from the logger class
  Sep 19, 2020 7:56:33 AM LoggerExample main
  INFO: Log generated from the major class
  ```

## Log4J logging

  <ins>**Introduction**</ins>

  <ins>**Setup & Usage**</ins>

  <ins>**Configuration file**</ins>

  <ins>**Logging levels & methods**</ins>

  ```java

  ```

  <ins>**Appenders**</ins>
