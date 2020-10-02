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

- For processing natural language & extracting useful info from that text using ML, requires that this text should be understandable by machine. For this purpose, the text is converted into set of real numbers, technically a vector.

- WE is basically a learned representation for text where words that have the same meaning have a similar representation in the vector space.

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

- These vectors capture hidden information about a language, like word analogies or semantics.


### Word Embedding Methods

These methods learn a real-valued vector representation for a predefined fixed sized vocabulary from a corpus of text.

Most famous architectures such as Word2Vec, Fasttext, Glove helps to convert text to word vectors and leverage cosine similarity for word similarity features.

1. <ins>**Embedding layer**</ins>
  - Most native approach

  - Here word embeddings are learned jointly with a neural network model on a specific natural language processing task.

  - The embedding layer is used on the front end of a neural network and is fit in a supervised way using the Backpropagation algorithm.

  - This approach of learning an embedding layer requires a lot of training data and is slow.


  - 2 methods:
    - FeedForward Neural Net Language Model (NNLM)
    - Recurrent Neural Net Language Model (RNNLM)


  - NNLM, RNNLM outperforms for the huge dataset of words but computation complexity is a big overhead

2. <ins>**Word2Vec**</ins>

  - Developed at Google

  - Word representations in Vector Space, or word2vec algorithm.

  - Makes the neural-network-based training of the embedding more efficient and is now the de facto standard for developing pre-trained word embedding.

  - Takes a large text corpus as I/P and produces a vector space, of several hundred dimensions, with each unique word in the corpus being assigned a corresponding vector in this space.

  - Word vectors are positioned in the vector space such that words that share common contexts in the corpus are located in close proximity to one another.

  - 2 learning models were introduced for learning the word embedding:
    - Continuous Bag-of-Words, or CBOW model.
    learns the embedding by predicting the current word based on its context.

    - Continuous Skip-gram Model.
    learns the embedding by predicting the surrounding words given a current word.

    ![](../assets/images/FT-2.png)

  - Key benefit is that high-quality word embeddings can be learned efficiently (low space and time complexity), allowing larger embeddings to be learned (more dimensions) from much larger corpora of text (billions of words).


3. <ins>**GloVe**</ins>

  - Developed at Stanford.

  - Global Vectors for Word Representation, or GloVe, algorithm is an extension to the Word2Vec method for efficiently learning word vectors.

  - an approach to combine both the global statistics of matrix factorization techniques like LSA (Latent Semantic Analysis) with the local context-based learning in Word2Vec.

  - Rather than using a window to define local context, GloVe constructs an explicit word-context or word co-occurrence matrix using statistics across the whole text corpus. The result is a learning model that may result in generally better word embeddings.

### Fasttext

We can either generate word vectors for any raw data or use the pre-trained word vectors which ship with Fastext.

<ins>**Example**</ins>

`Downloading & cleaning raw dataset`
```bash
jalaz@jalaz-personal:~ wget -c http://mattmahoney.net/dc/enwik9.zip -P data/
jalaz@jalaz-personal:~ unzip data/enwik9.zip -d data/
jalaz@jalaz-personal:~ perl /home/jalaz/fastText/wikifil.pl data/enwik9 > data/fil9
```

`Generation of word vectors`
```python
import fasttext

# Generation by default settings
model = fasttext.train_unsupervised('data/fil9')

# Saving the model for later use
model.save_model("result/fil9.bin")
model = fasttext.load_model("result/fil9.bin")
```

2 models for computing word representations:
  1. **skipgram model** learns to predict a target word thanks to a nearby word.
  2. **cbow model** predicts the target word according to its context. The context is represented as a bag of the words contained in a fixed size window around the target word.

  ![cbow-skipgram](../assets/images/FT-3.png)

Practcally, skipgram models works better with subword information than cbow models.

