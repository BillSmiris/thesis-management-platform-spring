package com.example.thesis_app.professor.dto.response;

import jakarta.persistence.Column;

public class ProfessorPersonalData {
    private String firstName;
    private String lastName;
    private String email;

    public ProfessorPersonalData() {
    }

    public ProfessorPersonalData(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
