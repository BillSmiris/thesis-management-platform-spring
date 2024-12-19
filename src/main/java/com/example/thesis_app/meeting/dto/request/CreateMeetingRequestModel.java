package com.example.thesis_app.meeting.dto.request;

import java.util.Date;

public class CreateMeetingRequestModel {
    private long thesisId;
    private String agenda;
    private Date date;

    public CreateMeetingRequestModel() {
    }

    public CreateMeetingRequestModel(long thesisId, String agenda, Date date) {
        this.thesisId = thesisId;
        this.agenda = agenda;
        this.date = date;
    }

    public long getThesisId() {
        return thesisId;
    }

    public void setThesisId(long thesisId) {
        this.thesisId = thesisId;
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
}
