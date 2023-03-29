package com.zaga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zaga.model.dto.UpdateProjectDetails;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.service.ProjectDetailsService;

@Path("/zaga/projectManagement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectDetailsResource {

    @Inject
    ProjectDetailsService service;

   @POST
   @Path("/projectManagement/createProjectDetails")
    public Response createProjectDetails(ProjectDetails projectDetails){
        ProjectDetails projectDetails2 = service.createProjectDetails(projectDetails);
        return Response.ok(projectDetails2).build();
    }

    @GET
    @Path("/projectManagement/viewProjectDetails")
     public Response getProjectDetails(){
        List<ProjectDetails> projectDetails = service.getProjectDetails();
        return Response.ok(projectDetails).build();
    }

    @GET
    @Path("/projectManagement/viewProjectDetailsById/{projectId}")
    public Response getProjectDetailsById(@PathParam("projectId") String projectId){
        ProjectDetails projectDetails = service.getProjectDetailsById(projectId);
        return Response.ok(projectDetails).build();
    }

    @PUT
    @Path("/projectManagement/updateProjectDetails")
    public Response updateProjectDetails(UpdateProjectDetails dto){
        service.updateProjectDetails(dto);
        return Response.ok(dto.getProjectDetails()).build();
    }
    @DELETE
    @Path("/projectManagement/deleteProjectDetails/{projectId}")
    public void deleteProjectDetails(@PathParam("projectId") String projectId){
        // ProjectDetails.findByIdOptional(projectId).ifPresent(p -> p.delete());
        service.deleteProjectDetails(projectId);
    }
}
