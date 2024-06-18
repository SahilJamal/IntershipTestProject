# TestApplication

## Overview

Provide a brief description of the project and its main features.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.x
- MySQL Server (optional, if using a database)

## Installation

1. Clone the repository:

https://github.com/SahilJamal/IntershipTestProject

2. Build the project using Maven:

mvn clean install

## Configuration

1. Configure database connection:

- Open `application.properties` located in `src/main/resources`
- Set `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` according to your database configuration.

## Running the Application

- Run the application using Maven:

  mvn spring-boot

- Alternatively, you can run the main class `Application.java` from your IDE.

## Usage

User Registration: Clients can register new users by sending a POST request to /api/user/register. If successful, the user is created and returned with a success message (HttpStatus.CREATED). If validation fails or a user with the same email exists, appropriate error messages are returned (HttpStatus.BAD_REQUEST).

User Fetching: Clients can fetch user details by providing a valid username via a GET request to /api/user/fetch. If the username exists, the user details are returned (HttpStatus.OK). If the username is invalid or not found, appropriate error messages are returned (HttpStatus.NOT_FOUND).
