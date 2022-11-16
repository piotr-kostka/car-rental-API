# ::CAR RENTAL APPLICATION::

This is the BackEnd part of final project in the Kodilla Java Developer Course. 

The FrontEnd you can find here: https://github.com/piotr-kostka/car-rental-API-frontend

Last commit in BackEnd application: https://github.com/piotr-kostka/car-rental-API/commit/8a5a3688b6f66a8e6f0631908466395400ef8d9e

This is a simple car rental application. Details are enlisted below.

## Table of contents

1. [Technologies](#technologies-used)
2. [Getting Started](#getting-started)
3. [How to run it](#technologies-used)
5. [Test coverage](#test-coverage)
6. [Usage of an external API](#usage-of-api)
7. [List of Endpoints](#list-of-endpoints)



## Technologies Used
```
      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.7.5)
```
This project is based on Spring Boot in Java 11.

BackEnd:

* Java 11
* Spring
* REST API
* Hibernate
* Gradle
* JUnit
* MySQL
* Scheduler


FrontEnd (https://github.com/piotr-kostka/car-rental-API-frontend):

* Vaadin

## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
See deployment for notes on how to deploy the project on a live system. Just clone or download the files into a folder and it's ready to be used.

### BackEnd

- Clone the repository
```
git clone https://github.com/piotr-kostka/car-rental-API.git
```
- Create a MySql connection as described in the application.properties.

- Build the project
```
./gradlew build
```

### FrontEnd

- Clone the repository
```
git clone https://github.com/piotr-kostka/car-rental-API-frontend
```

- Build the project
```
./gradlew build
```
## How to run it

Once you have the BackEnd and FrontEnd running start by this address:
http://localhost:8081/home

### Ports
```
BackEnd: 8080
FrontEnd: 8081
```
## Test coverage

BackEnd application has over 75% test coverage:

![](https://snipboard.io/wXTP1s.jpg)

## Usage of API

This project uses 2 external API's.

1. First one is used to convert EUR and USD to the current PLN exchange rate. 


    Example Json path:


![](https://snipboard.io/tFbEV0.jpg)

2. Second one is used to calculate average temperature, total rainfall and snowfall during next 7 days.
These data are used to calculate the ratio by which the base car rental price per day is multiplied.


    Example Json path:

![](https://snipboard.io/fAJrni.jpg)


## List of Endpoints

You can check the full list of controllers via the Swagger2 library. 

To do this, run the application locally and enter this address in the web browser: 

http://localhost:8080/swagger-ui.html#/

User:
```
GET /v1/users/
GET /v1/users/{userId}
POST /v1/users/
PUT /v1/users/
DELETE /v1/users/{userId}
```

Manufacturer:
```
GET /v1/manufacturers/
GET /v1/manufacturers/{manufacturerId}
POST /v1/manufacturers/
PUT /v1/manufacturers/
DELETE /v1/manufacturers/{manufacturerId}
```

Model:
```
GET /v1/models/
GET /v1/models/{modelId}
POST /v1/models/
PUT /v1/models/
DELETE /v1/models/{modelId}
```

Car:
```
GET /v1/cars/
GET /v1/cars/{carId}
GET /v1/cars/manufacturer/{manufacturerId}
GET /v1/cars/model/{modelId}
GET /v1/cars/price/{price}
GET /v1/cars/available
POST /v1/cars/
PUT /v1/cars/
DELETE /v1/cars/{carId}
```

Rental:
```
GET /v1/rentals/
GET /v1/rentals/{rentalId}
GET /v1/rentals/user/{userId}
POST /v1/rentals/
PUT /v1/rentals/return/{rentalId}
PUT /v1/rentals/pay/{rentalId}
```
