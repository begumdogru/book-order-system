# ReadingIsGood - Online Book Retail System

![ReadingIsGood Logo](https://example.com/readingisgood_logo.png)

ReadingIsGood is an online book retail firm that operates exclusively on the Internet. Our main goal is to deliver books from our centralized warehouse to our customers within the same day. Stock consistency is our top priority, ensuring that customers always find the books they want in stock.

## Table of Contents
- [Base Requirements](#base-requirements)
- [Must-Have Requirements](#must-have-requirements)
- [Nice to Have's](#nice-to-haves)
- [How to Submit](#how-to-submit)

## Base Requirements

To run the ReadingIsGood system, you must have the following technologies and components in place:

- **Java**: Minimum SDK version 11.
- **Spring Framework**: Spring Boot is recommended.
- **Database**: You can use either a relational database (H2 is an option) or a non-relational database.
- **Restful Endpoints**: Implement RESTful API endpoints.
- **Clean Code**: Ensure clean and maintainable code.
- **Testing**: Provide unit and integration tests with at least 50% functionality coverage.
- **Documentation**: Include brief design documentation, tech stack details, and instructions on how to start the project. If necessary, provide credentials and highlight any assumptions.
- **Containerization**: Containerize the application using a tool like Docker.

## Must-Have Requirements

The following components are essential for the ReadingIsGood system:

### Customer Controller
- Register new customers.
- Query all orders of a customer, with paging support.

### Book Controller
- Persist new books.
- Update book stock.

### Order Controller
- Persist new orders, including order statuses.
- Update stock records.
- Handle concurrent purchasing of the last book.

### Order Controller
- Query orders by ID.
- List orders by date interval (start date - end date).

### Statistics Controller
- Provide customer's monthly order statistics, including:
  - Total order count
  - Total amount of all purchased orders
  - Total count of purchased books

The statistics endpoint will supply data for UI components displaying monthly statistics.

### Validations
- Ensure the system is error-proof with appropriate validations.

### Authentication
- Secure endpoints, for example, using bearer tokens.

### Responses
- Define success and error response models and use them consistently.

### Postman
- Prepare Postman requests and share them to facilitate testing.

## Nice to Have's

These are optional but recommended features to enhance the system:

### Logging
- Log all changes on entities, including which user made specific changes and when.

### Open API Specification
- Implement an OpenAPI specification (Swagger) for easy API documentation.
