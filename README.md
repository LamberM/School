# School Project

This project was implemented using Spring, Docker, mysql
## TODO
* available endpoints in README
* controller tests
* create new interesting function
## To start server:
#### !!! To run application you need to have mysql database !!!
I am using Docker where I create container with image.
#### To download mysql on docker:
~~~~
docker pull mysql
~~~~
#### To create container with image:
~~~~
docker run --name {database name} -e MYSQL_ROOT_PASSWORD={password} -d -p {deafult: 3306:3306} mysql
~~~~
#### Using IntelliJ Idea
* go to src/main/java/LamberM/school/SchoolApplication.java class
* run 'SchoolApplication'

Server will start at http://localhost:8080/

## Available endpoints:

## Response codes
#### 200 OK - when operation is successful
#### 400 Bad Request - when validation error occurs
#### 404 Not Found - when no data in mysql