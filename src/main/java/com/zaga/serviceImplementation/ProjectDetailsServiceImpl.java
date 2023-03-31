package com.zaga.serviceImplementation;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.zaga.model.entity.ProjectDetails;
import com.zaga.repository.ProjectDetailsRepository;
import com.zaga.service.ProjectDetailsService;

@ApplicationScoped
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    @Inject
    Logger logger;

    @Inject 
    ProjectDetailsRepository repo;

    @Override
    public ProjectDetails createProjectDetails(ProjectDetails projectDetails) {
        if(canCreate(projectDetails)){
            ProjectDetails.persist(projectDetails);
        }else{

            logger.error("Could not Persist data already exists");
            throw new WebApplicationException("Already exists", 500);
        }
        return null;   
    }

    @Override
    public List<ProjectDetails> getProjectDetails() {

    List<ProjectDetails> projects = repo.listAll();

    if(projects.isEmpty()) {

        throw new WebApplicationException("The Resource is empty ",404);
       }

    return projects;
    }

    @Override
    public ProjectDetails getProjectDetailsById(String projectId){
        ProjectDetails projectDetails = repo.getProjectDetailsById(projectId);
        if (projectDetails == null){
            throw new WebApplicationException("The Resource is empty ",404);
        }
        return projectDetails;
    }

    @Override
    public ProjectDetails updateProjectDetails(ProjectDetails dto) {
        
       ProjectDetails projectDetails = repo.getProjectDetailsById(dto.getProjectId());
       if (projectDetails == null){
        throw new WebApplicationException("The Resource is empty ",404);
    }
       ProjectDetails details = dto;

       details.setId(projectDetails.getId());
       ProjectDetails.update(details);
       return dto;


    }

    @Override
    public void deleteProjectDetails(String projectId) {
        
        repo.deleteProjectDetailsById(projectId);
        
     }
     
    @Override
     public Boolean canCreate(ProjectDetails projectDetails) {
          
        ProjectDetails testprojectDetails = getProjectDetailsById(projectDetails.getProjectId());

         if (testprojectDetails == null){
            return Boolean.TRUE;
         }

        return null ;

     }


}
