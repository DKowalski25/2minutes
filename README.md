# NoteTaking Application

This is a simple NoteTaking application built with Spring Boot. It allows users to register, log in, and manage their goals (notes). The application uses PostgreSQL as the database and provides RESTful APIs for interaction.

## Table of Contents

- Features
- Prerequisites
- Installation
- Configuration
- Running the Application
- API Documentation
- Docker Setup
- Contributing
- License

## Features

- User registration and authentication.
- Create, read, update, and delete goals (notes).
- RESTful APIs for managing goals.
- Swagger UI for API documentation.
- Docker support for easy deployment.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java 17 or higher.
- Maven for building the project.
- Docker (optional, for running the application in a container).
- PostgreSQL (optional, if not using Docker).

## Installation

### Clone the repository:

```bash
git clone https://github.com/yourusername/notetaking.git
cd notetaking
```

### Install dependencies:

```bash
mvn clean install
```

## Configuration

### Create a .env file:

Create a `.env` file in the root directory of the project with the following content:

```env
DB_URL=jdbc:postgresql://localhost:5432/notetaking
DB_USERNAME=yourusername
DB_PASSWORD=yourpassword
JWT_SECRET=your_jwt_secret_key
```

### Update application.properties (optional):

If you don't use `.env`, you can configure the database and JWT settings directly in `src/main/resources/application.properties`.

## Running the Application

### Without Docker

#### Start PostgreSQL:

Ensure PostgreSQL is running on your machine.

#### Run the application:

```bash
mvn spring-boot:run
```

The application will start on [http://localhost:8080](http://localhost:8080).

### With Docker

#### Build the Docker image:

```bash
docker build -t notetaking .
```

#### Run the Docker container:

```bash
docker run -p 8080:8080 notetaking
```

The application will start on [http://localhost:8080](http://localhost:8080).

## API Documentation

The application provides Swagger UI for API documentation. After starting the application, you can access it at:

```
http://localhost:8080/swagger-ui.html
```

### Available Endpoints

#### Authentication

**POST** `/api/auth/register`: Register a new user.

**Request Body:**

```json
{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123"
}
```

#### Goals

**GET** `/api/goals`: Get all goals for the authenticated user.

**GET** `/api/goals/type/{type}`: Get goals by type (e.g., daily, weekly, monthly).

**POST** `/api/goals`: Create a new goal.

**Request Body:**

```json
{
  "description": "Finish the project",
  "deadline": "25.12.2025",
  "type": "monthly"
}
```

**DELETE** `/api/goals/{id}`: Delete a goal by ID.

## Docker Setup

### Using Docker Compose

#### Create a `docker-compose.yml` file:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:latest
    environment:
      POSTGRES_DB: notetaking
      POSTGRES_USER: yourusername
      POSTGRES_PASSWORD: yourpassword
    ports:
      - "5432:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

  app:
    image: notetaking
    environment:
      DB_URL: jdbc:postgresql://postgres:5432/notetaking
      DB_USERNAME: yourusername
      DB_PASSWORD: yourpassword
      JWT_SECRET: your_jwt_secret_key
    ports:
      - "8080:8080"
    depends_on:
      - postgres

volumes:
  postgres-data:
```

#### Start the application with Docker Compose:

```bash
docker-compose up
```

The application will start on [http://localhost:8080](http://localhost:8080), and PostgreSQL will run in a separate container.

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature/YourFeatureName`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeatureName`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Acknowledgments

- Spring Boot for the awesome framework.
- PostgreSQL for the reliable database.
- Swagger for API documentation.

