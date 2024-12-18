package com.example.thesis_app.student;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.configuration.auth.CustomUserDetails;
import com.example.thesis_app.student.dto.response.StudentPersonalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentPersonalData getPersonalData(CustomPrincipal principal) {
        Optional<Student> studentOptional = studentRepository.findByUsername(principal.getName());

        if(studentOptional.isEmpty()) {
            throw new RuntimeException("No student profile found for user!");
        }

        Student student = studentOptional.get();

        return new StudentPersonalData(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhoneNumber()
        );
    }
}
