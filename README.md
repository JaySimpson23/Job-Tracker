# Job-Tracker

A fullstack application built with Spring Boot/PostgreSQL backend and with a React frontend that features full user authentication and security using Spring Security and JWT. Also allows users to track and manage job applications.

## Tech Stack (Backend)
- Java 21 / Spring Boot
- Spring Data JPA / Hibernate
- Spring Security / JWT
- PostgreSQL
- Maven
- Docker

## Tech Stack (Frontend)
- React JS
- Node JS
- React Router
- Vite

## Getting Started

### Prerequisites (Backend)
- Java 21
- PostgreSQL running locally
- Docker for containerization

### Prerequisites (Frontend)
- React 
- Node
- Vite

### Installation (Backend)
1. Clone the repo
2. Cd into job-tracker/BackEnd folder
3. Configure your database credentials in application.properties
4. Add your JWT secret key to application.properties
5. Run with mvn spring-boot:run

### Installation (Frontend)
1. Cd into job-tracker/Frontend folder
2. Run npm install for dependencies
3. npm run dev

### Installation (Docker - Recommended)
1. Clone the repo
2. Copy .env.example to .env
3. Fill in with the correct credentials
4. Run docker compose up --build

## Features
- User registration and login with JWT authentication
- Create, read, update, and delete job applications
- Secure endpoints — only authenticated users can access their own data
- Validates that user is created with the required fields
- Exception handling for general business logic (Duplicate names, fields not fill out correctly, user and applications not found, attempts at  accessing another users data)
- React frontend supporting logging in, registering, create applications and editing applications as well as logging out.

## API Endpoints

### Auth
- POST   /api/users/register   Register a new user
- POST   /api/auth/login      Login and receive JWT token

### Job Applications
- GET    /api/applications        Get all applications for logged in user
- GET    /api/applications/{id}    Get one application
- POST   /api/applications         Create a new application
- PUT    /api/applications/{id}    Update an application
- DELETE /api/applications/{id}    Delete an application
