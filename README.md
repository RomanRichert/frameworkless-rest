# Frameworkless REST Microservice Example

This is a minimalist Java project that demonstrates a simple REST microservice **without any frameworks**.

## Overview

This microservice provides:
- **POST /labresults** – Create a new LabResult record
- **GET /labresults** – Retrieve all LabResult records
- **GET /labresults?patientId=ID** – Filter records by patient ID

It uses:
- **Java 8 standard libraries only**
- **PostgreSQL** as the database
- **JDBC** for database operations
- **HttpServer** from `com.sun.net.httpserver` for handling HTTP requests
- **Jakarta JSON** for JSON processing

## Purpose

The goal of this project is to demonstrate a clear and understandable example of a REST microservice, designed **without frameworks like Spring**.

## Setup

1. Create a `.env` file with your DB credentials:

   ```
   DB_URL=jdbc:postgresql://localhost:5432/labdb
   DB_USER=yourusername
   DB_PASSWORD=yourpassword
   ```

2. Prepare your PostgreSQL database with a table `lab_results`, or use the provided SQL migration scripts in `resources/migration/`.

3. Compile and run the project:

   ```bash
   javac -d out src/com/richert/frameworklessrest/*.java
   java -cp out com.richert.frameworklessrest.Main
   ```

## Example Request

```bash
curl -X POST http://localhost:8080/labresults   -H "Content-Type: application/json"   -d '{"patientId":"123", "result":4.7, "unit":"mmol/L"}'
```