---
layout: post
title: Test-driven development in Spring boot
categories: [Java, DevOps]
---

Testing is the process of checking an application functionalities. Unit testing is done on developer's end.

### Terminologies

1. <ins>**Test fixture**</ins>
  - Set of objects which are used as a baseline for running tests.
  - Also called as test precondition.
  - For eg, a test fixture = fixed string, which is used as input for a method. The test would validate if the method behaves correctly with this input.


2. <ins>**Unit testing**</ins>
  - Unit test - piece of code that executes a specific functionality in the codebase and asserts a certain behavior or state.
  - Test coverage - percentage of code which is tested by unit tests
  - Targets a small unit of code, e.g., a method or a class only.
  - For unit testing code having external dependencies, either use mock objects or mocking frameworks like Mockito.


3. <ins>**Integration testing**</ins>
  - Aims to test the behavior of a component or the integration between a set of components.
  - Also called as functional testing.
  - Checks that the whole system works as intended overall.


4. <ins>**Performance testing**</ins>
  - Used to benchmark software components repeatedly.
  - Also called as load testing.
  - Purpose is to ensure that the code under test runs fast enough even if it’s under high load


5. <ins>**Calibration testing**</ins>
  - Not an industry conventions, but quite useful for consumer-centric services.
  - Results need to be calibrated before & after dev-changes for confirming go-to-live decision.
  - For Eg, used heavily in applications like search, recommendations etc.


6. <ins>**Standard conventions**</ins>
  - src/main/java - for Java classes
  - src/test/java - for test classes
  - Popular testing frameworks are JUnit and TestNG.
  - use the "Test" suffix at the end/start of test classes names. (Junit)
  - a test name should explain what the test does.

||Manual testing|Automation testing|
|---|---|---|
|Executing test cases|completely manual or script|using an automation tool|
||Time-consuming and tedious|fast and simple once setted|
|Investment in HR|Huge|Minimal|
|Reliability|less, has chances of human errors|More & are precise|


## JUnit

![junit-intro](../assets/images/JUNIT-1.png)

JUnit is a unit testing framework for Java programming language.
  - JUnit is important in the test-driven development with Java.
  - JUnit 5 is the existing version.

<ins>Usage</ins>

<ins>Example</ins>

#### JUnit vs TestNG

||JUnit|TestNG|
|---|---|---|
||||
||||
||||

## Mockito

<ins>Introdction</ins>

<ins>Usage</ins>

<ins>Example</ins>
