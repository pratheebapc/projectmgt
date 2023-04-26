package com.zaga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.MeetingMinutes;

// import io.quarkus.arc.lookup.LookupIfProperty.List;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class MeetingMinutesRepository implements PanacheMongoRepository<MeetingMinutes>{


    public List<MeetingMinutes> getMeetingMinutesById(String projectId) {
      List<MeetingMinutes> minutes = MeetingMinutes.list("projectId = ?1",projectId);
      return minutes;
    }
    public void deleteMeetingMinutesByMeetingMinutesId(String meetingMinutesId){
        PanacheQuery<MeetingMinutes> meetingMinute = MeetingMinutes.find("meetingMinutesId = ?1", meetingMinutesId);
        MeetingMinutes mm = meetingMinute.firstResult();
        MeetingMinutes.deleteById(mm.getId());
    }
    
    public MeetingMinutes getMeetingMinutesByMeetingMinutesId(String meetingMinutesId){
        System.out.println(meetingMinutesId);
        PanacheQuery<MeetingMinutes> meetingMinute = MeetingMinutes.find("meetingMinutesId = ?1", meetingMinutesId);
        MeetingMinutes mm = meetingMinute.firstResult();
        System.out.println("------repo------"+mm);
        return mm;
    }
    
    
}
