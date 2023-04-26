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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.service.MeetingMinutesService;

@Path("/zaga/projectManagement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeetingMinutesResource {

  @Inject
  MeetingMinutesService service;

  @Inject
  MeetingMinutesRepository repo;

  @GET
  @Path("/meetingMinutes/getMeetingMinutes")
  public Response getMeetingMinutes(){
    List<MeetingMinutes> minutes = service.getMeetingMinutesList();
    return Response.ok(minutes).build();
  }

  @GET
  @Path("/meetingMinutes/getMeetingMinutesByMeetingMinutesId/{meetingMinutesId}")
  public Response getMeetingMinutesByMeetingMinutesId(String meetingMinutesId){
    try{
      MeetingMinutes minutes = repo.getMeetingMinutesByMeetingMinutesId(meetingMinutesId);
      return Response.ok(minutes).build();

    }
    catch(WebApplicationException e) {
      return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
    }
  }

  @GET
  @Path("/meetingMinutes/getMeetingMinutesByProjectId/{projectId}")
  public Response getMeetingMinutesByProjectId(String projectId){
    try{
      List<MeetingMinutes> minutes = repo.getMeetingMinutesById(projectId);
      return Response.ok(minutes).build();

    }
    catch(WebApplicationException e) {
      return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
    }
  }

  @PUT
  @Path("/meetingMinutes/modifyMeetingMinutesById")
  public Response updateMeetingMinutes(MeetingMinutes dto) {
    try {
      service.updateMeetingMinutes(dto);
      return Response.ok(dto).build();

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