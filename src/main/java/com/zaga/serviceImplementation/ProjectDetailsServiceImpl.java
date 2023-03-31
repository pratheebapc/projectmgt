package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;


import com.zaga.model.entity.ProjectDetails;
import com.zaga.repository.ProjectDetailsRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.ProjectDetailsService;

@ApplicationScoped
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    @Inject 
    ProjectDetailsRepository repo;

    @Inject
    Logger logger;
 
    @Inject
    SequenceRepository seqRepo;

    @Override
    public ProjectDetails createProjectDetails(ProjectDetails projectDetails) {
        // TODO Auto-generated method stub
        String seqNo = seqRepo.getSequenceCounter("Project");
        projectDetails.setProjectId(seqNo);
        ProjectDetails.persist(projectDetails);
        return projectDetails;   
    }

    @Override
    public List<ProjectDetails> getProjectDetails() {
    List<ProjectDetails> projects = repo.listAll();
    return projects;
    }

    @Override
    public ProjectDetails getProjectDetailsById(String projectId){
        ProjectDetails projectDetails = repo.getProjectDetailsById(projectId);
        return projectDetails;
    }

    @Override
    public ProjectDetails updateProjectDetails(ProjectDetails dto) {
        // TODO Auto-generated method stub
       ProjectDetails projectDetails = repo.getProjectDetailsById(dto.getProjectId());
       ProjectDetails details = dto;
       details.setId(projectDetails.getId());
       ProjectDetails.update(details);
       return dto;


    }

    @Override
    public void deleteProjectDetails(String projectId) {
        // TODO Auto-generated method stub
        repo.deleteProjectDetailsById(projectId);
        
     }
 


}
