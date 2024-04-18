# CagnotteApp

CagnotteApp is a Spring Boot application designed to manage a customer loyalty program where customers earn rewards each time they make a purchase. The application allows customers to check the balance and availability of their rewards (cagnotte).

## Features

- Loyalty rewards accumulation
- Balance checking with conditions for availability
- RESTful API with detailed error handling

## Technologies

- **Spring Boot 3.2.4**: Framework for building stand-alone, production-grade Spring-based applications.
- **H2 Database**: In-memory database for rapid development and testing without the need for external installation.
- **Spring Data JPA**: Simplifies data access layers with Java Persistence API.
- **Lombok**: Java library tool to minimize boilerplate code.
- **JUnit**: For unit and integration testing.

## Prerequisites

To run this project, you need to have the following installed:
- JDK 17
- Maven 3.6 or above

## Running the Application

Clone the repository:

```bash
git clone https://github.com/salaheddine2011/cagnoteApp.git
cd cagnoteApp
mvn spring-boot:run
```

# CagnotteApp API Endpoints

CagnotteApp provides a couple of key RESTful endpoints to interact with the customer loyalty cagnotte system. Below is a description of these endpoints, including how to use them.

## API Endpoints Overview
### 1. Add a Client
- **Method**: POST
- **Endpoint**: `/api/clients`
- **Description**: Registers a new client in the system.
### 2. Show a Specific Client by ID
- **Method**: GET
- **Endpoint**: `/api/clients/{clientId}`
- **Description**: Retrieves a specific client's details by their ID.
### 3. Add Amount to Cagnotte
- **Method:** POST
- **Endpoint:** `/api/cagnottes/{clientId}`
- **Description:** Adds a specified amount to the client's cagnotte.

#### Parameters

- **Path Variables:**
  - `clientId` (Long): The ID of the client.

#### Request Body

- **`amount` (BigDecimal):** The amount to add to the cagnotte.

#### Responses

- **200 OK:** Successfully added the amount.
- **400 Bad Request:** Invalid data or request format.
- **404 Not Found:** Client not found.

### 4. Check Cagnotte Availability

- **Method:** GET
- **Endpoint:** `/api/cagnottes/{clientId}`
- **Description:** Checks if the client's cagnotte is available for use.

#### Parameters

- **Path Variables:**
  - `clientId` (Long): The ID of the client.

#### Responses

- **200 OK:** Returns a boolean indicating if the cagnotte is available.
- **400 Bad Request:** Invalid client ID.
- **404 Not Found:** Client not found.

## General Information

These endpoints are designed to facilitate the management of client rewards within the CagnotteApp system. By providing the necessary client ID and other required parameters, clients can easily manage and check their cagnotte status through a user-friendly API.

## Using the API

To use these endpoints, you'll need to ensure you have the correct client ID and the API is being called with valid HTTP methods. If you encounter any issues such as `404 Not Found` or `400 Bad Request`, please check the request data and the path variables to ensure accuracy.

