
package org.example.grade.service;

import org.example.grade.entity.Grade;
import org.example.grade.repository.GradeRepository;
import org.example.student.repository.StudentRepository;
import org.example.course.repository.CourseRepository;
import org.example.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeService(
            GradeRepository gradeRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Grade not found"));
    }

    public List<Grade> getGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public List<Grade> getGradesByCourseId(Long courseId) {
        return gradeRepository.findByCourseId(courseId);
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public Grade createGrade(Grade grade) {

        if (grade.getScore() == null ||
                grade.getScore() < 0 ||
                grade.getScore() > 100) {
            throw new IllegalArgumentException(
                    "Score must be between 0 and 100");
        }

        if (grade.getStudentId() == null ||
                !studentRepository.existsById(grade.getStudentId())) {
            throw new ResourceNotFoundException("Student not found");
        }

        if (grade.getCourseId() == null ||
                !courseRepository.existsById(grade.getCourseId())) {
            throw new ResourceNotFoundException("Course not found");
        }

        grade.setLetterGrade(calculateLetterGrade(grade.getScore()));
        grade.setGradedAt(LocalDateTime.now());

        return gradeRepository.save(grade);
    }

    private String calculateLetterGrade(Double score) {
        if (score >= 90) return "A";
        if (score >= 75) return "B";
        if (score >= 60) return "C";
        if (score >= 50) return "D";
        return "F";
    }

    @Transactional(transactionManager = "gradeTransactionManager")
    public void deleteGrade(Long id) {
        Grade grade = getGradeById(id);
        gradeRepository.delete(grade);
    }
}
