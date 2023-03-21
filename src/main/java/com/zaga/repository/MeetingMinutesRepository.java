package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.MeetingMinutes;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class MeetingMinutesRepository implements PanacheMongoRepository<MeetingMinutes>{

    public void deleteMeetingMinutesByMeetingMinutesId(String meetingMinutesId){
        PanacheQuery<MeetingMinutes> meetingMinute = MeetingMinutes.find("meetingMinutesId = ?1", meetingMinutesId);
        MeetingMinutes mm = meetingMinute.firstResult();
        MeetingMinutes.deleteById(mm.getId());
    }
    
    public MeetingMinutes getMeetingMinutesByMeetingMinutesId(String meetingMinutesId){
        PanacheQuery<MeetingMinutes> meetingMinute = MeetingMinutes.find("meetingMinutesId = ?1", meetingMinutesId);
        MeetingMinutes mm = meetingMinute.firstResult();
        return mm;
    }
    
    
}
