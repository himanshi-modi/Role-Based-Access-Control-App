# RBAC Authentication System (Spring Boot + React)

## Overview
This project is a Full-Stack Authentication and Role-Based Access Control (RBAC) system built using Spring Boot for the backend and React with TypeScript for the frontend.

The application allows users to:
- Register and login
- Receive JWT-based authentication tokens
- Use Two-Factor Authentication (2FA)
- Access content based on assigned roles
- View different dashboards depending on their authorization level

The system demonstrates secure authentication, role-based authorization, and protected frontend routes.

---

## Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Two-Factor Authentication (2FA)
- Spring Data JPA
- Hibernate
- MapStruct
- Lombok
- Maven
- Swagger / OpenAPI

### Frontend
- React
- TypeScript
- Vite
- React Router
- Axios
- React Query
- React Hook Form
- TailwindCSS

### Database
- PostgreSQL / MySQL

---

## Project Structure

project-root
│
├── src/                # Spring Boot backend
│
├── frontend/           # React + TypeScript frontend
│   ├── src
│   ├── package.json
│   └── vite.config.ts
│
└── README.md

---

## Features

### Authentication
- User Registration
- User Login
- JWT Token Generation
- Two-Factor Authentication (2FA)
- Token-based authentication for protected endpoints

### Role-Based Authorization

Two roles are supported:

USER  
- Can access `/api/user`
- Can view user-level content

ADMIN  
- Can access `/api/admin`
- Can view admin-level content

### Public Access
`/api/public` is accessible by everyone.

---

## Backend API Endpoints

### Authentication

POST api/auth/signup
POST api/auth/login  

### RBAC Endpoints

GET /api/public → Accessible by everyone  
GET /api/user → Accessible by USER role  
GET /api/admin → Accessible by ADMIN role  

JWT tokens must be sent in request headers:

Authorization: Bearer <token>

---

## Frontend Pages

The frontend application contains the following pages:

Login Page  
Allows existing users to authenticate.

Signup Page  
Allows new users to register.

Public Dashboard  
Accessible after login and contains navigation options.

User Dashboard  
Displays content accessible only by users with USER role.

Admin Dashboard  
Displays content accessible only by users with ADMIN role.

---

## Frontend Functionality

The frontend implements:
- Login & Registration forms
- JWT storage in localStorage
- Attaching JWT token to API requests
- Protected routes
- Conditional rendering based on user role

## Backend Functionalities

- **User Registration (Signup)**  
  Allows new users to register using name, email, and password. Passwords are securely hashed before being stored in the database.

- **User Authentication (Login)**  
  Users can log in using email and password. Credentials are verified using Spring Security’s `AuthenticationManager` and `UserDetailsService`.

- **JWT Token Generation**  
  After successful login, the system generates a signed JSON Web Token (JWT) containing user identification and expiration information.

- **JWT-Based Authorization**  
  Protected APIs require a valid JWT token in the request header to allow access.

- **Custom JWT Authentication Filter**  
  Intercepts incoming requests, validates the JWT token, extracts the user details, and sets authentication in the Spring Security SecurityContext.

- **Role-Based Access Control (RBAC)**  
  Users are assigned roles and permissions that determine what resources or APIs they can access.

- **Secure Password Handling**  
  Passwords are encrypted using `BCryptPasswordEncoder` to ensure secure credential storage and verification.

- **API Documentation with Swagger**  
  Integrated Swagger UI allows interactive testing and documentation of all backend APIs.
  
## Running the Project

### Backend Setup

1. Clone the repository

git clone <your-repository-link>

2. Navigate to project

cd project-root

3. Run the application

mvn spring-boot:run

Backend runs on:

http://localhost:8080

---

### Frontend Setup

1. Navigate to frontend folder

cd frontend

2. Install dependencies

npm install

3. Start the development server

npm run dev

Frontend runs on:

http://localhost:5173

---



---

## Author

Himanshi Modi

GitHub:  
https://github.com/himanshi-modi
