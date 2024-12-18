package com.example.thesis_app.meeting.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.meeting.MeetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student/meeting")
public class StudentMeetingController {
    private static final Logger logger = LoggerFactory.getLogger(StudentMeetingController.class);

    private final MeetingService meetingService;

    @Autowired
    public StudentMeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping()
    public ResponseEntity<Object> getStudentMeetings(@AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(meetingService.getStudentMeetings(principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
