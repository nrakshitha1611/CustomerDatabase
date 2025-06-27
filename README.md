# Customer Database API (Spring Boot + Docker + Shell CLI)

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Build & Run the app](#build--run-the-app-via-docker)
- [API Endpoints](#api-endpoints)
- [Verify the API is running](#verify-the-api-is-running)
- [Testing the API via Command Line (CLI)](#testing-the-api-via-command-line-cli)
- [Technologies Used](#technologies-used)
---
## Overview
*This project is a **Spring Boot-based REST API** for managing customer records, containerized with Docker and supported by an interactive **Shell CLI client** (`CustomerCli.sh`) to test API endpoints easily.*
---

##  Project Structure
```
├── customer/ # Spring Boot REST API application
├── src/main/java/com/api/customer/
│   ├── CustomerApiApplication.java
│   ├── CustomerController.java
│   ├── service/CustomerService.java
│   ├── repository/CustomerRepository.java
│   ├── entity/Customer.java
├── src/main/resources/
│   ├── application.properties
│   ├── application.yml
└── src/test/java/com/api/customer/
    ├── CustomerIntegrationTest.java
    ├── CustomerServiceTest.java
└── README.md
```

---

## Features

- Create, Read, Update, Delete (CRUD) operations for Customers
- Spring Boot + Spring Data JPA + H2 In-Memory DB
- RESTful API with proper validations
- Lightweight Dockerized deployment
- Shell-based Command Line Interface for testing APIs

---

## Prerequisites

- Java 17 or above
- Maven
- Docker Desktop (Linux containers mode)
- Bash shell (macOS/Linux/Git Bash for Windows)

---

## Build & Run the App (via Docker)

### 1. Navigate to the project folder:

```bash
cd CustomerDatabase/customer
```
### 2. Build the Docker Image:
```bash
docker build -t customer-app .
```
### 3. Run the Docker container:
```bash
docker run -d -p 8080:8080 --name customer customer-app
```
---

## Build & Run the Code Locally (Without Docker)

If you prefer to run the application directly on your machine instead of using Docker, follow these steps:

### 1. **Clone the Repository**

```bash
git clone https://github.com/nrakshitha1611/CustomerDatabase.git
cd CustomerDatabase/customer
```

### 2. **Check Prerequisites**
- Make sure you have Java 17+ and Maven installed.
- You can verify your Java version with:
```bash
java -version
mvn -version
```
### 3. Build the application
```bash
mvn clean install
```
This will create a JAR file in the target/ directory.
### 4. Run the Application
You can run the app using Maven:
```
mvn spring-boot:run
```
Or directly using the JAR file:
```
java -jar target/customer-0.0.1-SNAPSHOT.jar
```
If you prefer testing locally on your IDE, run the application as a Spring app.

## Verify the API is running
- Base URL: `http://localhost:8080/actuator/health`
- H2 Console (optional): `http://localhost:8080/h2-console`

---

## API Endpoints:
```
| Method | Endpoint              | Description         |
| ------ | --------------------- | ------------------- |
| GET    | /api/customers        | Get all customers   |
| GET    | /api/customers/{id}   | Get customer by ID  |
| POST   | /api/customers        | Create new customer |
| PUT    | /api/customers/{id}   | Update existing     |
| DELETE | /api/customers/{id}   | Delete by ID        |
```
---

## Testing the API via Command Line (CLI)
Use the provided `CustomerCli.sh` file to interact with the API directly from the terminal.

### Steps:
Ensure your system has Gitbash(Windows) installed
### 1. Make the script executable:
```bash
chmod +x CustomerCli.sh
```
### 2. Run the CLI:
```bash
./CustomerCli.sh
```
### 3. Menu options will appear
```bash
=== Customer CLI ===
1. Create Customer
2. Get Customer by ID
3. Exit
```
### 4. Example input:
```bash
First Name: John
Last Name: Doe
Email Address: john@example.com
```
### 5. Expected Output:
```bash
{"id":"...","firstName":"John",...}
Status: 200
```
---
## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory: Active only when the application is running)
- Docker
- Bash scripting (curl based CLI)

---
## Contact
Maintained by [Rakshitha Nagaraj](https://github.com/nrakshitha1611?tab=repositories)
---
**Note: The project was timeboxed and completed in approx 2-3hrs spread across 3 days**



