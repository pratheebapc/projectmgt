package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.ProjectDetails;

import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public class ProjectDetailsRepository implements PanacheMongoRepository<ProjectDetails> {
    
}
