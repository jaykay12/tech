---
layout: post
title: Basics of Spring Boot
categories: [Java, Spring, Web Development]
---

Spring Boot is an open-source micro framework maintained by a company called Pivotal. This is used to build stand-alone, auto- configurable and production ready spring applications.

![Spring Boot](../assets/images/SB-1.png)

- Provides an easier and faster way to set up, configure, and run both simple and web-based applications.

- It is basically a Spring module that provides the RAD (Rapid Application Development) feature to the Spring Framework.

**Goals of Spring Boot:**

- Reduces development, unit test, and integration test time.
- Avoids defining more Annotation & XML Configuration
- Avoids writing lots of import statements

By achieving these, Spring Boot Framework reduces Development time, Developer Effort, and increases productivity.

**Notable features of Spring Boot:**

- `Autoconfiguration`: Developers can manually configure their Spring application. However when not done, Spring Boot can change the configuration based on the listed dependencies.
For Eg: If "MySQL" is listed in dependencies, & connection configuration classes are not defined, then it will configure your Spring application with the “MySQL connector”.

- `Standalone`: No need to deploy the application to a web server. Simply enter the run command to start the application.

- `Opinionated`: Spring Boot decides which defaults to use for the configuration. Also, it decides which packages to install for the required dependencies.
For Eg: If Spring Boot starter “pom” for “JPA” is listed, it will autoconfigure an in-memory database, a hibernate entity manager, and a simple data source. This opinionated default configuration can be overridden.

**Advantages of Spring Boot:**

- It makes use of Java, which is one of the most popular programming languages in the world.

- Acts as an amazing tool that helps to get enterprise-grade applications up and running quickly without having to worry about configuring your application correctly and safely.

- Helps to autoconfigure all components for a production-grade Spring application.

- Avoids writing lots of boilerplate code, annotations, and XML configuration.

- Comes with embedded HTTP servers like Tomcat or Jetty to test web applications.

**Limitations of Spring Boot:**

- The opinionated style installs many extra dependencies it assumes you’ll need. Due to which the deployment binary size can become very large.

- The Spring Boot artifact can be directly deployed into Docker containers. This is great for quickly creating microservices. However, Since the framework has been built to be agile and lightweight, it should therefore not be used for monolithic applications.

- It's quite a struggle to upgrade legacy spring code to this.
