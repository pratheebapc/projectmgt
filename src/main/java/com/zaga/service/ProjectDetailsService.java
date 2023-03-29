package com.zaga.service;

import java.util.List;

import com.zaga.model.dto.UpdateProjectDetails;
import com.zaga.model.entity.ProjectDetails;

public interface ProjectDetailsService {
    
    ProjectDetails createProjectDetails(ProjectDetails projectDetails);

    ProjectDetails updateProjectDetails(UpdateProjectDetails dto);

    List<ProjectDetails> getProjectDetails();

    ProjectDetails getProjectDetailsById(String projectId);


    void deleteProjectDetails(String projectId);

    // ProjectDetails viewProjectDetails(ProjectDetails projectDetails);}
}