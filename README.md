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

## Accessing the H2 Database Console
The H2 console is an embedded web application that provides a convenient way to interact with the H2 database used by the CagnotteApp.

### How to Access
After starting the CagnotteApp, you can access the H2 Console at the following URL:

`http://localhost:8080/h2-console`

### Login Details
- JDBC URL: Typically, the JDBC URL is jdbc:h2:mem:testdb (or check your application's configuration).
- User Name: sa (default username if not configured otherwise)
- Password: (leave blank if not configured otherwise)
- Ensure that the JDBC URL matches the one configured in your application.properties or application.yml.

Important Notes
Security: The H2 console should not be enabled in production environments as it can pose a security risk.
Data Persistence: Data in the H2 database is not persistent and will be lost when the application is restarted, unless configured otherwise.




## CagnotteApp API Endpoints

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

#### Example Requests
1. Add a client
```bash
POST /api/clients
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "userName": "johndoe123"
}
```
2. Show a Specific Client by ID
`GET /api/clients/1`
3. Add Amount of a specific user to Cagnotte
```bash
POST /api/cagnottes/{clientId}/add
Content-Type: application/json

{
    "amount": 100.50
}

```
4. Check Cagnotte Availability

`GET /api/cagnottes/1/available`

- return true or false

## General Information

These endpoints are designed to facilitate the management of client rewards within the CagnotteApp system. By providing the necessary client ID and other required parameters, clients can easily manage and check their cagnotte status through a user-friendly API.


