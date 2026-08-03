# Job-Tracker
> 🚧 This project is currently in progress 🚧

A backend REST API built with Java, Spring Boot, Maven, and PostgreSQL that allows users to track and manage job applications. Features full user authentication and security using Spring Security and JWT.

## Tech Stack
- Java 21 / Spring Boot
- Spring Data JPA / Hibernate
- Spring Security / JWT
- PostgreSQL
- Maven

## Getting Started

### Prerequisites
- Java 21
- PostgreSQL running locally

### Installation

Clone the repo
Configure your database credentials in application.properties
Add your JWT secret key to application.properties
Run with mvn spring-boot:run

## Features
- User registration and login with JWT authentication
- Create, read, update, and delete job applications
- Secure endpoints — only authenticated users can access their own data

## API Endpoints

### Auth
- POST   /auth/register   Register a new user
- POST   /auth/login      Login and receive JWT token

### Job Applications
- GET    /jobs         Get all applications for logged in user
- GET    /jobs/{id}    Get one application
- POST   /jobs         Create a new application
- PUT    /jobs/{id}    Update an application
- DELETE /jobs/{id}    Delete an application
