package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.ProjectDetails;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class ProjectDetailsRepository implements PanacheMongoRepository<ProjectDetails> {
    
    public ProjectDetails getProjectDetailsById(String projectId){
        PanacheQuery<ProjectDetails> details = ProjectDetails.find("projectId=?1", projectId);
        ProjectDetails pd = details.firstResult();
        return pd;
    }

    public void deleteProjectDetailsById(String projectId){
        // PanacheQuery<ProjectDetails> details = ProjectDetails.find("projectId=?1", projectId);
        // ProjectDetails pd = details.firstResult();
        ProjectDetails.delete("projectId=?1", projectId);
        
    }
    
    
    
}
