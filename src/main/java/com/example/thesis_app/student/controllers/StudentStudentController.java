package com.example.thesis_app.student.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.configuration.auth.CustomUserDetails;
import com.example.thesis_app.student.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student/student")
public class StudentStudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentStudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "data")
    public ResponseEntity<Object> getPersonalData(@AuthenticationPrincipal CustomPrincipal principal){
        try {
            return ResponseEntity.ok(studentService.getPersonalData(principal));
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
