# Homework 3

## Usage (Build from Dockerfile)

### Application Microservice
``` bash
cd Dockerfile/application
docker build -t spring-boot-word-ladder:application-microservice .
docker run -d --name wordladder spring-boot-word-ladder:application-microservice
```

### Login Microservice
``` bash
cd Dockerfile/login
docker build -t spring-boot-word-ladder:login-microservice .
docker run -d -p 8964:8964 --name login --link wordladder:word-ladder spring-boot-word-ladder:login-microservice
```

## Usage (Pull from DockerHub)
