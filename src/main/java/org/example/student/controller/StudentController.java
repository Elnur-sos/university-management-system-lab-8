package org.example.student.controller;

import org.example.student.entity.Student;
import org.example.student.service.StudentService;
import org.example.student.service.TranscriptService;
import org.example.dto.StudentTranscriptDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final TranscriptService transcriptService;

    public StudentController(
            StudentService studentService,
            TranscriptService transcriptService) {
        this.studentService = studentService;
        this.transcriptService = transcriptService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }

    @GetMapping("/{id}/transcript")
    public StudentTranscriptDto getTranscript(@PathVariable Long id) {
        return transcriptService.getTranscript(id);
    }
}