# School Project

This project was implemented using Spring, Hibernate, PostgreSQL, Docker
## TODO
* available endpoints in README
* controller tests
* integration tests
* create new interesting function
### Must have to start server:
* Docker
* IntelliJ Idea
## To start server:
#### Using IntelliJ Idea
1) Create target file using:
~~~
mvn clean package
~~~
2) Copy this text, use terminal and paste:
~~~
docker-compose up
~~~
Server will start at http://localhost:8080/

#### If you want to terminate the action
1) Use terminal and click on your keyboard ctrl + c
2) Copy this text, use terminal and paste:
~~~
docker-compose down
~~~
## Available endpoints:

## Response codes
#### 200 OK - when operation is successful
#### 400 Bad Request - when validation error occurs
#### 404 Not Found - when no data in database