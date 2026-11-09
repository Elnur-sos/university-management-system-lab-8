CREATE DATABASE course_db;

\c course_db;

CREATE TABLE IF NOT EXISTS courses (
    id BIGSERIAL PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL,
    course_code VARCHAR(255) UNIQUE NOT NULL,
    credits INTEGER,
    instructor VARCHAR(255)
);