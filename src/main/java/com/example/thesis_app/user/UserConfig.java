package com.example.thesis_app.user;

import com.example.thesis_app.enums.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class UserConfig {

    @Bean
    @Order(1)
    CommandLineRunner userData(UserService userService) {
        return args -> {
            userService.saveUser(
                    "am12345",
                    "password123",
                    Role.STUDENT
            );

            userService.saveUser(
                    "am67890",
                    "password456",
                    Role.STUDENT
            );

            userService.saveUser(
                    "amp12345",
                    "profpass123",
                    Role.PROFESSOR
            );

            userService.saveUser(
                    "amp67890",
                    "profpass456",
                    Role.PROFESSOR
            );
        };
    }
}