package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

import org.jboss.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.WebApplicationException;


import com.zaga.model.entity.MeetingMinutes;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.service.MeetingMinutesService;

@ApplicationScoped
public class MeetingMinutesServiceImpl implements MeetingMinutesService {
    @Inject
    MeetingMinutesRepository repo;

    @Inject
    Logger logger;

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
    
}
