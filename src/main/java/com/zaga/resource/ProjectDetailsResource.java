package com.zaga.resource;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import lombok.Builder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.common.jaxrs.ResponseImpl;
import org.bson.types.Binary;
// import org.eclipse.microprofile.reactive.messaging.Channel;
// import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;

import com.zaga.event.EventDto;
import com.zaga.model.entity.PdfEntity;
import com.zaga.model.entity.ProjectDetails;
import com.zaga.model.entity.ProjectLimitedDto;
import com.zaga.repository.PdfRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.service.ProjectDetailsService;

@Tag(name = "Project Details", description = "CRUD Operations for Project Details")
@Path("/projectDetails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectDetailsResource {

    // @Inject
    // @Channel("mail-out")
    // Emitter<EventDto> eventemitter;

    @Inject
    ProjectDetailsService service;

    @Inject
    PdfRepository repository;

    @Inject
    SequenceRepository sequenceRepository;

    @POST
    @Path("/createProjectDetails")
    @APIResponse(responseCode = "200", description = "Created a new project details mongodb document in the mongodb collection - Project Details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = ProjectDetails.class)))
    public Response createProjectDetails(ProjectDetails projectDetails) {
        try {
            System.out.println("--------------ProjectDetails" + projectDetails);

            ProjectDetails projectDetails2 = service.createProjectDetails(projectDetails);

            EventDto poEvent = EventDto.builder().destination("PoService").source("ProjectAssigment")
                    .eventDate(LocalDateTime.now())
                    .eventId(UUID.randomUUID().toString())
                    .eventData(projectDetails2).build();

            // eventemitter.send(poEvent);

            return Response.ok(projectDetails2).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewProjectDetails")
    @APIResponse(responseCode = "200", description = "Viewing All Project Details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = ProjectDetails.class)))
    public Response getProjectDetails() {
        List<ProjectLimitedDto> projectDetails = service.getProjectDetails();
        return Response.ok(projectDetails).build();
    }

    @GET
    @Path("/viewProjectDetailsById/{projectId}")
    @APIResponse(responseCode = "200", description = "Viewing Project Details by projectId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = ProjectDetails.class)))
    public Response getProjectDetailsById(@PathParam("projectId") String projectId) {
        try {
            ProjectDetails projectDetails = service.getProjectDetailsById(projectId);
            return Response.ok(projectDetails).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/viewProjectDetailsByCategory/{projectType}")
    @APIResponse(responseCode = "200", description = "Viewing Project Details by projectType", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = ProjectDetails.class)))
    public Response getProjectDetailsByCategory(@PathParam("projectType") String projectType) {
        try {
            List<ProjectDetails> projectDetails = service.getProjectDetailsbyCategory(projectType);
            return Response.ok(projectDetails).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/updateProjectDetails")
    @APIResponse(responseCode = "200", description = "Updated Project Details mongodb document in the mongodb database by projectId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = ProjectDetails.class)))
    public Response updateProjectDetails(ProjectDetails dto) {
        try {
            service.updateProjectDetails(dto);
            return Response.ok(dto).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/deleteProjectDetails/{projectId}")
    @APIResponse(responseCode = "204", description = "Deleted a Project Details mongodb document in the mongodb database by projectId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = ProjectDetails.class)))
    public void deleteProjectDetails(@PathParam("projectId") String projectId) {
        // ProjectDetails.findByIdOptional(projectId).ifPresent(p -> p.delete());
        service.deleteProjectDetails(projectId);

    }
    @POST
    @Path("/uploadPdfDocument")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadPdfDocument(InputStream inputStream, @QueryParam("projectName") String projectName,@QueryParam("projectId") String projectId, @QueryParam("startDate") LocalDate startDate, @QueryParam("endDate") LocalDate endDate)
            throws IOException {
        // ProjectDetails projectDetails = new ProjectDetails();
        PdfEntity pdfDocument = new PdfEntity();
        StringBuilder DocId = new StringBuilder();
        DocId.append(projectName);
        DocId.append("_");
        DocId.append(startDate);
        DocId.append("_");
        DocId.append(endDate);
        // String seqNo = sequenceRepository.getSequenceCounter("ApprovedTimesheet");
        pdfDocument.setDocumentId(DocId.toString());
        pdfDocument.projectId = projectId;
        pdfDocument.projectName = projectName;
        pdfDocument.startDate = startDate;
        pdfDocument.endDate = endDate;
        pdfDocument.data = new Binary(inputStream.readAllBytes());

        // ProjectDetails details = new ProjectDetails();
        // details.getProjectId();

        repository.persist(pdfDocument);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/{projectId}")
    public Response viewPdfDocument(@PathParam("projectId") String projectId) {
        try {
            List<PdfEntity> pdf = repository.viewPdfDocumentByProjectId(projectId);
            return Response.ok(pdf).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Path("/{documentId}")
    public PdfEntity viewPfDocumentByDocumentId(@PathParam("documentId") String documentId) {
        try {
            PdfEntity pdf = repository.viewPdfDocumentByDocumentId(documentId);
            return pdf;
        } catch (WebApplicationException e) {
            return null;
        }
    }}