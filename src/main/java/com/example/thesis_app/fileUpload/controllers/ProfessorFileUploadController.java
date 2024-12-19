package com.example.thesis_app.fileUpload.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.fileUpload.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/professor/file")
public class ProfessorFileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(ProfessorFileUploadController.class);

    private final FileUploadService fileUploadService;

    @Autowired
    public ProfessorFileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("thesis/{thesisId}")
    public ResponseEntity<Object> getThesisFiles(@PathVariable("thesisId") Long thesisId, @AuthenticationPrincipal CustomPrincipal principal) {
        try {
            return ResponseEntity.ok(fileUploadService.getThesisFiles(thesisId, principal));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getFile(@PathVariable("id") Long id, @AuthenticationPrincipal CustomPrincipal principal) {
        try {
            return fileUploadService.getFile(id, principal);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
