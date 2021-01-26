---
layout: post
title: MongoDB, Peer Document NoSQL DBs
categories: [NoSQL Databases, Databases]
---

![mongodb](../assets/images/MONGODB-1.png)

MongoDB is a
  - cross-platform
  - document-oriented NoSQL DB

provides
  - Easy scalability
  - High performance
  - High availability

### Concepts & Terminologies

MongoDB works on the concept of Collections & Documents.

- <ins>**Database**</ins>
  - Physical container for collections.
  - Each DB gets its own set of files on the file system.
  - A single MongoDB server has multiple databases.

- <ins>**Collection**</ins>
  - Group of MongoDB documents.
  - Equivalent of an RDBMS table.
  - A collection can only exists within a single database.
  - Collections do not enforce a schema.
  - Documents within a collection can have different fields.

- <ins>**Document**</ins>
  - Set of key-value pairs.
  - Documents have dynamic schema.
  - `Dynamic schema` - Documents in the same collection can have different set of fields or structure, and the common fields may hold different types of data in any 2 documents.

![rdbms-comparision](../assets/images/MONGODB-2.png)

`_id`
- 12 bytes hexadecimal number which assures the uniqueness of every document.
- `_id` can be provided while inserting the document. If not provided then MongoDB provides a unique id for every document.
- These 12 bytes
  - first 4 bytes -> current timestamp,
  - next 3 bytes -> machine id
  - next 2 bytes -> process id of MongoDB server
  - remaining 3 bytes -> simple incremental VALUE.

### Installation & Configuration

**Step 1:** Adding Official MongoDB repository in the ubuntu packages & refresh sources list.

```bash
jalaz@jalaz-personal:~$ wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -
[sudo] password for jalaz:
OK

jalaz@jalaz-personal:~$ echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list
deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/4.4 multiverse

jalaz@jalaz-personal:~$ sudo apt-get update
...
Get:3 https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/4.4 Release [4,437 B]
Get:4 https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/4.4 Release.gpg [801 B]
...
Fetched 14.5 kB in 3s (5,065 B/s)
Reading package lists... Done
```

**Step 2:**  Installation (Standard one)(Works for 12.04, 14.04, 16.04)
```bash
jalaz@jalaz-personal:~$ sudo apt install -y mongodb
jalaz@jalaz-indiamart:~$ mongo --version
```

**Additional:** For 18.04 MongoDB is not officially supported. Used ubuntu packages which are not maintained by MongoDB org. This step is a replacment to Step 1 & Step 2.

```bash
jalaz@jalaz-indiamart:~$ sudo apt-get install mongodb
jalaz@jalaz-indiamart:~$ mongo --version
MongoDB shell version v3.6.8
```

**Step 3:** Starting, Stopping & Checking status

```bash
jalaz@jalaz-personal:~$ sudo systemctl start mongodb
jalaz@jalaz-personal:~$ sudo systemctl status mongodb
● mongodb.service - An object/document-oriented database
   Loaded: loaded (/lib/systemd/system/mongodb.service; enabled; vendor preset: enabled)
   Active: active (running) since Wed 2021-01-20 15:55:48 IST; 6min ago
     Docs: man:mongod(1)
 Main PID: 16863 (mongod)
    Tasks: 23 (limit: 4915)
   Memory: 40.2M
   CGroup: /system.slice/mongodb.service
           └─16863 /usr/bin/mongod --unixSocketPrefix=/run/mongodb --config /etc/mongodb.conf
jalaz@jalaz-personal:~$ sudo systemctl stop mongodb
```

#### MongoDB package components

- <ins>**Core Processes**</ins>
  - `mongod` : core database process
    - primary daemon process for the MongoDB system.
    - It handles data requests, manages data access, and performs background management operations.

  - `mongos` : controller and query router for sharded clusters.
    - For a sharded cluster, the mongos instances provide the interface between the client applications and the sharded cluster.
    - The mongos instances route queries and write operations to the shards.
    - From the perspective of the application, a mongos instance behaves identically to any other MongoDB instance.

  - `mongo` : interactive MongoDB Shell.
    - provides a powerful interface for system administrators as well as a way for developers to test queries and operations directly with the database.

