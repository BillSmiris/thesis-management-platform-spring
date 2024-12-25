package com.example.thesis_app.professor;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.professor.dto.response.ProfessorPersonalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {
    private static final Logger logger = LoggerFactory.getLogger(ProfessorService.class);

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public ProfessorPersonalData getPersonalData(CustomPrincipal principal) {
        Optional<Professor> professorOptional = professorRepository.findByUsername(principal.getName());

        if(professorOptional.isEmpty()) {
            throw new RuntimeException("No professor profile found for user!");
        }

        Professor professor = professorOptional.get();

        return new ProfessorPersonalData(
            professor.getFirstName(),
            professor.getLastName(),
            professor.getEmail()
        );
    }
}
