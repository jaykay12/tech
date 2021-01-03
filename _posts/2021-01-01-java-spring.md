---
layout: post
title: Basics of Spring Framework
categories: [Java, Spring]
---

Spring is an open-source & most popular application development framework for enterprise java.
- Developed by Rod Johnson & released in 2003.
- Better over other J2EE frameworks like Java Servlets & JSPs.
- Used for creating high performance, easily testable & reusable code.

![spring](../assets/images/SPRING-1.png)

<ins>**Benefits of Spring:**</ins>

- Universal acceptance & great supporting community.
- Flexible in usage (Due to wide-range of extensions & 3rd party libraries)
- Super productive (Reduces extra efforts of developers like auto-configurable etc in Spring Boot)
- Fast performance (Reduced startup & shutdown - optimised execution - nonblocking architecture - live reload features)
- Highly secure

<ins>**Features provided by Spring:**</ins>

![spring-features](../assets/images/SPRING-2.png)


## Spring Concepts

#### Runtime Architecture

Spring framework is modular, allows us to pick & choose modules application, without having to bring in the rest.

Main 20 modules form part of Spring Framework:

![spring-runtime-architecture](../assets/images/SPRING-3.png)

1. <ins>**Core Container:**</ins>
  - `core` module provides fundamental features like DI & IOC.
  - `beans` module provides BeanFactory.
  - `context` module builds upon the core & beans modules, providing medium to the objects defined & configured.
  - `SpEL` module provides powerful expression language for querying & manipulating objects at runtime.

2. <ins>**Data Access/Integration Container:**</ins>
  - `JDBC` module provides an abstraction layer over JDBC operations.
  - `ORM` module provides integration layer for prominent object-relational mapping APIs like Hibernate, JPA etc.
  - `OXM` module provides support for object-xml mappings implementations for Castor, XMLBeans, JAXB etc.
  - `JMS` module (Java Messaging Services) contains features for producing & consuming messages.
  - `transaction` module supports transaction management.

3. <ins>**Web Container:**</ins>
  - `web` module provides basic web integration features like mutli-part file uploads & IOC container initialisation using servlets listeners & web-oriented application context.
  - `web-mvc` module contains Model-View-Controller implementation for web application.
  - `web-socket` modules provides support for 2-way communication.
  - `web-portlet` module mirrors functionality of web-servlets.

4. <ins>**Miscellanous:**</ins>
  - `AOP`, `aspects` & `instrumentation` module provides support for Aspect Oriented programming.

  - `test` module provides testing support of Spring components with JUnit & TestNG.


### Terminology

<ins>**Spring Containers**</ins>
Spring containers, also called IoC containers are at the core of Spring Framework.
- containers create objects, wires them together, configures them & manages their complete lifecycle from creation to destruction.
- containers uses DI to manage the components (Spring Beans) that makes up the application.
- containers gathers instructions on what components to instantiate, configure or assemble by reading configuration metadata provided in the form of java annotations or xml files.

![spring-containers](../assets/images/SPRING-4.png)

2 main IoC containers are there:
- BeanFactory container provides basic support.
- ApplicationContext container provides more enterprise-specific features & includes all the functionality of BeanFactory container.

#### Spring Bean

<ins>**Bean**</ins> is an
- object that is instantiated, managed & configured by IoC container.
- forms the backbone of the spring framework
- created by the configuration metadata which is supplied to the IoC container.

Using the configuration metadata, IoC container gathers following info:
- Creation of bean
- Bean's lifecycle details
- Beans's dependencies

Following properties are set for each bean:
- `class` : specifies the bean class to be used for creation.
- `name` : uniquely identifies the bean.
- `scope` : specifies the scope of objects created from a particular bean definitions.
- `constructor-arg` : used for injecting dependencies.
- `properties` : used for injecting dependencies.
- `autowiring mode` : used for injecting dependencies.
- `lazy-initialisation mode` : tells the IoC container to create bean instance when requested, rather than at the startup.
- `initialisation method` : callback to be called after setting all the necessary properties on the bean by the IoC container.
- `destruction method` : callback to be called after destroying the bean.

