package com.zaga.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.zaga.model.entity.MeetingMinutes;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.service.MeetingMinutesService;

@Path("/zaga/projectManagement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeetingMinutesResource {

@Inject
MeetingMinutesRepository repo;

    @Inject
    MeetingMinutesService service;

    @Path("meetingMinutes/createMeetingMinutes")
    @POST
    public Response createMeetingMinutes (MeetingMinutes meetingMinutes){
         MeetingMinutes meeetingminutes = service.createMeetingMinutes(meetingMinutes);
         return Response.ok(meeetingminutes).build();
    }
}
