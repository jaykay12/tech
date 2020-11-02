---
layout: post
title: RNNs, LSTMS & GRUs - Concepts & Usages
categories: [Machine Learning]
---

## Feedforward Network

In simple feedforward networks, for some input & weights, outputs are generated. The basic classification problems, facial recognition systems come under this domain as each output is independent of the previous or next output. These are easily solved using these networks.

![feedforward-plus](../assets/images/RNN-1.png)

But there exists, various applications dealing with sequential data or time-series data like translation, image captioning, query correction where the current output is dependent on the previous output as well. This relation is also sometimes termed as context-specific for nlp applications.

Summing up these issues in feedforward networks:
- can't handle sequential data
- considers only the current input
- can't memorize previous outputs

![feedforward-minus](../assets/images/RNN-2.png)

## Recurrent Neural Network (RNNs)

RNNs are specialised neural networks which are designed to handle time-series or sequential form of data. RNNs are basically feedforward networks with an internal state (memory).

![rnn-basics](../assets/images/RNN-5.png)

<ins>**Mathematical formulation**</ins>
Here,
- X is the input layer & Y is the output layer.
- X(t) is the input at time instance, t.
- W(i) is the weight associated with input layer, W(R) is the weight associated with recurrent layer, W(y) is the weight associated with output layer.
- b(h) & b(y) are the biases associated.

![rnn-mathematics](../assets/images/RNN-3.png)


<ins>**Training**</ins>
RNNs uses the standard backpropation algorithm, but it is applied on every time instance. Thus, it is also called BTT (Backpropation Through Time)

<ins>**Issues in RNNs**</ins>

There are 2 primary issues faced in RNNs:
- Vanishing Gradients
- Exploding Gradients

![rnn-issues-solutions](../assets/images/RNN-4.png)

## GRU (Gated Relay Unit)

## LSTM (Long State Term Memory)
