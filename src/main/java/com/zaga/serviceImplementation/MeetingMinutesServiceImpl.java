package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;

import org.jboss.logging.Logger;

import com.oracle.svm.core.annotate.Inject;
import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.service.MeetingMinutesService;

@ApplicationScoped
public class MeetingMinutesServiceImpl implements MeetingMinutesService {
    
    @javax.inject.Inject
    MeetingMinutesRepository repo;

    @javax.inject.Inject
    Logger logger;

    @Override
    public MeetingMinutes updateMeetingMinutes(UpdateMeetingMinutesDto dto){

        if (dto.getMeetingMinutesId() == null) {

            throw new WebApplicationException("Invalid Meeting Minutes Id ", 400);
         }
   
         MeetingMinutes meetingMinutes = repo.getMeetingMinutesByMeetingMinutesId(dto.getMeetingMinutesId());
   
         if (meetingMinutes == null) {
   
            throw new WebApplicationException("Resource Not Found For Meeting Minutes Id ", 404);
         }
   
         logger.info("------Meeting Minutes------" + meetingMinutes);
   
         MeetingMinutes minute = dto.getMeetingMinutes();
   
         minute.setId(meetingMinutes.getId());
   
         logger.info("-----Dto -----" + minute);
   
         MeetingMinutes.update(minute);
   
         return dto.getMeetingMinutes();
    }

    @Override
    public void deleteMeetingMinutes(String meetingMinutesId) {

      if (meetingMinutesId == null) {

         throw new WebApplicationException("Invalid Meeting Minutes Id ", 400);

      }
       
     repo.deleteMeetingMinutesByMeetingMinutesId(meetingMinutesId);

      
   }
    
}
