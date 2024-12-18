package com.example.thesis_app.meeting.controllers;

import com.example.thesis_app.meeting.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/professor/meeting")
public class ProfessorMeetingController {
    private final MeetingService meetingService;

    @Autowired
    public ProfessorMeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping(path = "data")
    public ResponseEntity<String> getPersonalData(AuthenticationPrincipal principal){
        return ResponseEntity.ok("");
    }
}
