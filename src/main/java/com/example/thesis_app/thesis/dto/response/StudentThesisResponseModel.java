package com.example.thesis_app.thesis.dto.response;

import java.util.Date;
import java.util.List;

public class StudentThesisResponseModel {
    private Long id;
    private String title;
    private Date startDate;
    private List<String> programmingLanguages;
    private List<String> technologies;
    private float finalGrade;
    private String supervisorFirstName;
    private String supervisorLastName;
    private String supervisorEmail;

    public StudentThesisResponseModel() {
    }

    public StudentThesisResponseModel(Long id, String title, Date startDate, List<String> programmingLanguages, List<String> technologies, float finalGrade, String supervisorFirstName, String supervisorLastName, String supervisorEmail) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.programmingLanguages = programmingLanguages;
        this.technologies = technologies;
        this.finalGrade = finalGrade;
        this.supervisorFirstName = supervisorFirstName;
        this.supervisorLastName = supervisorLastName;
        this.supervisorEmail = supervisorEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getSupervisorFirstName() {
        return supervisorFirstName;
    }

    public void setSupervisorFirstName(String supervisorFirstName) {
        this.supervisorFirstName = supervisorFirstName;
    }

    public String getSupervisorLastName() {
        return supervisorLastName;
    }

    public void setSupervisorLastName(String supervisorLastName) {
        this.supervisorLastName = supervisorLastName;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }
}
