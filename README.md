# School Project

This project was implemented using Spring, Hibernate, PostgreSQL, Docker
## TODO
* integration test
### Must have to start server:
* Docker
* IntelliJ Idea
## To start server:
#### Using IntelliJ Idea
* Use in terminal
~~~
docker-compose up
~~~
Server will start at http://localhost:8080

#### If you want to terminate the action
* Use in terminal:
~~~
docker-compose down
~~~
## Available endpoints:
http://localhost:8080/swagger-ui/index.html#/
## Response codes
#### 200 OK - when operation is successful
#### 400 Bad Request - when validation error occurs
#### 404 Not Found - when no data in database