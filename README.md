# University Management System

## 1. Project Description

University Management System is a Spring Boot REST API application that manages students, courses, and grades using three independent PostgreSQL databases.

## 2. Technologies Used

- Java 21
- Spring Boot 3.5.5
- Spring Data JPA
- PostgreSQL
- Maven
- REST API
- Postman

## 3. Database Architecture

The application uses three separate PostgreSQL databases:

1. **student_db** – stores student information.
2. **course_db** – stores course information.
3. **grade_db** – stores student grades.

Each database has its own DataSource, EntityManagerFactory, and Transaction Manager.

## 4. Project Features

- Create, read, update, and delete students.
- Create, read, update, and delete courses.
- Add and view student grades.
- Search grades by student ID and course ID.
- Validate student and course IDs.
- Prevent duplicate student emails and course codes.
- Validate scores between 0 and 100.
- Generate student transcripts.
- Calculate average student scores.
- Handle application errors.

## 5. API Endpoints

### Students

| Method | Endpoint |
|---|---|
| GET | /api/students |
| GET | /api/students/{id} |
| POST | /api/students |
| PUT | /api/students/{id} |
| DELETE | /api/students/{id} |
| GET | /api/students/{id}/transcript |

### Courses

| Method | Endpoint |
|---|---|
| GET | /api/courses |
| GET | /api/courses/{id} |
| POST | /api/courses |
| PUT | /api/courses/{id} |
| DELETE | /api/courses/{id} |

### Grades

| Method | Endpoint |
|---|---|
| GET | /api/grades |
| GET | /api/grades/{id} |
| GET | /api/grades/student/{id} |
| GET | /api/grades/course/{id} |
| POST | /api/grades |
| DELETE | /api/grades/{id} |

## 6. Database Setup

1. Install PostgreSQL.
2. Create three databases:
    - student_db
    - course_db
    - grade_db
3. Execute the SQL scripts located in the `sql` directory:
    - student_db.sql
    - course_db.sql
    - grade_db.sql

## 7. Configuration

1. Open `src/main/resources`.
2. Copy `application.properties.example`.
3. Create a file named `application.properties`.
4. Configure your PostgreSQL username and passwords.
5. Make sure the three database connections are configured correctly.

**Important:** Do not upload your real database passwords to GitHub.

## 8. How to Run the Project

1. Install Java 21 and PostgreSQL.
2. Create and configure the three databases.
3. Configure `application.properties`.
4. Open the project in IntelliJ IDEA.
5. Run the main Spring Boot application class.
6. The application starts at:

`http://localhost:8089`

## 9. API Testing

The REST API endpoints were tested using Postman.

The Postman collection is available in the `postman` directory.

Screenshots of successful API requests are available in the `screenshots` directory.

## 10. Project Structure

```text
UniversityManagementSystem
│
├── src
│   └── main
│       ├── java
│       └── resources
│           ├── application.properties
│           └── application.properties.example
│
├── sql
│   ├── student_db.sql
│   ├── course_db.sql
│   └── grade_db.sql
│
├── postman
│   └── University Management System API.postman_collection.json
│
├── screenshots
│
├── README.md
└── pom.xml
```