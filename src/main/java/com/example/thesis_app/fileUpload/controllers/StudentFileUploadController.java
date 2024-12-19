package com.example.thesis_app.fileUpload.controllers;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.fileUpload.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "api/v1/student/file")
public class StudentFileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(StudentFileUploadController.class);

    private final FileUploadService fileUploadService;

    @Autowired
    public StudentFileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping()
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("thesisId") Long thesisId,
                                             @AuthenticationPrincipal CustomPrincipal principal) {
        try {
            fileUploadService.uploadFile(file, thesisId, principal);
            return ResponseEntity.ok("");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
