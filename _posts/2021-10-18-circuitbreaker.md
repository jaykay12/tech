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

- Used in microservices architecture where multiple services interact with each other over a network, and CB protects them from cascading failures to enhance the resiliency and fault tolerance of a distributed system.

- Protective and safety mechanism that prevents your application from continuously making requests to a service that has got some problems or is down.

In a microservices architecture, services have to communicate with each other. Sometimes, there can be service failures, or it takes significant time to respond to requests due to network connectivity issues. So, a failure of a single microservice can have a cascading effect on all microservices and can significantly affect system availability.

### Working

Developers needed a method to prevent this cascading effect between microservices in case of network connectivity issues, timeouts, or service unavailability.
CB Pattern allows developers to prevent cascading failures in microservices architecture by invoking remote services through a proxy.

The circuit breaker pattern is a fail-safe mechanism to prevent cascading failures of microservices.

CB pattern works by wrapping each service call in an object that serves as a proxy. This object monitors the success and failure rates of the service calls and maintains a state which reflects the health of the service. 


<img src="../assets/images/CB-4.png" width="50%">

Open|Closed|Half-Open
---|---|---
|CB allows normal service communication<br> CB monitors the responses from the service for errors. For successful responses w/o issues <br><br> remains in the `closed state`|When num(failures)>threshold, CB switches to the `open state`, preventing requests from reaching the service and providing a fallback response.<br><br> (threshold Value like 5 failures within 10 seconds)|Once the timeout or reset interval passes, the circuit breaker goes to the `half-open state`. <br> CB allows a limited number of test requests to pass through for seeing if the service has recovered or not.<br><br> If the test requests succeed, the circuit breaker goes back to `closed state`.<br><br> If any of the test requests fails, it means the service has still issues and the circuit breaker goes to `Open` state to block further requests.
|<img src="../assets/images/CB-1.png">|<img src="../assets/images/CB-2.png">|<img src="../assets/images/CB-3.png">|

This pattern is inspired by the electrical circuit breaker, which automatically opens the circuit if the current flow exceeds a certain threshold. This prevents the circuit from overheating and causing a fire.

In a microservices architecture, it works the same way, monitors the health of a microservice and automatically blocks requests from that service if it becomes unhealthy.


When one service synchronously calls another, there’s a possibility that another service can be down for some reason. In such a case, threads are blocked as they keep on waiting for the other service to respond. This can lead to resource exhaustion and the caller service will also be unable to handle further requests. This is called a cascading effect and can take down our entire microservices architecture.

To prevent such scenarios, a circuit breaker comes into the picture. It will block all subsequent calls to the other service immediately. After the timeout period, some requests are passed through. If they succeed, then the circuit breaker resumes normal flow. Otherwise, the timeout period starts again.

### Advantages

- Suitable for handling asynchronous communications.
- Improves availability and performance by reducing the overall application downtime.
- Enhancing or keeps user experience intact, by handling internal services error gracefully & end-user not witnessing major impact.
- Facilitates faster recovery and maintenance. Due to the intermediate state of `half-open`, the service gets enough time & buffer to get itself up & become healthy.
- By avoiding unnecessary requests to failed or slow services and reducing the impact of network latency and congestion, the circuit breaker pattern can help increase system performance.
- Provides fallback responses or alternative actions when a service is unavailable or degraded instead of waiting for timeouts or returning errors.
- Makes it easier to isolate failures and allows them to heal without affecting the rest of the system.
- Provides feedback and metrics on the health of your services through monitoring dashboards.

### Challenges

- Good infrastructure management is required to maintain circuit breakers.
- Misconfiguration of circuit breakers can cause throughput issues of the service.
- High maintainability & testing overheads.

## Prominent Leaders

- <ins>**Hystrix**</ins>

- <ins>**Resilience4j**</ins>

- <ins>**Istio**</ins>

- <ins>**Sentinel**</ins>
      - Open-source library actively maintained by Alibaba Group
      - Official part of the Spring Cloud Circuit Breaker.
      - Offers features like flow control, concurrency limiting, circuit breaking, and adaptive system protection to guarantee the reliability of microservice architecture.
      - Allows monitoring using dashboard similar to hystrix.
      - Flow control (Rate-limiting)
          - Sentinel controls the speed of random incoming requests to avoid the overloading of microservices.
          - Ensures that our service isn’t killed by a surge in traffic.
          - Supports a variety of traffic shaping strategies:
              - Direct Rejection Mode
              - Slow Start Warm-Up Mode
      - Circuit breaker
          - Uses the principle of max concurrency limiting to implement circuit breaking.
          - Reduces the impact of unstable resources by restricting the number of concurrent threads.
          - All calls to the affected resource will be rejected in the specified time window. This prevents the cascading effect.
      - Adaptive System Protection
          - Sentinel protects our server in case the system load goes too high.
          - Uses load1 (system load) as the metric to initiate traffic control. The request will be blocked under the following conditions:
              - Current system load (load1) > threshold (highestSystemLoad);
              - Current concurrent requests (thread count) > estimated capacity (min response time * max QPS)
  


- <ins>**Amazon Mesh**</ins>

