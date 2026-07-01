# Restaurant_Management_System

Restaurant_Management_System is a restaurant management platform built using a microservice architecture. The system is developed with Spring Boot and Spring Cloud and provides functionality for managing restaurants, users, reservations, reviews, notifications, and personalized recommendations. The application is designed as a collection of independently deployable microservices that communicate through REST APIs and are integrated via an API Gateway.

## Exam Assignment

The microservice system was developed to satisfy the requirements of the project assignment.

The system:

- is developed using Spring Boot and Spring Cloud and includes all essential microservice components required for a production-ready architecture,
- contains independently deployable and containerized components (microservices, databases, and supporting services),
- consists of multiple business-oriented microservices, including:
  - User Service
  - Restaurant Service
  - Reservation Service
  - Review Service
  - Notification Service
  - Recommendation Service
- uses Spring Cloud Gateway as a single entry point for client requests,
- uses Eureka Service Discovery for dynamic service registration and discovery,
- supports synchronous communication between microservices using REST and OpenFeign,
- stores data in separate databases following the Database per Service pattern,
- includes centralized configuration using Spring Cloud Config,
- is fully containerized using Docker and Docker Compose,
- includes unit and integration tests for validating business logic and service communication,
- provides a clearly defined build and deployment process using Maven and Docker.

## Persistence

Data persistence is implemented using Spring Data JPA with PostgreSQL databases. Each microservice manages its own dedicated database, following the **Database per Service** pattern. This approach ensures loose coupling, independent data management, and improved scalability across the microservice architecture.

The persistence layer is based on Spring Data JPA, where entities represent the domain model and repositories provide an abstraction for performing database operations.

Each business microservice has its own database:

- User Service – PostgreSQL
- Restaurant Service – PostgreSQL
- Reservation Service – PostgreSQL
- Review Service – PostgreSQL
- Notification Service – PostgreSQL
- Recommendation Service – PostgreSQL

The image below shows the class diagram used in the project.


## Microservice Landscape

The Restaurant_Management_System is built using Spring Cloud and follows several well-established microservice architecture patterns. These patterns improve scalability, maintainability, service communication, and centralized system management.

| Design Pattern | Spring Cloud Component | Description |
|----------------|------------------------|-------------|
| **Service Discovery** | Netflix Eureka | Eureka Server acts as the service registry, allowing microservices to register themselves and discover other available services dynamically without hardcoded addresses. |
| **API Gateway** | Spring Cloud Gateway | The API Gateway serves as the single entry point for all client requests. It routes incoming requests to the appropriate microservices and hides internal service details from external clients. |
| **Centralized Configuration** | Spring Cloud Config Server | Configuration files for all microservices are managed centrally, allowing configuration changes without modifying each individual service. |
| **Inter-Service Communication** | Spring WebClient | Synchronous and non-blocking communication between microservices is implemented using WebClient, allowing services to fetch data from one another efficiently. |
| **Containerization** | Docker & Docker Compose | Every microservice and its database are containerized, enabling consistent deployment and simplified environment setup across different platforms. |
