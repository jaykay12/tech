---
layout: post
title: AWS EC2 - Concepts & Usages
categories: [Deployment, AWS]
---

AWS or Amazon Web Services provides on-demand cloud computing services & APIs to subscribers on the metered pay-as-you-go basis.
[AWS Developer Console](https://aws.amazon.com/console/)

![aws-ec2](../assets/images/AWS-EC2-1.png)

AWS Elastic Compute Cloud is a web service which provides secure & resizable compute capacity on the cloud.

Features:
- Virtual computing environments(virtual servers) in the AWS cloud called instances.
- Pre-configured AMIs.
- Variety of Instance types.
- Security for instances using key pairs.
- Additional firewall mechanism around the instance using security groups.
- Supports virtual networks or virtual private clouds.
- Temporary instance storage using instance store volumes.
- Persistent instance storage using Amazon EBS volumes.

#### Concepts

- <ins>**AMI**</ins>
  - Amazon Machine Images
  - Preconfigured templates for your instances.
  - Contains the software configuration (OS, application server, and applications) required to launch the instance.
  - Famous ones are:
    - Amazon Linux 2 AMI (HVM)
    - Microsoft Windows Server 2019 Base
    - Red Hat Enterprise Linux 8 (HVM)
    - SUSE Linux Enterprise Server 15 SP2 (HVM)
    - Ubuntu Server 20.04 LTS (HVM)
    - Ubuntu Server 18.04 LTS (HVM) etc.


- <ins>**Instance types**</ins>
  - Various configurations of CPU, memory, storage, and networking capacity for the instances.
  - Instance types are optimized to fit different use cases.
  - Famous ones are:
    - t2.nano (1 CPU, 0.5 GB)
    - t2.micro (1 CPU, 1 GB)
    - z1d.metal (48 CPU, 384 GB)
    - x1e.32xlarge (128 CPU, 3904 GB)

- Key pairs

- <ins>**Security groups**</ins>
  - Set of firewall rules that control the traffic for the instance.

- Virtual private clouds

- Amazon EBS

## Creation & Handling

#### Creation

- **Step 1**: Initiation

  ![launch-initiate](../assets/images/AWS-EC2-2.png)


- **Step 2**: Selecting the suitable AMI (Me: Ubuntu 18.04)

  ![launch-initiate](../assets/images/AWS-EC2-3.png)


- **Step 3**: Selecting the best Instance type (Me: t2.micro)

  ![launch-initiate](../assets/images/AWS-EC2-4.png)


- **Step 4**: Configuring Instances, Storages or Tags (Optional)

- **Step 5**: Adding Security group to the instance
  - We can add rules to allow specific traffic to reach the instance.
  - For eg, For a web server running on instance and allow internet traffic, add rules that allow unrestricted access to the HTTP and HTTPS ports.

  ![launch-initiate](../assets/images/AWS-EC2-5.png)


- **Step 6**: Review the instance state & configurations, once sure. Go ahead.

  ![launch-initiate](../assets/images/AWS-EC2-6.png)


- **Step 7**: For securing the instance, AWS uses Public-Private key based authentication.

  ![launch-initiate](../assets/images/AWS-EC2-7.png)

Create a Key-pair in this step & secure/download the key generated in this step as it will used for logging into the instance.

`Setting up stuff`:

  ![launch-initiate](../assets/images/AWS-EC2-8.png)

`In Launch`:

  ![launch-initiate](../assets/images/AWS-EC2-9.png)

`Launched`:

  ![launch-initiate](../assets/images/AWS-EC2-10.png)
  
#### Connecting


#### Advanced Concepts


## Deploying KitabGhar on EC2 Instance

#### Setting up MySQL Database

#### Setting up KitabGhar Portal

#### Setting up KitabGhar Recommend API
