package com.example.thesis_app.fileUpload.dto.response;

import java.util.Date;

public class ProfessorFileListResponseItem {
    private Long id;
    private String name;
    private Date timestamp;

    public ProfessorFileListResponseItem() {
    }

    public ProfessorFileListResponseItem(Long id, String name, Date timestamp) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
