# E-commerce Microservices Application

## Overview
This is a **Spring Boot** e-commerce application built using **Gradle** and a **microservice architecture**. The system consists of five distinct services:

- **Product Service**: Manages product details.
- **Order Service**: Handles order processing.
- **User Service**: Manages authentication and user-related operations using **JWT authentication**.
- **API Gateway**: Routes requests and secures endpoints.
- **Service Discovery**: Uses **Netflix Eureka** for client discovery.

## Technologies Used
- **Java (Spring Boot)** - Backend framework
- **Gradle** - Dependency management and build tool
- **Spring Cloud Netflix Eureka** - Service discovery
- **Spring Security & JWT** - Authentication & authorization
- **Spring Cloud Gateway** - API Gateway
- **PostgreSQL/MySQL** - Database for storing data
- **Docker** - Containerization (if applicable)

## Architecture Diagram
```
User -> API Gateway -> (User Service, Product Service, Order Service) -> Database
                         |
                         -> Service Discovery (Eureka)
```

## Setup and Installation
### Prerequisites
- Java 17+
- Gradle
- PostgreSQL/MySQL (configured in `application.properties`)
- Docker (optional for containerized deployment)

### Steps to Run Locally
1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/ecommerce-microservices.git
   cd ecommerce-microservices
   ```

2. Start the Eureka server:
   ```sh
   cd service-discovery
   ./gradlew bootRun
   ```

3. Start the API Gateway:
   ```sh
   cd api-gateway
   ./gradlew bootRun
   ```

4. Start the microservices (User, Product, Order):
   ```sh
   cd user-service
   ./gradlew bootRun
   ```
   ```sh
   cd product-service
   ./gradlew bootRun
   ```
   ```sh
   cd order-service
   ./gradlew bootRun
   ```

5. The application should now be running. Access it via:
   - **Eureka Dashboard**: `http://localhost:8761`
   - **API Gateway**: `http://localhost:8080`

## API Endpoints
| Service       | Endpoint                      | Method | Description                  |
|--------------|--------------------------------|--------|------------------------------|
| User         | `/auth/register`              | POST   | Register a new user         |
| User         | `/auth/login`                 | POST   | Authenticate and get JWT    |
| Product      | `/products`                    | GET    | Get list of products        |
| Order        | `/orders`                      | POST   | Place an order             |

## Authentication
- The **User Service** implements JWT-based authentication.
- Clients must provide a valid token in the `Authorization` header as `Bearer <token>`.
- API Gateway handles request validation before forwarding to microservices.

## Future Enhancements
- Implement **Kafka** for event-driven communication.
- Introduce **CI/CD pipeline** for automated deployment.


