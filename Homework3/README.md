# Homework 3

## Introduction
In this homework, no microservice framework is used. Two docker containers (one for login service and the other for "Word Ladder" service) are simply linked using "--link" option provided by docker itself.

The container for login should expose itself on the host machine and act as a proxy while that for "Word Ladder" service should be inaccessible to exterior users and only respond to the request sent by the former container.

In this homework, the container for "Word Ladder" service is designed to be stateless, which means that it will respond to any valid request without authentication. All the authentication work is left to the container for login service.

***/Homework3/Backend/WordLadder*** is a Spring Boot application for the login service, while that for the "Word Ladder" is reused from ***/Homework1/Backend/WordLadder***.

## Usage (Build from Dockerfile)

### Application Microservice
Download the ***v1.0*** release from [GitHub](https://github.com/wangtianxia-sjtu/SE418-Homework/releases) and place it in the same directory as the Dockerfile for "application-microservice".
``` bash
cd Dockerfile/application
docker build -t spring-boot-word-ladder:application-microservice .
docker run -d --name wordladder spring-boot-word-ladder:application-microservice
```

### Login Microservice
Download the release for login service from [GitHub](https://github.com/wangtianxia-sjtu/SE418-Homework/releases) and place it in the same directory as the Dockerfile for "login-microservice".
``` bash
cd Dockerfile/login
docker build -t spring-boot-word-ladder:login-microservice .
docker run -d -p 8964:8964 --name login --link wordladder:word-ladder spring-boot-word-ladder:login-microservice
```

## Usage (Pull from DockerHub)
``` bash
docker pull wangtianxia/spring-boot-word-ladder:application-microservice
docker pull wangtianxia/spring-boot-word-ladder:login-microservice
docker run -d --name wordladder wangtianxia/spring-boot-word-ladder:application-microservice
docker run -d -p 8964:8964 --name login --link wordladder:word-ladder wangtianxia/spring-boot-word-ladder:login-microservice
```

## Frontend
The Vue.js frontend in Homework2 is still compatible and can be reused.
