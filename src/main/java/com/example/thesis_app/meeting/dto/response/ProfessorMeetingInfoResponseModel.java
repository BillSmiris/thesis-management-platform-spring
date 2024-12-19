package com.example.thesis_app.meeting.dto.response;

import java.util.Date;

public class ProfessorMeetingInfoResponseModel {
    private long id;
    private String agenda;
    private Date date;
    private String notes;

    public ProfessorMeetingInfoResponseModel() {
    }

    public ProfessorMeetingInfoResponseModel(long id, String agenda, Date date, String notes) {
        this.id = id;
        this.agenda = agenda;
        this.date = date;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
