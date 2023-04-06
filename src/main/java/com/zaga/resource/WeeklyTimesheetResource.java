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

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.zaga.model.entity.WeeklyTimesheet;
import com.zaga.service.WeeklyTimesheetService;

@Tag (name = "Weekly Timesheet", description = "CRUD Operations for Project Details")
@Path("/zaga/projectManagement/weeklyTimesheet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeeklyTimesheetResource {

    @Inject
    WeeklyTimesheetService service;
    
    @POST
    @Path("/createWeeklyTimesheet")
    public WeeklyTimesheet createWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        return service.createWeeklyTimesheet(weeklyTimesheet);
    }

    @GET
    @Path("/getWeeklyTimesheets")
    public List<WeeklyTimesheet> getWeeklyTimesheets() {
        return service.getWeeklyTimesheets();
    }

    @GET
    @Path("/getWeeklyTimesheet/weeklyTimesheetType}")
    public List<WeeklyTimesheet> getWeeklyTimesheetByType(@PathParam("weeklyTimesheetType") String timesheetType) {
        return service.getWeeklyTimesheetByType(timesheetType);
    }
    
    @GET
    @Path("/getWeeklyTimesheet/{weeklyTimesheetId}")
    public WeeklyTimesheet getWeeklyTimesheetById(@PathParam("weeklyTimesheetId") String timesheetId) {
        return service.getWeeklyTimesheetById(timesheetId);
    }

    @PUT
    @Path("/updateWeeklyTimesheet")
    public WeeklyTimesheet updateWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        return service.updateWeeklyTimesheet(weeklyTimesheet);
    }

    @DELETE
    @Path("/deleteWeeklyTimesheet")
    public WeeklyTimesheet deleteWeeklyTimesheet(String timesheetId) {
        return service.deleteWeeklyTimesheet(timesheetId);
    }
}
