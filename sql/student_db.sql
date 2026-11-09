CREATE DATABASE student_db;

\c student_db;

CREATE TABLE IF NOT EXISTS students (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    major VARCHAR(255),
    enrollment_year INTEGER
);