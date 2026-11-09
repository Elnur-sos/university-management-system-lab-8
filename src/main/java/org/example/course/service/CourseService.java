package org.example.course.service;

import org.example.course.entity.Course;
import org.example.course.repository.CourseRepository;
import org.example.exception.ConflictException;
import org.example.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course createCourse(Course course) {

        if (courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new ConflictException("Course code already exists");
        }

        return courseRepository.save(course);
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public Course updateCourse(Long id, Course course) {

        Course existingCourse = getCourseById(id);

        if (!existingCourse.getCourseCode().equals(course.getCourseCode())
                && courseRepository.existsByCourseCode(course.getCourseCode())) {
            throw new ConflictException("Course code already exists");
        }

        existingCourse.setCourseName(course.getCourseName());
        existingCourse.setCourseCode(course.getCourseCode());
        existingCourse.setCredits(course.getCredits());
        existingCourse.setInstructor(course.getInstructor());

        return courseRepository.save(existingCourse);
    }

    @Transactional(transactionManager = "courseTransactionManager")
    public void deleteCourse(Long id) {
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }
}