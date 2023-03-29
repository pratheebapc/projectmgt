package com.zaga.model.dto;

import com.zaga.model.entity.ProjectDetails;

import lombok.Data;
@Data
public class UpdateProjectDetails{
    
    public String projectId;
    public ProjectDetails projectDetails;
}
