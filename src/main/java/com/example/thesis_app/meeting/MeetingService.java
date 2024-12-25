package com.example.thesis_app.meeting;

import com.example.thesis_app.configuration.auth.CustomPrincipal;
import com.example.thesis_app.meeting.dto.request.CreateMeetingRequestModel;
import com.example.thesis_app.meeting.dto.response.ProfessorMeetingInfoResponseModel;
import com.example.thesis_app.meeting.dto.response.ProfessorMeetingListResponseItem;
import com.example.thesis_app.meeting.dto.response.StudentMeetingListResponseItem;
import com.example.thesis_app.thesis.Thesis;
import com.example.thesis_app.thesis.ThesisRepository;
import jakarta.transaction.Transactional;
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
    private final ThesisRepository thesisRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository, ThesisRepository thesisRepository) {
        this.meetingRepository = meetingRepository;
        this.thesisRepository = thesisRepository;
    }

    public List<StudentMeetingListResponseItem> getStudentMeetings(CustomPrincipal principal){
        Optional<List<StudentMeetingListResponseItem>> meetingOptional = meetingRepository.getStudentMeetingsByUsername(principal.getName());

        if(meetingOptional.isEmpty()) {
            throw new RuntimeException("No thesis found for student");
        }

        return meetingOptional.get();
    }

    public List<ProfessorMeetingListResponseItem> getProfessorMeetingsForThesis(Long id, CustomPrincipal principal){
        Optional<List<ProfessorMeetingListResponseItem>> meetingOptional = meetingRepository.getProfessorMeetingsForThesis(id, principal.getName());

        if(meetingOptional.isEmpty()) {
            throw new RuntimeException("No meetings found.");
        }

        return meetingOptional.get();
    }

    @Transactional
    public void createMeeting(CreateMeetingRequestModel body, CustomPrincipal principal) {
        if(thesisRepository.checkHasPermission(body.getThesisId(), principal.getName()) == 0) {
            throw new RuntimeException("You do not have permission to edit this resource.");
        }

        Meeting newMeeting = new Meeting();

        Thesis thesis = new Thesis();
        thesis.setId(body.getThesisId());

        newMeeting.setThesis(thesis);
        newMeeting.setAgenda(body.getAgenda());
        newMeeting.setMeetingDate(body.getDate());

        meetingRepository.save(newMeeting);
    }

    public ProfessorMeetingInfoResponseModel getMeetingInfo(Long id, CustomPrincipal principal) {
        Optional<ProfessorMeetingInfoResponseModel> meetingOptional = meetingRepository.getMeetingInfo(id, principal.getName());

        if(meetingOptional.isEmpty()) {
            throw new RuntimeException("Meeting does not exist or is not accessible.");
        }

        return meetingOptional.get();
    }

    @Transactional
        public void saveMeeting(ProfessorMeetingInfoResponseModel body, CustomPrincipal principal) {
        Optional<Meeting> meetingOptional = meetingRepository.getByIdSecure(body.getId(), principal.getName());

        if(meetingOptional.isEmpty()) {
            throw new RuntimeException("Meeting was not found or is not accessible.");
        }

        Meeting meeting = meetingOptional.get();
        meeting.setNotes(body.getNotes());
        meetingRepository.save(meeting);
    }
}
