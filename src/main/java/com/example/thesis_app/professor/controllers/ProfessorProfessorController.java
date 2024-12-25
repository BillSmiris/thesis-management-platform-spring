package com.example.thesis_app.professor.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.professor.ProfessorService;
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
@RequestMapping(path = "api/v1/professor/professor")
public class ProfessorProfessorController {
    private static final Logger logger = LoggerFactory.getLogger(ProfessorProfessorController.class);

    private final ProfessorService professorService;

    @Autowired
    public ProfessorProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(path = "data")
    public ResponseEntity<Object> getPersonalData(@AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(professorService.getPersonalData(principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
