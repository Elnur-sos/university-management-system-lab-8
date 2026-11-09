CREATE DATABASE grade_db;

\c grade_db;

CREATE TABLE IF NOT EXISTS grades (
    id BIGSERIAL PRIMARY KEY,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    score INTEGER NOT NULL CHECK (score >= 0 AND score <= 100),
    letter_grade VARCHAR(2),
    graded_at TIMESTAMP
);