package com.zaga.serviceImplementation;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;

import org.jboss.logging.Logger;

import com.zaga.model.entity.PdfEntity;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.model.entity.ProjectLimitedDto;
import com.zaga.repository.PdfRepository;
import com.zaga.repository.ProjectDetailsRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.ProjectDetailsService;

@ApplicationScoped
public class ProjectDetailsServiceImpl implements ProjectDetailsService {

    @Inject
    Logger logger;

    @Inject
    ProjectDetailsRepository repo;

    @Inject
    SequenceRepository seqRepo;

    @Inject
    PdfRepository pdfRepo;

    @Override
    public ProjectDetails createProjectDetails(ProjectDetails projectDetails) {
        // TODO Auto-generated method stub
        String seqNo = seqRepo.getSequenceCounter("Project");
        projectDetails.setProjectId(seqNo);

        if (canCreate(projectDetails)) {
            ProjectDetails.persist(projectDetails);
        } else {

            logger.error("Could not Persist data already exists");
            throw new WebApplicationException("Already exists", 500);
        }
        return projectDetails;
    }

    @Override
    public List<ProjectLimitedDto> getProjectDetails() {

        List<ProjectDetails> projects = repo.listAll();

        if (projects.isEmpty()) {

            throw new WebApplicationException("The Resource is empty ", 404);
        }

        List<ProjectLimitedDto> projectDtoList = projects.stream()
                .map(project -> {
                    ProjectLimitedDto dto = new ProjectLimitedDto();
                    dto.setProjectId(project.getProjectId());
                    dto.setProjectName(project.getProjectName());
                    dto.setEmployeeName(project.getEmployeeName());
                    return dto;
                })
                .collect(Collectors.toList());

        return projectDtoList;
    }

    @Override
    public ProjectDetails getProjectDetailsById(String projectId) {
        ProjectDetails projectDetails = repo.getProjectDetailsById(projectId);
        if (projectDetails == null) {
            throw new WebApplicationException("The Resource is empty ", 404);
        }
        return projectDetails;
    }

    @Override
    public ProjectDetails updateProjectDetails(ProjectDetails dto) {

        logger.info("Projectdetail inside service implementation " + dto);

        ProjectDetails projectDetails = repo.getProjectDetailsById(dto.getProjectId());
        if (projectDetails == null) {
            throw new WebApplicationException("The Resource is empty ", 404);
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

        ProjectDetails testprojectDetails = repo.checkProjectDetails(projectDetails);

        if (testprojectDetails == null) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;

    }

    @Override
    public List<ProjectDetails> getProjectDetailsbyCategory(String category) {
        List<ProjectDetails> details = repo.getProjectDetailsByProjectType(category);
        return details;
    }

    @Override
    public void savePdfDocument(String name, InputStream inputstream) throws IOException{
      
        logger.infof("Saving PDF document with name '%s'", name);
        byte[] data = inputstream.readAllBytes();
        PdfEntity pdfDocument = new PdfEntity();
        pdfRepo.persist(pdfDocument);
    }

}
