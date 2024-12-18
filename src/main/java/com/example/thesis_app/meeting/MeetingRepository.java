package com.example.thesis_app.meeting;

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
}
