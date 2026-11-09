package org.example.grade.controller;

import org.example.grade.entity.Grade;
import org.example.grade.service.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable Long id) {
        return gradeService.getGradeById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Grade> getGradesByStudentId(
            @PathVariable Long studentId) {
        return gradeService.getGradesByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}")
    public List<Grade> getGradesByCourseId(
            @PathVariable Long courseId) {
        return gradeService.getGradesByCourseId(courseId);
    }

    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.createGrade(grade);
    }

    @DeleteMapping("/{id}")
    public String deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return "Grade deleted successfully";
    }
}