# Backend Challenge

## Overview

This project is a backend challenge that demonstrates proficiency in Java 17, MongoDB Atlas, Resilience4j, Swagger, and Spring Boot. The challenge involves developing two APIs: **Client API** and **Insurance API**, with a focus on creating robust and maintainable solutions. The architecture employed is Ports and Adapters, also known as Hexagonal Architecture.

## Technologies Used

- **Java 17**: The project is developed using Java 17, which provides enhanced language features and performance improvements.
- **MongoDB Atlas**: A cloud-based NoSQL database used for storing and managing data.
- **Resilience4j**: Provides fault tolerance capabilities including Circuit Breaker and Retry mechanisms to ensure resilience in API interactions.
- **Swagger**: Used for API documentation and testing, allowing easy exploration and understanding of the API endpoints.
- **Spring Boot**: The framework used for building and running the APIs, providing a robust infrastructure and ease of development.

## Architecture

The project follows the **Ports and Adapters** (Hexagonal) architecture pattern. This design ensures that the core business logic is decoupled from external systems such as databases and APIs. Key components include:

- **Ports**: Interfaces that define the input and output operations of the application.
- **Adapters**: Implementations of the ports, which interact with external systems or frameworks (e.g., REST controllers, database repositories).

## APIs

### Client API

- **Create Client**
  - **Endpoint**: `POST /api/clients`
  - **Description**: Creates a new client.
  - **Request Body**: Client object
  - **Response**: Client object

- **Get Client By ID**
  - **Endpoint**: `GET /api/clients/{id}`
  - **Description**: Retrieves a client by ID.
  - **Response**: Client object or 404 Not Found

- **Update Client**
  - **Endpoint**: `PUT /api/clients/{id}`
  - **Description**: Updates an existing client.
  - **Request Body**: Updated Client object
  - **Response**: Updated Client object or 404 Not Found

- **Delete Client**
  - **Endpoint**: `DELETE /api/clients/{id}`
  - **Description**: Deletes a client by ID.
  - **Response**: 204 No Content or 404 Not Found

### Insurance API

- **Simulate Insurance**
  - **Endpoint**: `GET /api/insurance/simulate`
  - **Description**: Simulates an insurance based on the provided insurance type.
  - **Request Parameters**: `insuranceType`
  - **Response**: Insurance object

- **Take Out Insurance**
  - **Endpoint**: `POST /api/insurance/takeout`
  - **Description**: Takes out insurance for a client.
  - **Request Parameters**: `clientId`, `insuranceType`
  - **Response**: InsuranceContract object or 404 Not Found

## Configuration

### Application Properties

The project uses `application.properties` for configuration. Key settings include:

- **MongoDB Connection**: Connection details for MongoDB Atlas.
- **Resilience4j Configuration**: Settings for Circuit Breaker and Retry mechanisms.

Example configuration:
```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@cluster.mongodb.net/mydatabase
resilience4j.circuitbreaker.instances.clientServiceCB.registerHealthIndicator=true
resilience4j.retry.instances.clientService.retryOnExceptions=java.net.ConnectException
