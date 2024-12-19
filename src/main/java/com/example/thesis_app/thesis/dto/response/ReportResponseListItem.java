package com.example.thesis_app.thesis.dto.response;

public class ReportResponseListItem {
    private long id;
    private String title;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private String studentPhone;
    private float grade;
    private long numberOfMeetings;
    private long numberOfFiles;

    public ReportResponseListItem() {
    }

    public ReportResponseListItem(long id, String title, String studentFirstName, String studentLastName, String studentEmail, String studentPhone, float grade, long numberOfMeetings, long numberOfFiles) {
        this.id = id;
        this.title = title;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.grade = grade;
        this.numberOfMeetings = numberOfMeetings;
        this.numberOfFiles = numberOfFiles;
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

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public long getNumberOfMeetings() {
        return numberOfMeetings;
    }

    public void setNumberOfMeetings(long numberOfMeetings) {
        this.numberOfMeetings = numberOfMeetings;
    }

    public long getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(long numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }
}