`Tweaking parameters`
```python
model1 = fasttext.train_unsupervised('data/fil9_small', model="cbow")
model2 = fasttext.train_unsupervised('data/fil9_small', model="skipgram")
model3 = fasttext.train_unsupervised('data/fil9', minn=2, maxn=5, dim=300)
model4 = fasttext.train_unsupervised('data/fil9', epoch=1, lr=0.5)
model5 = fasttext.train_unsupervised('data/fil9', thread=4)
```

- **dim** (dimension)
  - Controls the size of the vectors
  - larger dim -> more information capture but requires more data to be learned.
  - If too large -> harder and slower to train.
  - By default, dim = 100, but any value in the 100-300 range is as popular.


- **subwords**
  - All the substrings contained in a word between **minn** and **maxn**.
  - By default, subword = (3,6), for different languages ranges may vary.


- **epoch**
  - Controls how many times the model will loop over the dataset.
  - By default, epoch = 5. For massive dataset, epoch should be less.


- **lr**
  - Higher lr -> faster the model converge to a solution but at the risk of overfitting to the dataset.
  - By default, lr = 0.05

- **thread**
  - fastText is multi-threaded and uses 12 threads by default.
  - This can easily be tweaked using this parameter for lesser-core CPUs.

`Usage of word embeddings`

- Word embedding generated for a word can be checked
```python
print(model.words)
print("\n-------------------------------------\n")
print(model.get_word_vector("female"))
```
```bash
['the',
 'of',
 ...
 'germany',
 ...
 'actress',
 ...
 'governor',
 'players',
 ...
 'models',
 ...]
 -------------------------------------
[ 0.01122757  0.18961109 -0.16199729  0.11208588
 ---      ---     ---     ---     ---     ---     ---
  0.19992262 -0.06550902 -0.40920728 -0.16724268]
```


- Semantic information of the vectors are captured with the **nn functionality**.
```python
model.get_nearest_neighbors('london')
```
```bash
[(0.7785311341285706, 'princeton'),
 (0.7696226239204407, 'cambridge'),
 (0.7583264112472534, 'glasgow'),
 (0.7519310116767883, 'oxfordshire'),
 ...,
 (0.7124481797218323, 'routledge')]
```


- **nn functionality** can also be used for spellcorrections.
```python
model.get_nearest_neighbors('actres')
```
```bash
[(0.9361368417739868, 'actress'),
 (0.9093650579452515, 'actresses'),
 (0.852777361869812, 'actor'),
 (0.8409433364868164, 'songwriter'),
 ...,
 (0.771904468536377, 'snooker')]
```


- **analogies functionality** can be used for managing analogies between data points
```python
model.get_analogies("berlin", "germany", "france")
```
```bash
[(0.896462, u'paris'),
 (0.768954, u'bourges'),
 ...,
 (0.740635, u'bordeaux'),
 (0.736122, u'pigneaux')]
```


- **character n-grams** are really important. Using subword-level information helps building vectors for unknown words.
```python
model_without_subwords = fasttext.train_unsupervised('data/fil9_small', maxn=0)
model_normal = fasttext.train_unsupervised('data/fil9_small')
model_without_subwords.get_nearest_neighbors('accomodation')
print("\n------------------------------------\n")
model_normal.get_nearest_neighbors('accomodation')
```
```bash
[(0.775057, u'sunnhordland'),
 (0.769206, u'accomodations'),
 (0.753011, u'administrational'),
 ...,
 (0.732465, u'asserbo')]
 ------------------------------------
[(0.96342, u'accomodations'),
 (0.942124, u'accommodation'),
 (0.915427, u'accommodations'),
 ...,
 (0.701426, u'hospitality')]
```

## Text Classification
This deals with classifying text into 1 or more labels, spam detection, language identification, sentiment analysis comes under this domain.
  - Can be supervised or unsupervised.

  - Can be single-label classifiers (like spam-not spam) or multi-label classifiers (like hindi-english-telugu-tamil etc)

<ins>**Fasttext**</ins>

<ins>**Usage**</ins>

<ins>**Example**</ins>
