package com.zaga.serviceImplementation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.zaga.model.entity.MeetingMinutes;
import com.zaga.model.entity.SequenceCounters;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.MeetingMinutesService;
import com.zaga.service.SequenceService;

@ApplicationScoped
public class MeetingMinutesServiceImpl implements MeetingMinutesService {

    @Inject
    MeetingMinutesRepository repo;
   
    @Inject
   Logger logger;

   @Inject
   SequenceRepository seqRepo;

    @Override
    public MeetingMinutes createMeetingMinutes(MeetingMinutes meetingMinutes) {
       String seqno =  seqRepo.getSquenceCounter("MeetingMinutes");
        meetingMinutes.setMeetingMinutesId(seqno);
        MeetingMinutes.persist(meetingMinutes);
       return meetingMinutes;
    }

    
}
