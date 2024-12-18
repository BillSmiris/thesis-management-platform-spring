package com.example.thesis_app.student;

import com.example.thesis_app.user.User;
import com.example.thesis_app.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class StudentConfig {
    @Order(2)
    @Bean
    CommandLineRunner studentData(StudentRepository studentRepository, UserRepository userRepository) {
        return args -> {
            User studentUser1 = userRepository.findByUsername("am12345").orElseThrow();
            User studentUser2 = userRepository.findByUsername("am67890").orElseThrow();

            Student student1 = new Student();
            student1.setFirstName("Alice");
            student1.setLastName("Brown");
            student1.setEmail("alice.brown@student.university.edu");
            student1.setPhoneNumber("123-456-7890");
            student1.setUser(studentUser1);

            Student student2 = new Student();
            student2.setFirstName("Bob");
            student2.setLastName("White");
            student2.setEmail("bob.white@student.university.edu");
            student2.setPhoneNumber("987-654-3210");
            student2.setUser(studentUser2);

            studentRepository.save(student1);
            studentRepository.save(student2);
        };
    }
}
