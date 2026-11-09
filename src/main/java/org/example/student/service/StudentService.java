package org.example.student.service;

import org.example.student.entity.Student;
import org.example.student.repository.StudentRepository;
import org.example.exception.ConflictException;
import org.example.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student createStudent(Student student) {

        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists");
        }

        return studentRepository.save(student);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public Student updateStudent(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        if (!existingStudent.getEmail().equals(student.getEmail())
                && studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists");
        }

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setMajor(student.getMajor());
        existingStudent.setEnrollmentYear(student.getEnrollmentYear());

        return studentRepository.save(existingStudent);
    }

    @Transactional(transactionManager = "studentTransactionManager")
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}