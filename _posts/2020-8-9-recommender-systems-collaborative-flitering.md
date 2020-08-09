---
layout: post
title: Recommendation Systems
categories: [Machine Learning, Miscellaneous]
---

Recommender System are a subclass of information filtering system that seeks to predict the "rating" or "preference" a user would give to an item.
Major Examples:
- Playlist generators for video and music services like Netflix, YouTube and Spotify.
- Product recommenders for services such as Amazon and IndiaMART.
- Content recommenders for social media platforms like Facebook and Twitter.

Recommender System makes use of either or both of Collaborative filtering-based approach & content-based approach. Nowadays, Knowledge-based system approach is also in use.

**Collaborative filtering** approaches build a model from a user's past behavior (items previously purchased or selected and/or numerical ratings given to those items) as well as similar decisions made by other users.
_Major example is Last.fm._
Last.fm creates a "station" of recommended songs by observing what bands and individual tracks the user has listened to on a regular basis and comparing those against the listening behavior of other users.

**Content-based filtering** (Personality-based) approaches utilize a series of discrete, pre-tagged characteristics of an item in order to recommend additional items with similar properties.
_Major example is Pandora Music._
Pandora uses the properties of a song or artist to seed a "station" that plays music with similar properties. User feedback is used to refine the station's results, deemphasizing certain attributes when a user "dislikes" a particular song and emphasizing other attributes when a user "likes" a song.

## Collaborative Filtering

Collaborative filtering is based on the assumption that people who agreed in the past will agree in the future, and that they will like similar kinds of items as they liked in the past.
By locating peer users/items with a rating history similar to the current user or item, they generate recommendations using this neighborhood.
Thus Collaborative filtering has 2 approaches: item-item based CF & user-user based CF.

**Advantages**:
1. Does not rely on machine analyzable content and therefore it is capable of accurately recommending complex items such as movies without requiring an "understanding" of the item itself.
2. It's scope is unlimited in nature.     


**Drawbacks**:
1. **_Cold start:_** For a new user or item, there isn't enough data to make accurate recommendations.
2. **_Scalability:_** In many of the environments in which these systems make recommendations, there are millions of users and products. Thus, a large amount of computation power is often necessary to calculate recommendations.
3. **_Sparsity:_** The number of items sold on major e-commerce sites is extremely large. The most active users will only have rated a small subset of the overall database. Thus, even the most popular items have very few ratings.

**Data Collection Strategies behind Recommenders:**
- Explicit Data Collection:
   - Asking for user ratings
   - User's search pattern
   - User's playlist or shopping lists
   - Presenting 2 items & let him choose 1 of the two     


- Implicit Data Collection:
   - Analyse user's social media & discovering similar likes/dislikes
   - Clickstream data for user's engangement on platform
   - User's order history or watch history
