package com.example.thesis_app.thesis.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.thesis.ThesisService;
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
@RequestMapping(path = "api/v1/student/thesis")
public class StudentThesisController {
    private static final Logger logger = LoggerFactory.getLogger(StudentThesisController.class);

    private final ThesisService thesisService;

    @Autowired
    public StudentThesisController(ThesisService thesisService) {
        this.thesisService = thesisService;
    }

    @GetMapping()
    public ResponseEntity<Object> getStudentThesis(@AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(thesisService.getStudentThesis(principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
