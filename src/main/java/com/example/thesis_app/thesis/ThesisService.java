package com.example.thesis_app.thesis;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.thesis.dto.response.ProfessorThesisListResponseItem;
import com.example.thesis_app.thesis.dto.response.ProfessorThesisResponseModel;
import com.example.thesis_app.thesis.dto.response.StudentThesisResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThesisService {
    private static final Logger logger = LoggerFactory.getLogger(ThesisService.class);

    private final ThesisRepository thesisRepository;

    @Autowired
    public ThesisService(ThesisRepository thesisRepository) {
        this.thesisRepository = thesisRepository;
    }

    public StudentThesisResponseModel getStudentThesis(CustomPrincipal principal){
        Optional<StudentThesisResponseModel> thesisOptional = thesisRepository.getByStudentUsername(principal.getName());

        if(thesisOptional.isEmpty()) {
            throw new RuntimeException("No thesis found for student");
        }

        return thesisOptional.get();
    }

    public List<ProfessorThesisListResponseItem> getProfessorTheses(CustomPrincipal principal){
        Optional<List<ProfessorThesisListResponseItem>> thesisOptional = thesisRepository.getByProfessorUsername(principal.getName());

        if(thesisOptional.isEmpty()) {
            throw new RuntimeException("No theses found for professor");
        }

        return thesisOptional.get();
    }

    public ProfessorThesisResponseModel getProfessorThesisById(Long id, CustomPrincipal principal){
        Optional<ProfessorThesisResponseModel> thesisOptional = thesisRepository.getProfessorThesisById(id, principal.getName());

        if(thesisOptional.isEmpty()) {
            throw new RuntimeException("The thesis does not exist or is not accessible.");
        }

        return thesisOptional.get();
    }
}