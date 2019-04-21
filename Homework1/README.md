# Homework 1

## Backend
``` bash
cd Backend/WordLadder/
./mvnw clean test
./mvnw spring-boot:run
```
or
``` bash
cd Backend/WordLadder/
./mvnw clean test
./mvnw clean package
java -jar target/WordLadder-0.0.1-SNAPSHOT.jar (or the corresponding jar file path)
```

## Frontend
``` bash
cd Frontend
npm install
npm run serve
```

## RESTful API

/search?start=good?end=last
