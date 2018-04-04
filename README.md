# Omniva Invoices test app
Application is created using Eclipse STS and project can be simply imported into any Eclipse STS workspace

# Build and Run
1. Special Spring Boot "run configuration" provided by STS:
* Specify "ee.omniva.OmnivaApplication" as main class and run

2. Console build and run with Maven commands
* Build and run tests: mvn clean install
* Run: mvn spring-boot:run

# Functionality
Application starts on localhost port 8080:
http://localhost:8080/omniva

## Access H2 in-memory database
http://localhost:8080/omniva/h2console

Driver-class: org.h2.Driver
JDBS URL: jdbc:h2:mem:OmnivaDB
Username: sa
Password: 

## REST API Documentation (Swagger)
http://localhost:8080/omniva/swagger-ui.html

## Generate random invoices
http://localhost:8080/omniva/invoices/generate/{amountOfInvoices}

## Get invoice by id
http://localhost:8080/omniva/invoices/{id}

## Get all invoices
NB! Very expensive operation

http://localhost:8080/omniva/invoices/

## Check if invoice is paid
http://localhost:8080/omniva/invoices/{id}/ispaid
