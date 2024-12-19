package com.example.thesis_app.thesis;

import com.example.thesis_app.meeting.Meeting;
import com.example.thesis_app.thesis.dto.response.ProfessorThesisListResponseItem;
import com.example.thesis_app.thesis.dto.response.ProfessorThesisResponseModel;
import com.example.thesis_app.thesis.dto.response.StudentThesisResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    @Query("SELECT new com.example.thesis_app.thesis.dto.response.StudentThesisResponseModel(" +
            "t.id, t.title, t.startDate, t.programmingLanguages, t.technologies, t.finalGrade, " +
            "t.supervisor.firstName, t.supervisor.lastName, t.supervisor.email) " +
            "FROM Thesis t WHERE t.student.user.username = :username")
    Optional<StudentThesisResponseModel> getByStudentUsername(@Param("username") String username);

    @Query("SELECT new com.example.thesis_app.thesis.dto.response.ProfessorThesisResponseModel(" +
            "t.id, t.title, t.startDate, t.programmingLanguages, t.technologies, t.finalGrade, " +
            "t.student.firstName, t.student.lastName, t.student.email, t.student.phoneNumber) " +
            "FROM Thesis t WHERE t.id = :id AND t.supervisor.user.username = :username")
    Optional<ProfessorThesisResponseModel> getProfessorThesisById(@Param("id") long id, @Param("username") String username);

    @Query("SELECT new com.example.thesis_app.thesis.dto.response.ProfessorThesisListResponseItem(" +
            "t.id, t.title, t.student.firstName, t.student.lastName) " +
            "FROM Thesis t WHERE t.supervisor.user.username = :username")
    Optional<List<ProfessorThesisListResponseItem>> getByProfessorUsername(@Param("username") String username);

    @Query("SELECT COUNT(*) FROM Thesis t WHERE t.id = :id AND t.supervisor.user.username = :username")
    Long checkHasPermission(@Param("id") Long id, @Param("username") String username);

    @Query("SELECT t FROM Thesis t WHERE t.id = :id AND t.supervisor.user.username = :username")
    Optional<Thesis> getByIdSecure(@Param("id") Long id, @Param("username") String username);

    @Query("SELECT COUNT(*) FROM Thesis t WHERE t.id = :id AND t.student.user.username = :username")
    Long studentCheckPermission(@Param("id") Long id, @Param("username") String username);
}
