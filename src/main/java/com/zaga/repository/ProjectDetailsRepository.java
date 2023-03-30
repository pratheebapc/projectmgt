package com.zaga.repository;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.ProjectDetails;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
// import io.quarkus.mongodb.panache.common.PanacheUpdate;

@ApplicationScoped
public class ProjectDetailsRepository implements PanacheMongoRepository<ProjectDetails> {
    
    public ProjectDetails getProjectDetailsById(String projectId){
        PanacheQuery<ProjectDetails> details = ProjectDetails.find("projectId=?1", projectId);
        ProjectDetails pd = details.firstResult();
        return pd;
    }

    // public ProjectDetails updateProjectDetails(String projectId, ProjectDetails projectDetails){
    //     PanacheQuery<ProjectDetails> details = ProjectDetails.find("projectId=?1", projectId);
    //     PanacheUpdate pd = update(
    //     return pd;
    // }

    public void deleteProjectDetailsById(String projectId){
        // PanacheQuery<ProjectDetails> details = ProjectDetails.find("projectId=?1", projectId);
        // ProjectDetails pd = details.firstResult();
        ProjectDetails.delete("projectId=?1", projectId);
        
    }
    
}
