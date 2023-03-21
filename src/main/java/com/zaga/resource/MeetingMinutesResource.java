package com.zaga.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;


import com.zaga.model.dto.UpdateMeetingMinutesDto;
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

  

  @PUT
  @Path("/meetingMinutes/modifyMeetingMinutesById")
  public Response updateMeetingMinutes(UpdateMeetingMinutesDto dto) {
    try {
      service.updateMeetingMinutes(dto);
      return Response.ok(dto.getMeetingMinutes()).build();

    } catch (WebApplicationException e) {
      return Response.status(e.getResponse().getStatusInfo()).entity(e.getMessage()).build();
    }
  }

  @DELETE
    @Path("/meetingMinutes/deleteMeetingMinutesById/{meetingMinutesId}")
    public Response deleteMeetingMinutesById(@PathParam("meetingMinutesId") String meetingMinutesId) {
      try {
        service.deleteMeetingMinutes(meetingMinutesId);
        return Response.ok().build();
      } catch (WebApplicationException e) {
        return Response.status(e.getResponse().getStatusInfo()).entity(e.getMessage()).build();
      }
    }
  @POST
  @Path("meetingMinutes/createMeetingMinutes")
  public Response createMeetingMinutes(MeetingMinutes meetingMinutes) {
    MeetingMinutes meeetingminutes = service.createMeetingMinutes(meetingMinutes);
    return Response.ok(meeetingminutes).build();
  }
}
