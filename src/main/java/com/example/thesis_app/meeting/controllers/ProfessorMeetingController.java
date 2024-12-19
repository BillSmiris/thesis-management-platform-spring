package com.example.thesis_app.meeting.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.meeting.MeetingService;
import com.example.thesis_app.meeting.dto.request.CreateMeetingRequestModel;
import com.example.thesis_app.meeting.dto.response.ProfessorMeetingInfoResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/professor/meeting")
public class ProfessorMeetingController {
    private final MeetingService meetingService;

    @Autowired
    public ProfessorMeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping(path="thesis/{id}")
    public ResponseEntity<Object> getProfessorMeetingsForThesis(@PathVariable("id") Long id, @AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(meetingService.getProfessorMeetingsForThesis(id, principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createMeeting(@RequestBody CreateMeetingRequestModel body, @AuthenticationPrincipal CustomPrincipal principal){
        try {
            meetingService.createMeeting(body, principal);
            return ResponseEntity.ok("");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path="{id}")
    public ResponseEntity<Object> getMeetingInfo(@PathVariable("id") Long id, @AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(meetingService.getMeetingInfo(id, principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path="notes")
    public ResponseEntity<Object> setMeetingNotes(@RequestBody ProfessorMeetingInfoResponseModel body, @AuthenticationPrincipal CustomPrincipal principal){
        try {
            meetingService.saveMeeting(body, principal);
            return ResponseEntity.ok("");
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
