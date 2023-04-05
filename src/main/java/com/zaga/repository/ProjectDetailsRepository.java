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

    public ProjectDetails checkProjectDetails(ProjectDetails projectDetails){
        System.out.println("ProjectDetails checkProjectDetails--------------------------"+projectDetails);
        PanacheQuery<ProjectDetails> details = ProjectDetails.find("projectName=?1", projectDetails.getProjectName());
        ProjectDetails pd = details.firstResult();
        System.out.println("pd--------------"+pd);
        return pd;
    }

    public void deleteProjectDetailsById(String projectId){
        ProjectDetails.delete("projectId=?1", projectId);
        
    }
    
}
