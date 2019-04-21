# Homework 2

## Backend
``` bash
cd Backend/WordLadder/
./mvnw clean test
./mvnw spring-boot:run
```
or
``` bash
cd Backend/WordLadder/
./mvnw clean test surefire-report:report (Test reports are available in target/site)
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

/actuator/health (only for admin)

other actuator APIs (like /actuator/info) (only for admin)
