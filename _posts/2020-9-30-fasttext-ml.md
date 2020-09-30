---
layout: post
title: fasttext, Facebook ML Library
categories: [ML, Miscellaneous]
---

An open-source, free, lightweight library created by Facebook R%D that learns text representations and build text classifiers.

![fasttext](../assets/images/FT-1.png)

- Written in C++ and supports multiprocessing during training.

- Allows us to train supervised and unsupervised representations of words and sentences.

<ins>**Setting it up**</ins>
```bash
$ pip install fasttext
----------------------------Installing-------------------------
$ python
Python 2.7.15 |(default, May  1 2018, 18:37:05)
Type "help", "copyright", "credits" or "license" for more information.
>>> import fasttext
>>>
```

### Word Embeddings

- For processing natural language text and extracting useful information from any word/sentence using ML and DL techniques requires the text to be converted into a set of real numbers, technically a vector. This representation —> Word Embeddings.

- It is basically a learned representation for text where words that have the same meaning have a similar representation in the vector space.

- The process of converting words into real numbers/vectors -> Vectorization.


- Word embeddings help in the following use cases.
  - Compute similar words
  - Calculate semantics behind words
  - Document clustering/grouping
  - Feature extraction for text classifications
  - Natural language processing.


- Word embeddings can be calculated using pre-trained methods from libraries such as,
  - Word2Vec — From Google
  - Fasttext — From Facebook
  - GloVe — From Stanford


- These are distributed representations of text in an n-dimensional space. Essential for solving most NLP problems.

### Word Embedding Methods

These methods learn a real-valued vector representation for a predefined fixed sized vocabulary from a corpus of text. There are mainly 4 methods for achieving this:

1. <ins>**Embedding layer**</ins>
  - Most native approach

  - Here word embeddings are learned jointly with a neural network model on a specific natural language processing task.

  - The embedding layer is used on the front end of a neural network and is fit in a supervised way using the Backpropagation algorithm.

  - This approach of learning an embedding layer requires a lot of training data and is slow.

2. <ins>**Word2Vec**</ins>

  - Developed at Google

  - Makes the neural-network-based training of the embedding more efficient and is now the de facto standard for developing pre-trained word embedding.

  - Takes a large text corpus as I/P and produces a vector space, of several hundred dimensions, with each unique word in the corpus being assigned a corresponding vector in this space.

  - Word vectors are positioned in the vector space such that words that share common contexts in the corpus are located in close proximity to one another.

  - 2 learning models were introduced for learning the word embedding:
    - Continuous Bag-of-Words, or CBOW model.
    learns the embedding by predicting the current word based on its context.

    - Skip-Gram Model.
    learns the embedding by predicting the surrounding words given a current word.

    ![](../assets/images/FT-2.png)

  - Key benefit is that high-quality word embeddings can be learned efficiently (low space and time complexity), allowing larger embeddings to be learned (more dimensions) from much larger corpora of text (billions of words).


3. <ins>**GloVe**</ins>

### Fasttext

<ins>**Usage**</ins>  

<ins>**Example**</ins>


## Text Classification
This deals with classifying text into 1 or more labels, spam detection, language identification, sentiment analysis comes under this domain.
  - Can be supervised or unsupervised.

  - Can be single-label classifiers (like spam-not spam) or multi-label classifiers (like hindi-english-telugu-tamil etc)

<ins>**Fasttext**</ins>

<ins>**Usage**</ins>

<ins>**Example**</ins>
