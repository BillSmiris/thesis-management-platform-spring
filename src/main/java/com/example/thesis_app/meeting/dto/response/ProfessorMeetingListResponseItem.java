package com.example.thesis_app.meeting.dto.response;

import java.util.Date;

public class ProfessorMeetingListResponseItem {
    long id;
    Date date;
    String agenda;

    public ProfessorMeetingListResponseItem() {
    }

    public ProfessorMeetingListResponseItem(long id, Date date, String agenda) {
        this.id = id;
        this.date = date;
        this.agenda = agenda;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
