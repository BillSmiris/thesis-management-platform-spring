package com.example.thesis_app.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserId(Long userId);

    @Query("SELECT s from Student s WHERE s.user.username = :username")
    Optional<Student> findByUsername(String username);
}
