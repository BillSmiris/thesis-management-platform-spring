package com.example.thesis_app.professor;

import com.example.thesis_app.user.User;
import com.example.thesis_app.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ProfessorConfig {
    @Order(2)
    @Bean
    CommandLineRunner professorData(ProfessorRepository professorRepository, UserRepository userRepository) {
        return args -> {
            User professorUser1 = userRepository.findByUsername("amp12345").orElseThrow();
            User professorUser2 = userRepository.findByUsername("amp67890").orElseThrow();

            Professor professor1 = new Professor();
            professor1.setFirstName("John");
            professor1.setLastName("Doe");
            professor1.setEmail("john.doe@university.edu");
            professor1.setUser(professorUser1);

            Professor professor2 = new Professor();
            professor2.setFirstName("Jane");
            professor2.setLastName("Smith");
            professor2.setEmail("jane.smith@university.edu");
            professor2.setUser(professorUser2);

            professorRepository.save(professor1);
            professorRepository.save(professor2);
        };
    }
}
