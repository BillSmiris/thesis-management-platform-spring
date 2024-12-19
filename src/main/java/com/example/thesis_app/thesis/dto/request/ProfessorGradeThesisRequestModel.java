package com.example.thesis_app.thesis.dto.request;

public class ProfessorGradeThesisRequestModel {
    private Long id;
    private float grade;

    public ProfessorGradeThesisRequestModel() {
    }

    public ProfessorGradeThesisRequestModel(Long id, float grade) {
        this.id = id;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
