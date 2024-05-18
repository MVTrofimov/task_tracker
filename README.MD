# Task tracker
![java](https://img.shields.io/badge/java-orange?style=flat-square&logo=java) ![spring](https://img.shields.io/badge/spring-green?style=flat-square&logo=spring) ![mongo](https://img.shields.io/badge/mongo-green?style=flat-square&logo=MongoDB) ![docker](https://img.shields.io/badge/docker-blue?style=flat-square&logo=Docker) ![maven](https://img.shields.io/badge/maven-yellow?style=flat-square&logo=Maven)
___
## Description:
__A reactive paradigma CRUD pet-project written by me as part of consolidating the knowledge gained in the course on creating applications using the Spring framework.__
___
## About project:
__Backend-part of the service for creating and monitoring the completion of tasks.__
___
## Technology stack:
* __Java(17)__
* __Lombok__
* __Spring Framework:__
    * Spring WEB-Flux
    * Spring JPA
    * Spring Security
* __Mapstruct__
* __Maven__
* __MongoDB__
* __Docker__
___
## API capabilities
*  find all users;
*  find a user by ID;
*  create a user;
*  update user information;
*  delete a user by ID;

*  find all tasks;
*  find a specific task by ID;
*  create a task;
*  update the task;
*  add an observer to a task;
*  delete a task by ID;
___
## Security
__Security in this application is implemented using Spring basic auth. The application supports three types of users: manager and user. Based on the rights granted to the user, the user can perform only those actions that are allowed to the user with this level of rights.__
___
## Docker
__The application database is deployed on docker using docker-compose.yml file.__