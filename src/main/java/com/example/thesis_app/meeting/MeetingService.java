package com.example.thesis_app.meeting;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.meeting.dto.response.StudentMeetingListResponseItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    private static final Logger logger = LoggerFactory.getLogger(MeetingService.class);

    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public List<StudentMeetingListResponseItem> getStudentMeetings(CustomPrincipal principal){
        Optional<List<StudentMeetingListResponseItem>> meetingOptional = meetingRepository.getStudentMeetingsByUsername(principal.getName());

        if(meetingOptional.isEmpty()) {
            throw new RuntimeException("No thesis found for student");
        }

        return meetingOptional.get();
    }
}
