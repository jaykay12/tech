---
layout: post
title: Circuit Breakers
categories: [Miscellaneous]
---

# Fault Tolerance & Resiliency

1. Timeouts
2. Circuit Breaker Pattern
3. <> Pattern

# Circuit Breaker Pattern

A design pattern 

- that falls under the `sustainable design patterns` category.

- Used in microservices architecture where different services interacting with each other over a network, and circuit breaker protects them from cascading failures to enhance the resiliency and fault tolerance of a distributed system.

- Protective and safety mechanism that prevents your application from continuously making requests to a service that has problems or is down.

In a microservices architecture, services have to communicate with each other. Sometimes, there can be service failures, or it takes significant time to respond to requests due to network connectivity issues.

A microservices architecture consists of a large number of interconnected microservices. So, a failure of a single microservice can have a cascading effect on all microservices and can significantly affect system availability.

### Usage

Developers needed a method to prevent this cascading effect between microservices in case of network connectivity issues, timeouts, or service unavailability.
CB Pattern allows developers to prevent cascading failures in microservices architecture by invoking remote services through a proxy.

The circuit breaker pattern is a fail-safe mechanism to prevent cascading failures of microservices.

<img src="../assets/images/CB-4.png" width="50%">

Open|Closed|Half-Open
---|---|---
|CB allows normal service communication<br> CB monitors the responses from the service for errors. For successful responses w/o issues <br><br> remains in the `closed state`|When num(failures)>threshold, CB switches to the `open state`, preventing requests from reaching the service and providing a fallback response.<br><br> (threshold Value like 5 failures within 10 seconds)|Once the timeout or reset interval passes, the circuit breaker goes to the `half-open state`. <br> CB allows a limited number of test requests to pass through for seeing if the service has recovered or not.<br><br> If the test requests succeed, the circuit breaker goes back to `closed state`.<br><br> If any of the test requests fails, it means the service has still issues and the circuit breaker goes to `Open` state to block further requests.
|<img src="../assets/images/CB-1.png">|<img src="../assets/images/CB-2.png">|<img src="../assets/images/CB-3.png">|

This pattern is inspired by the electrical circuit breaker, which automatically opens the circuit if the current flow exceeds a certain threshold. This prevents the circuit from overheating and causing a fire.

In a microservices architecture, it works the same way, monitors the health of a microservice and automatically blocks requests from that service if it becomes unhealthy.



### Advantages

- Helps to prevent cascading failures.
- Handles errors gracefully and provides better under experience.
- Reduces the application downtimes.
- Suitable for handling asynchronous communications.
- State changes of the circuit breaker can be used for error monitoring.

### Challenges

- Need good infrastructure management to maintain circuit breakers.
- Caused throughput issues in services if not configured properly.
- Difficult to test.

## Prominent Leaders

- <ins>**Hystrix**</ins>

- <ins>**Resilience4j**</ins>

- <ins>**Istio**</ins>

- <ins>**Sentinel**</ins>

- <ins>**Amazon Mesh**</ins>

