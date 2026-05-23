# Todo App — Backend

REST API backend for the Todo Application, built with **Spring Boot 3.2.5** and **Java 21**.  
The frontend repo is here → [todo-app (React)](https://github.com/Darshan-11-create/todo-app)  
Live demo → [https://darshan-11-create.github.io/todo-app/](https://darshan-11-create.github.io/todo-app/)

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2.5 |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Email | SendGrid |
| Build Tool | Maven |
| Containerisation | Docker |

---

## Features

- Create, read, update, and delete tasks via RESTful APIs
- Mark tasks as complete / incomplete
- Input validation and structured exception handling
- Email notifications via SendGrid integration
- Dockerised for easy deployment

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/todos` | Fetch all tasks |
| POST | `/api/todos` | Create a new task |
| PUT | `/api/todos/{id}` | Update a task |
| PATCH | `/api/todos/{id}/complete` | Mark task as complete |
| DELETE | `/api/todos/{id}` | Delete a task |

---

## Getting Started

### Prerequisites
- Java 21
- Maven
- MySQL

### Setup

```bash
# Clone the repo
git clone https://github.com/Darshan-11-create/todo_backend.git
cd todo_backend
```

Configure your database in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tododb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

```bash
# Run the application
./mvnw spring-boot:run
```

The server starts at `http://localhost:8080`

### Run with Docker

```bash
docker build -t todo-backend .
docker run -p 8080:8080 todo-backend
```

---

## Project Structure

```
src/
└── main/
    └── java/com/example/ToDo/
        ├── controller/    # REST controllers
        ├── service/       # Business logic
        ├── repository/    # JPA repositories
        └── model/         # Entity classes
```

---

## Author

**Bommineni Darshan Kumar**  
[GitHub](https://github.com/Darshan-11-create) · [LinkedIn](https://linkedin.com/in/darshan211) · [LeetCode](https://leetcode.com/Darshan_11_2)
