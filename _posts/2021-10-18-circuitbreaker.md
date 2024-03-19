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

A design pattern that falls under the `sustainable design patterns` category.

In a microservices architecture, services have to communicate with each other. Sometimes, there can be service failures, or it takes significant time to respond to requests due to network connectivity issues.

A microservices architecture consists of a large number of interconnected microservices. So, a failure of a single microservice can have a cascading effect on all microservices and can significantly affect system availability.

### Usage

Developers needed a method to prevent this cascading effect between microservices in case of network connectivity issues, timeouts, or service unavailability.
CB Pattern allows developers to prevent cascading failures in microservices architecture by invoking remote services through a proxy.

The circuit breaker pattern is a fail-safe mechanism to prevent cascading failures of microservices.

### Challenges

## Prominent Leaders

- <ins>**Hystrix**</ins>

- <ins>**Resilience4j**</ins>

- <ins>**Istio**</ins>

- <ins>**Sentinel**</ins>

- <ins>**Amazon Mesh**</ins>