- <ins>**Import/Export**</ins>
  - `mongodump`
    - provides a method for creating BSON dump files from the mongod instances.

  - `mongorestore`
    - restore these BSON dumps into mongod instances.

  - `bsondump`
    - converts BSON dump files into JSON.

  - `mongoimport`
    - provides a method for taking data in JSON, CSV, or TSV and importing it into a mongod instance.

  - `mongoexport`
    - provides a method to export data from a mongod instance into JSON, CSV, or TSV.

- <ins>**Diagnostics**</ins>
  - `mongotop`
  - `mongostat`

- <ins>**Security**</ins>
  - `mongoldap`
  - `mongokerberos`

### Basic Usage

MongoDB provides 2 types of data models:
- Embedded data model
- Normalized data model

<ins>**Embedded data model**</ins>

```json
{
    _id: "<ObjectId101>",
    Emp_ID: "75655",
    Personal_details: {
        Name: "Jalaz Kumar",
        Date_Of_Birth: "1995-08-12"
    },
    Contact: {
        e-mail: "jalaz.kumar@indiamart.com",
        city: "Almora"
    }
}
```

<ins>**Normalized data model**</ins>

`Employee`
```json
{
    _id: "<ObjectId101>",
    Emp_ID: "75655"
}
```

`Personal Details`
```json
{
    _id: "<ObjectId102>",
    DocEmpID: "<ObjectId101>",
    Name: "Jalaz Kumar",
    Date_Of_Birth: "1995-08-12"
}
```

`Contact`
```json
{
    _id: "<ObjectId103>",
    DocEmpID: "<ObjectId101>",
    e-mail: "jalaz.kumar@indiamart.com",
    city: "Almora"
}
```

#### Important Commands

<ins>**Creation & Insertion**</ins>

    use DATABASE_NAME
    show dbs
    db.dropDatabase()

    db.createCollection(name, options)
    show collections
    db.COLLECTION_NAME.drop()
    db.COLLECTION_NAME.insert(document)
    db.COLLECTION_NAME.insert([document1,document2])
    db.COLLECTION_NAME.insertOne(document)
    db.COLLECTION_NAME.insertMany(document1,document2)

<ins>**Querying & Projection**</ins>

    db.COLLECTION_NAME.find()
    db.COLLECTION_NAME.find().pretty()
    db.COLLECTIONNAME.findOne()

|SQL DBs (RDBMS)|NoSQL DBs|
|---|---|
|where name = 'jalaz'|db.students.find({"name":"jalaz"})|
|where likes < 50|db.students.find({"likes":{$lt:50}})|
|where name in ["Raj", "Ram", "Raghu"]|db.students.find({"name":{$in:["Raj", "Ram", "Raghu"]}})|
|where team = 'search' AND company = 'indiamart'|db.students.find({$and:[{"team":"search"},{"company": "indiamart"}]})|
|where likes>10 AND (skill = 'solr' OR name = 'jalaz')|db.students.find({"likes":{$gt:10}, $or: [{"skill": "solr"},{"name": "jalaz"}]})|
|where age is not greater than 25|db.students.find({"Age": {$not: {$gt:"25"}}})|

    db.COLLECTION_NAME.find({},{KEY_1:1, KEY_2:0, KEY_3:1})
    db.COLLECTION_NAME.find().limit(NUMBER)
    db.COLLECTION_NAME.find().limit(NUMBER).skip(NUMBER)
    db.COLLECTION_NAME.find().sort({KEY:1})

<ins>**Updation & Deletion**</ins>

2 functions are used prominently:
- update() : updates the value in the existing document.
- save() : replaces the existing document with the document passed as argument.


    db.COLLECTION_NAME.update(SELECTION_CRITERIA, UPDATED_DATA)
    db.COLLECTION_NAME.save({_id:ObjectId(),NEW_DATA})
    db.COLLECTION_NAME.findOneAndUpdate(SELECTIOIN_CRITERIA, UPDATED_DATA)
    db.COLLECTION_NAME.updateOne(<filter>, <update>)
    db.COLLECTION_NAME.updateMany(<filter>, <update>)

    db.COLLECTION_NAME.remove(DELETION_CRITERIA)
    db.COLLECTION_NAME.remove(DELETION_CRITERIA,1)

<ins>**Indexing**</ins>

<ins>**Aggregation**</ins>

---

### Practical Usage

### Advanced Concepts

1. Sharding

2. Replication

3. Backups

4. Deployment
