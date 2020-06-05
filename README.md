
Simple Students app with Unit tests
=========================


# How to run it?

If you have gradle installed and under target folder, run the following command:

     ./gradlew bootRun



After the server is running, go to

```
http://localhost:8080/
```

# How to interact with it?

Open Postman and import the `Students_Collection.json`

Or Check out the Documentation for Postman interaction with this project [here](https://documenter.getpostman.com/view/9118370/SztJzPVj)

The backend is done with 
- Spring Boot 
- Spring MVC architecture, 
- Spring Data JPA
- H2 In memory database

With unit tests written for each layer using
- Mockito
- MockMvc
- Junit


There is embedded tomcat and embedded

There is a standard set of libs, like guava, joda-time and so on.
