# CityConnection

CityConnection is a Spring Boot Restfull Web service to determine if  two cities are connected.  

## Requirements
For building and running the application you need:
* Java 1.8 or above
* Maven 3


## Build 
Clone code
Goto to the following direction city-connection-2\city-connection
 e.g cd city-connection-2\city-connection
mvn clean package

## Run Tests
From the build directory run the following commant
mvn test

The test coverage report is in 
 <buildDirectory>/target/site/jacoco/index.html

## Run

java -jar target/city-connection-0.0.1-SNAPSHOT.jar


## Usage

On a browser enter the following urls:

http://localhost:8082/connected?origin=Boston&destination=Newark
Should return yes

http://localhost:8082/connected?origin=Boston&destination=Philadelphia
Should return yes

http://localhost:8082/connected?origin=Philadelphia&destination=Albany
Should return no



## Swagger

Url: http://localhost:8082/swagger-ui.html