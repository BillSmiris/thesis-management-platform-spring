package com.example.thesis_app.professor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUserId(Long userId);

    @Query("SELECT p from Professor p WHERE p.user.username = :username")
    Optional<Professor> findByUsername(String username);
}
