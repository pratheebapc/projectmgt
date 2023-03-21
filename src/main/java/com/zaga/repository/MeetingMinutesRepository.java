package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.MeetingMinutes;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class MeetingMinutesRepository implements PanacheMongoRepository<MeetingMinutes>{

    
    
}
