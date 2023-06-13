# Rozklad
Spring Boot + Vue.js CRUD demo application for university to demonstrate how to develop fullstack application. The application have different entities with one to many relations.

## What's inside 
Project have backend and frontend. 
- Backend based on Spring Boot 2, Spring Data JPA
- Frontend based on Vue.js, Vuetify, axios and other 
- For database I used PostgreSQL

## Requirements 
- JDK 11 or higher
- Spring Boot 2
- node v18.12
- npm v8.19
- PostgreSQL for data storage

## Getting started
To start application you need Node.js, npm and PostgreSQL installed

### Database configuration
Bofore start application you need to configure PostgreSQL database conection voa *application.properties* file:

```
spring.datasource.url=jdbc:postgresql://localhost/[name of db]
spring.datasource.username=[name of postgres]
spring.datasource.password=[password to postgres]
```

### Build application 
To build application you need run command 
``` 
mvn clean install
```
It will generate source files for backend and frontend

### Start application
To start the application you need: 
1. Run ```mvn spring-boot:run```
2. After Spring execution you need to run in *package.json* sript ```vue-cli-service serve```
3. Can access to application via http://localhost:3000/
