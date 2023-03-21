package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.MeetingMinutes;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class MeetingMinutesRepository implements PanacheMongoRepository<MeetingMinutes>{


    public MeetingMinutes getMeetingMinutesById(String projectId) {
      PanacheQuery<MeetingMinutes> minutes = MeetingMinutes.find("projectId = ?1",projectId);
      return minutes.firstResult();
    }
    
    
}
