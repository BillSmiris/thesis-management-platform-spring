package com.example.thesis_app.thesis.dto.response;

public class ProfessorThesisListResponseItem {
    private long id;
    private String title;
    private String studentFirstName;
    private String studentLastName;

    public ProfessorThesisListResponseItem() {
    }

    public ProfessorThesisListResponseItem(long id, String title, String studentFirstName, String studentLastName) {
        this.id = id;
        this.title = title;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }
}
