
Simple Students app with Unit tests
=========================


## How to run it?

If you have gradle installed and under target folder, run the following command:

``` Bash
$ ./gradlew bootRun
```

After the server is running, in any browser go to

```
http://localhost:8080/
```

In memory H2 Data base console can be accessed using the following link:
```
http://localhost:8080/h2-console
```

## How to interact with it?

Open [Postman](https://www.postman.com/). Then Import the `Students_Collection.json` .

Or Check out the documentation for Postman interaction with this project [here](https://documenter.getpostman.com/view/9118370/SztJzPVj)

The backend is done with:
- Spring Boot 
- Spring MVC architecture 
- Spring Data JPA
- H2 In memory database

Authentication feature including:
- Jwt token

With unit tests written for each layer using:
- Mockito
- MockMvc
- Junit
