package com.example.thesis_app.thesis.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.thesis.ThesisService;
import com.example.thesis_app.thesis.dto.request.ProfessorGradeThesisRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/professor/thesis")
public class ProfessorThesisController {
    private static final Logger logger = LoggerFactory.getLogger(ProfessorThesisController.class);

    private final ThesisService thesisService;

    @Autowired
    public ProfessorThesisController(ThesisService thesisService) {
        this.thesisService = thesisService;
    }

    @GetMapping()
    public ResponseEntity<Object> getProfessorTheses(@AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(thesisService.getProfessorTheses(principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProfessorThesisById(@PathVariable("id") Long id, @AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(thesisService.getProfessorThesisById(id, principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("grade")
    public ResponseEntity<Object> gradeThesis(@RequestBody ProfessorGradeThesisRequestModel body, @AuthenticationPrincipal CustomPrincipal principal) {
        try {
            thesisService.gradeThesis(body, principal);
            return ResponseEntity.ok("");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
