# Client API

The **Client API** provides endpoints for managing client information. This API allows you to create, retrieve, update, and delete clients.

## Project Overview

This project is a robust application built with a range of modern technologies. Below is an overview of the technologies and tools used in this project.

## Architecture

The Client API uses the Ports and Adapters (Hexagonal) Architecture. This design separates the core business logic from external systems and dependencies, allowing for better scalability and easier maintenance. The application is organized into ports (interfaces) and adapters (implementations).

## Technologies Used

### Java 17

- The project is developed using Java 17, which provides modern language features, performance improvements, and enhanced security.

### MongoDB Atlas

- MongoDB Atlas is used as the database solution for storing and managing data. It is a fully managed cloud database service that ensures high availability and scalability.

### Resilience4j

- Resilience4j is implemented for resilience and fault tolerance in the application. It provides features such as Circuit Breaker and Retry, which help in managing failures and ensuring application reliability.

### Swagger

- Swagger is used for API documentation. It provides interactive API documentation and helps in testing and understanding the API endpoints.

### Spring Boot

- Spring Boot is the core framework used to build the application. It simplifies the development of production-ready applications with minimal configuration.

### Unit Testing

Unit tests are implemented to ensure the correctness of the application's components. The tests cover some aspects of the APIs. I used JUnit and Mockito for the testes.
## Getting Started

### Prerequisites

- Java 17
- Maven 3.8.x
- MongoDB Atlas
- Swagger UI

### Setup

1. **Clone the Repository:**

2. **mvn clean install:**

3. To run tests: **mvn test**
