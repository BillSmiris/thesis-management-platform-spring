package com.example.thesis_app.thesis;

import com.example.thesis_app.professor.Professor;
import com.example.thesis_app.professor.ProfessorRepository;
import com.example.thesis_app.student.Student;
import com.example.thesis_app.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.util.Date;

@Configuration
public class ThesisConfig {

    @Bean
    @Order(3)
    CommandLineRunner thesisData(
            ThesisRepository thesisRepository,
            StudentRepository studentRepository,
            ProfessorRepository professorRepository
    ) {
        return args -> {
            Professor supervisor = professorRepository.findAll().stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No professor found in the database."));

            Student student1 = studentRepository.findById(1L)
                    .orElseThrow(() -> new IllegalStateException("Student with ID 1 not found."));
            Student student2 = studentRepository.findById(2L)
                    .orElseThrow(() -> new IllegalStateException("Student with ID 2 not found."));

            Thesis thesis1 = new Thesis();
            thesis1.setTitle("A Study on Advanced Algorithms");
            thesis1.setStartDate(new Date());
            thesis1.setProgrammingLanguages(Arrays.asList("Java", "Python"));
            thesis1.setTechnologies(Arrays.asList("Spring Boot", "TensorFlow"));
            thesis1.setFinalGrade(0.0f); // Initial grade set to 0.0
            thesis1.setStudent(student1);
            thesis1.setSupervisor(supervisor);

            Thesis thesis2 = new Thesis();
            thesis2.setTitle("Exploring Artificial Intelligence in Healthcare");
            thesis2.setStartDate(new Date());
            thesis2.setProgrammingLanguages(Arrays.asList("Python", "R"));
            thesis2.setTechnologies(Arrays.asList("Scikit-Learn", "Pandas"));
            thesis2.setFinalGrade(0.0f);
            thesis2.setStudent(student2);
            thesis2.setSupervisor(supervisor);

            thesisRepository.save(thesis1);
            thesisRepository.save(thesis2);
        };
    }
}