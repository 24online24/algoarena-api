# AlgoArena

AlgoArena is a platform for competitive programming. It is a place where you can show your skills and compete with others. It is also a place where you can learn from others and improve your skills.

This is the REST API that is used by the AlgoArena platform. It handles the authentication, the user management, the problem management and the submission of solutions.

## Features

- User management
- Problem management
- Submission of solutions
- Authentication

The API

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need to have installed the following software:

- Java 17
- MySQL
- Docker & Docker Compose

### Installing

First, you need to make sure the Judge0 instance is running. You can do that by running the following command:

```bash
docker-compose up -d
```

Complete the .env with the template provided in the .env-template file. Then complete the application.properties file in the src/main/resources directory with the model provided in applicaion.properties-template.

Then, you can start the application like any other Spring Boot application. You can do that by running the following command:

```bash
./mvnw spring-boot:run
```
