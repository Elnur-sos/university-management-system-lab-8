package org.example.student.service;

import org.example.dto.StudentTranscriptDto;
import org.example.student.entity.Student;
import org.example.student.repository.StudentRepository;
import org.example.course.repository.CourseRepository;
import org.example.course.entity.Course;
import org.example.grade.repository.GradeRepository;
import org.example.grade.entity.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranscriptService {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    public TranscriptService(
            StudentRepository studentRepository,
            GradeRepository gradeRepository,
            CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    public StudentTranscriptDto getTranscript(Long studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Grade> grades = gradeRepository.findByStudentId(studentId);

        List<StudentTranscriptDto.GradeInfo> courseList = grades.stream()
                .map(grade -> {
                    Course course = courseRepository.findById(grade.getCourseId())
                            .orElseThrow(() -> new RuntimeException("Course not found"));

                    return new StudentTranscriptDto.GradeInfo(
                            course.getCourseName(),
                            grade.getScore(),
                            grade.getLetterGrade()
                    );
                })
                .toList();

        double average = grades.stream()
                .mapToInt(Grade::getScore)
                .average()
                .orElse(0.0);

        String studentName = student.getFirstName() + " "
                + student.getLastName();

        return new StudentTranscriptDto(
                student.getId(),
                studentName,
                courseList,
                average
        );
    }
}