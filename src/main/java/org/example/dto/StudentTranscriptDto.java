package org.example.dto;

import java.util.List;

public class StudentTranscriptDto {

    private Long studentId;
    private String studentName;
    private List<GradeInfo> courses;
    private Double averageScore;

    public StudentTranscriptDto() {
    }

    public StudentTranscriptDto(
            Long studentId,
            String studentName,
            List<GradeInfo> courses,
            Double averageScore) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courses = courses;
        this.averageScore = averageScore;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<GradeInfo> getCourses() {
        return courses;
    }

    public void setCourses(List<GradeInfo> courses) {
        this.courses = courses;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public static class GradeInfo {

        private String courseName;
        private Integer score;
        private String letterGrade;

        public GradeInfo() {
        }

        public GradeInfo(String courseName, Integer score, String letterGrade) {
            this.courseName = courseName;
            this.score = score;
            this.letterGrade = letterGrade;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public String getLetterGrade() {
            return letterGrade;
        }

        public void setLetterGrade(String letterGrade) {
            this.letterGrade = letterGrade;
        }
    }
}