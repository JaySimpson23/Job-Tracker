# Job-Tracker
> 🚧 This project is currently in progress 🚧
> 
A full-stack application for tracking and managing job applications. The backend is a REST API built with Java, Spring Boot, Maven, and PostgreSQL, with full user authentication and security via Spring Security and JWT. The frontend is a React app built with Node.js.

## Tech Stack

### Backend
- Java 21 / Spring Boot
- Spring Data JPA / Hibernate
- Spring Security / JWT
- PostgreSQL
- Maven
  
### Frontend
- React
- Node.js / npm
  
## Getting Started

### Prerequisites
- Java 21
- Node.js / npm
- PostgreSQL running locally
  
### Installation
Clone the repo

**Backend**
1. Configure your database credentials in application.properties
2. Add your JWT secret key to application.properties
3. Run with mvn spring-boot:run
   
**Frontend**
1. cd frontend
2. npm install
3. npm start
   
## Features
- User registration and login with JWT authentication
- Create, read, update, and delete job applications
- Secure endpoints — only authenticated users can access their own data
- React frontend for managing applications (in progress)
  
## API Endpoints

### Auth
- POST   /auth/register   Register a new user
- POST   /auth/login      Login and receive JWT token
### Job Applications
- GET    /jobs         Get all applications for logged in user
- GET    /jobs/{id}    Get one application
- POST   /jobs         Create a new application
- PUT    /jobs/{id}    Update an application
- DELETE /jobs/{id}    Delete an application
