package com.example.thesis_app.meeting;

import com.example.thesis_app.meeting.dto.response.ProfessorMeetingInfoResponseModel;
import com.example.thesis_app.meeting.dto.response.ProfessorMeetingListResponseItem;
import com.example.thesis_app.meeting.dto.response.StudentMeetingListResponseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query("SELECT new com.example.thesis_app.meeting.dto.response.StudentMeetingListResponseItem(" +
            "m.id, m.meetingDate, m.agenda) " +
            "FROM Meeting m WHERE m.thesis.student.user.username = :username AND m.meetingDate > CURRENT_TIMESTAMP " +
            "ORDER BY m.meetingDate ASC")
    Optional<List<StudentMeetingListResponseItem>> getStudentMeetingsByUsername(@Param("username") String username);

    @Query("SELECT new com.example.thesis_app.meeting.dto.response.ProfessorMeetingListResponseItem(" +
            "m.id, m.meetingDate, m.agenda) " +
            "FROM Meeting m WHERE m.thesis.id = :id AND m.thesis.supervisor.user.username = :username " +
            "ORDER BY m.meetingDate ASC")
    Optional<List<ProfessorMeetingListResponseItem>> getProfessorMeetingsForThesis(@Param("id") Long id, @Param("username") String username);

    @Query("SELECT new com.example.thesis_app.meeting.dto.response.ProfessorMeetingInfoResponseModel(" +
            "m.id, m.agenda, m.meetingDate, m.notes) " +
            "FROM Meeting m WHERE m.id = :id AND m.thesis.supervisor.user.username = :username")
    Optional<ProfessorMeetingInfoResponseModel> getMeetingInfo(@Param("id") Long id, @Param("username") String username);

    @Query("SELECT m FROM Meeting m WHERE m.id = :id AND m.thesis.supervisor.user.username = :username")
    Optional<Meeting> getByIdSecure(@Param("id") Long id, @Param("username") String username);
}
