---
layout: post
title: Apache Airflow, Revolutionising Workflows
categories: [Data Engineering]
---

# Introduction

Airflow is an open-source tool and framework for running your data pipelines in production. As an industry-leading data workflow management tool, Apache Airflow leverages Python to allow data practitioners to define their data pipelines as code. 

It's a workflow orchestrator that enables us to design, schedule, and monitor data pipelines. The tool represents processes in the form of directed acyclic graphs(DAGs) that visualize casual relationships between tasks and the order of their execution.

Airflow works with batch pipelines which are sequences of finite jobs, with clear start and end, launched at certain intervals or by triggers.

Airflow is not a data processing tool by itself but rather an instrument to manage multiple components of data processing. It’s also not intended for continuous streaming workflows.

## Airflow Architecture

---INSERT Image here---



## Concepts of Airflow

The simplest unit of the Airflow framework are tasks. Tasks can be thought of as operations or, for most data teams, operations in a data pipeline.

A traditional ETL workflow has three tasks; extracting, transforming, and loading data. 

**DAGs**

Directed Acyclic Graph (DAG) that organizes tasks to be executed in a certain order, 
- specified by dependencies between them (hence — directed); and
- with no cycles allowed (hence — acyclic).

Each DAG is essentially a Python script that represents workflow as code. It contains two key components — operators describing the work to be done and Task relationships defining the execution order. DAGs are stored in a specific DAG folder.

**MetaData DB**

Stores information about user permissions, past and current DAG and task runs, DAG configurations, and more. It serves as the source of truth for the scheduler.

By default, Airflow handles metadata with SQLite which is meant for development only. For production purposes, choose from PostgreSQL 10+, MySQL 8+, and MS SQL.

**Scheduler**

The scheduler reads DAG files, triggers tasks according to the dependencies, and tracks their execution. It stays in sync with all workflows saved in the DAG folder and checks whether any task can be started. By default, those lookups happen once per minute but you can configure this parameter.

When a certain task is ready for execution, the scheduler submits it to a middleman — the executor.

**Executors or Workers**

An executor is a mechanism responsible for task completion. After receiving a command from the scheduler, it starts allocating resources for the ongoing job.

There are two types of Airflow executors:
- local executors live on the same server as the scheduler; and
- remote executors operate on different machines, allowing you to scale out and distribute jobs across computers.

Two main remote options are Celery Executor and Kubernetes Executor. 
Celery is an asynchronous task queue for Python programs but to use it you need to set up a message broker — RabbitMQ or Redis. 
At the same time, the Kubernetes scenario doesn’t require any additional components and unlike other executors can automatically scale up and down (even to zero) depending on the workload.

No matter the type, the executor eventually assigns tasks to workers — separate processes that actually do the job.

**Webserver**

Airflow webserver runs a user interface that simplifies monitoring and troubleshooting data pipelines.
It lets you visualize DAG dependencies, see the entire DAG history over months or even years in the calendar view, analyze task durations and overlaps using charts, track task completion, and configure settings, to name just a few possibilities.

# Usage & Tricks

# Setup
