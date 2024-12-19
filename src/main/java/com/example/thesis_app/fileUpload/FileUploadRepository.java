package com.example.thesis_app.fileUpload;

import com.example.thesis_app.fileUpload.dto.response.ProfessorFileListResponseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    @Query("SELECT new com.example.thesis_app.fileUpload.dto.response.ProfessorFileListResponseItem(f.id, f.fileName, f.timestamp) " +
            "FROM FileUpload f WHERE f.thesis.id = :id AND f.thesis.supervisor.user.username = :username " +
            "ORDER BY f.timestamp DESC")
    Optional<List<ProfessorFileListResponseItem>> getThesisFiles(@Param("id") long id, @Param("username") String username);

    @Query("SELECT f FROM FileUpload f WHERE f.id = :id AND f.thesis.supervisor.user.username = :username")
    Optional<FileUpload> getByIdSecure(@Param("id") long id, @Param("username") String username);
}
