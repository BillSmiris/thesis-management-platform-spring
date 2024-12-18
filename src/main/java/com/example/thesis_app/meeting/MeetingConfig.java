package com.example.thesis_app.meeting;

import com.example.thesis_app.thesis.Thesis;
import com.example.thesis_app.thesis.ThesisRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Configuration
public class MeetingConfig {

    @Bean
    CommandLineRunner meetingData(MeetingRepository meetingRepository, ThesisRepository thesisRepository) {
        return args -> {
            List<Thesis> theses = thesisRepository.findAll();

            for (Thesis thesis : theses) {
                List<Meeting> meetings = createMeetings(thesis);
                meetingRepository.saveAll(meetings);
                thesis.setMeetings(meetings);
                thesisRepository.save(thesis);
            }
        };
    }

    private List<Meeting> createMeetings(Thesis thesis) {
        Calendar calendar = Calendar.getInstance();
//        Calendar currentCalendar = Calendar.getInstance();
//
//        calendar.set(currentCalendar.get(Calendar.YEAR), currentCalendar.get(Calendar.MONTH), currentCalendar.get(Calendar.DATE));

        calendar.add(Calendar.DATE, 1);
        Meeting meeting1 = new Meeting();
        meeting1.setMeetingDate(calendar.getTime());
        meeting1.setAgenda("Initial discussion on project goals");
        meeting1.setThesis(thesis);

        calendar.add(Calendar.DATE, 7);
        Meeting meeting2 = new Meeting();
        meeting2.setMeetingDate(calendar.getTime());
        meeting2.setAgenda("Review of progress on the first milestone");
        meeting2.setThesis(thesis);

        calendar.add(Calendar.DATE, 14);
        Meeting meeting3 = new Meeting();
        meeting3.setMeetingDate(calendar.getTime());
        meeting3.setAgenda("Technical review and mid-project evaluation");
        meeting3.setThesis(thesis);

        calendar.add(Calendar.DATE, 18);
        Meeting meeting4 = new Meeting();
        meeting4.setMeetingDate(calendar.getTime());
        meeting4.setAgenda("Pre-final submission review");
        meeting4.setThesis(thesis);

        calendar.add(Calendar.DATE, 21);
        Meeting meeting5 = new Meeting();
        meeting5.setMeetingDate(calendar.getTime());
        meeting5.setAgenda("Final presentation and feedback session");
        meeting5.setThesis(thesis);

        return Arrays.asList(meeting1, meeting2, meeting3, meeting4, meeting5);
    }
}

