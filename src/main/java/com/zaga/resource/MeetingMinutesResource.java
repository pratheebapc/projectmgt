package com.zaga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;


import com.zaga.model.entity.MeetingMinutes;
import com.zaga.service.MeetingMinutesService;

@Path("/zaga/projectManagement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeetingMinutesResource {

    @Inject
    MeetingMinutesService service;

    @Inject
    Logger logger;
    
    @Path("/projectStatus/viewProjectStatus")
    @GET
    public Response getMeetingMinutes(){
        try {
            List<MeetingMinutes> meetingMinutes = service.getMeetingMinutesList();
            return Response.status(Response.Status.OK).entity(meetingMinutes).build();

        } catch (WebApplicationException e) {
            logger.trace(e.getStackTrace());
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
    @Path("/meetingMinutes/viewMeetingMinutesById/{projectId}")
    @GET
    public Response getMeetingMinuteById(@PathParam("projectId") String projectId){
        try {
            MeetingMinutes meetingMinute = service.getMeetingMinuteById(projectId);
            return Response.status(Response.Status.OK).entity(meetingMinute).build();

        } catch (WebApplicationException e) {
            logger.trace(e.getStackTrace());
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
