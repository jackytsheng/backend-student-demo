
## Simple Students app with Unit tests

1. This project can be interacted with a frontend form, it can be found [here](https://github.com/jackytsheng/Jquery-Ajax-Learning). 
2. This project can be used to communicate with another [simple micro service](https://github.com/jackytsheng/node-demo) that used Node.js via **RabbitMQ**.

### How to run it?

If you have gradle installed and under target folder, run the following command:

``` Bash
$ ./gradlew bootRun
```

After the server is running, in any browser go to

```
http://localhost:8080/
```


### How to interact with it?

Open [Postman](https://www.postman.com/). Then Import the `Simple_Student_REST_Api.postman_collection.json` under the root directory.

Or Check out the [Postman documentation for this project.](https://documenter.getpostman.com/view/9118370/SztJzPVj)


### How to check H2 In memory DataBase?
In memory H2 Data base console can be accessed using the following link:
```
http://localhost:8080/h2-console
```

H2 Configuration as follow:

```
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### How to enable Rabbit MQ broker ?

This project is using [Docker](https://hub.docker.com/_/rabbitmq) for interaction

After pulling image

run container

```bash
$ docker run -d -p 5672:5672 --name rabbit-test rabbitmq:latest
```
you can also enable management console:

login in as `username: guest  password: guest`
```bash
$ docker run -d --name some-rabbit -p 15672:15672 rabbitmq:3-management
```
or combine run

```bash
$ docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management
```

Upon creation of student, a message will be sent to the Message broker.
### What is in this project ?
This is a simple *Restful Api* project.

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
- H2 In memory database 