Configuration metadata  can be provided to the spring container using 3 methods:
- XML-based configuration files
- Annotataion-based configuration files
- Java-based configuration files

<ins>**Bean scopes**</ins>
For each beans defined, scope of the bean can be declared.
There are 5 scopes available, out of which 3 can only be used in web-aware ApplicationContext.

- singleton
  - scopes the bean definition to a single instance per IoC container.
  - when we wish that spring should return the same bean instance when needed.

- prototype
  - scopes the bean defintion to have any number of object instances.
  - foces spring to produce a new bean instance each time when needed.

- request
  - scopes the bean definition to an HTTP request.

- session
  - scopes the bean definition to an HTTP session.

- global session
  - scopes the bean definition to a global HTTP session.

<ins>**Bean lifecycle**</ins>
Lifecycle is quite easy to understand. When instantiated, some initialisation is required for the bean to get into a useful state. When usage of bean is completed & is no longer required, then some clean-up is required.

init-method & destroy-method parameters are used to define the setup & teardown of bean.
- init-method specifies the method to be called on bean upon instantiation.
- destroy-method sepcifies the method that is called just before a bean is removed from the container.

```bash
<bean id = "exampleBean1" class = "examples.ExampleBean1" init-method = "init"/>
<bean id = "exampleBean2" class = "examples.ExampleBean2" destroy-method = "destroy"/>
```

```java
public class ExampleBean1 {
   public void init() {
      // do some initialization work
   }
}

public class ExampleBean2 {
   public void destroy() {
      // do some destruction work
   }
}
```

Apart from this way, direct methods are also there which doesn't require confguring the metadata in xml files:

```java
public class ExampleBean1 implements InitializingBean {
   public void afterPropertiesSet() {
      // do some initialization work
   }
}

public class ExampleBean2 implements DisposableBean {
   public void destroy() {
      // do some destruction work
   }
}
```

<ins>**Bean Post Processor**</ins>

The interface defines callback methods that can be used for providing own instantiation logic, dependency-resolution logic etc.

BPP operates on bean instances, means that IoC container instantiates bean instance before BPP works.

`Quote.java`
```java
public class Quote {
   private String title;

   // GETTER - SETTER

   public void init() {
      System.out.println("Bean is going through init.");
   }

   public void destroy() {
      System.out.println("Bean will destroy now.");
   }
}
```

`jalazBPP.java`
```java
public class jalazBPP implements BeanPostProcessor {
   public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      System.out.println("BeforeInitialization : " + beanName);
      return bean;
   }

   public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {    
      System.out.println("AfterInitialization : " + beanName);
      return bean;
   }
}
```

`bean-config.xml`
```bash
<beans xmlns = ""
   xmlns:xsi = ""
   xsi:schemaLocation = "">

   <bean id = "quote" class = "tech.jaykay12.Quote"
      init-method = "init" destroy-method = "destroy">
      <property name = "title" value = "\"Glory in the sky!\""/>
   </bean>

   <bean class = "tech.jaykay12.jalazBPP" />
</beans>
```

`Runner.java`
```java
public class Runner {
   public static void main(String[] args) {
      AbstractApplicationContext context = new ClassPathXmlApplicationContext("bean-config.xml");

      Quote q = (Quote) context.getBean("quote");
      q.getTitle();
      context.registerShutdownHook();
   }
}
```

`OUTPUT`
```bash
BeforeInitialization : quote
Bean is going through init.
AfterInitialization : quote
Title : "Glory in the sky!"
Bean will destroy now.
```


## Spring Core Concepts

#### DI

<ins>**Annotation-based**</ins>

<ins>**XML-based**</ins>

#### IOC

#### Aspect Oriented Programming

#### POJO





###
