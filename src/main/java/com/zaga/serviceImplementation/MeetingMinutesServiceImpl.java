package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.jboss.logging.Logger;

import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.MeetingMinutesService;

@ApplicationScoped
public class MeetingMinutesServiceImpl implements MeetingMinutesService {
    

    

   @Inject
   MeetingMinutesRepository repo;

   @Inject
   Logger logger;

   @Inject
   SequenceRepository seqRepo;


   @Override
    public List<MeetingMinutes> getMeetingMinutesList() {
       
       List<MeetingMinutes> meetingMinutes =  repo.listAll();

       System.out.println(meetingMinutes);

       if(meetingMinutes.isEmpty()) {

        throw new WebApplicationException("The Resource is empty ",404);
       }
       return meetingMinutes;
    }

    @Override
    public MeetingMinutes getMeetingMinuteById(String projectId) {
       
        if (projectId == null){
            throw new WebApplicationException("ProjectId is Invalid ",400);
        }
        MeetingMinutes meetingminutes = repo.getMeetingMinutesById(projectId);
        if (meetingminutes == null){
            throw new WebApplicationException("The Resource is empty ",404);
        }
        return meetingminutes;
        
    }
    
   @Override
   public MeetingMinutes createMeetingMinutes(MeetingMinutes meetingMinutes) {
      String seqno = seqRepo.getSquenceCounter("MeetingMinutes");
      meetingMinutes.setMeetingMinutesId(seqno);
      MeetingMinutes.persist(meetingMinutes);
      return meetingMinutes;
   }

   @Override
   public MeetingMinutes updateMeetingMinutes(UpdateMeetingMinutesDto dto) {

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
