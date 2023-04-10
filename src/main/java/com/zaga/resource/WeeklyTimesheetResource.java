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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.zaga.client.PdfService;
import com.zaga.model.entity.WeeklyTimesheet;
import com.zaga.service.WeeklyTimesheetService;

@Tag (name = "Weekly Timesheet", description = "CRUD Operations for Project Details")
@Path("/weeklyTimesheet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WeeklyTimesheetResource {

    @Inject
    WeeklyTimesheetService service;

    @Inject
    @RestClient
    PdfService pdfService;

    
    @POST
    @Path("/createTimesheet")
    public TemplateInstance generateTimesheetPdf(WeeklyTimesheet weeklyTimesheet) {
        return service.TimesheetTemplateGenerate(weeklyTimesheet);
    }
    
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
    @Path("/getWeeklyTimesheet/{weeklyTimesheetType}")
    public List<WeeklyTimesheet> getWeeklyTimesheetByType(@PathParam("weeklyTimesheetType") String timesheetType, @QueryParam("projectId") String projectId) {
        return service.getWeeklyTimesheetByType(timesheetType,projectId);
    }

    @GET
    @Path("/getWeeklyTimesheetByProjectId/{projectId}")
    public List<WeeklyTimesheet> getWeeklyTimesheetByProjectId(@PathParam("projectId") String projectId) {
        return service.getWeeklyTimesheetsByProjectId(projectId);
    }
    
    @GET
    @Path("/getWeeklyTimesheetByWeeklyTimesheetId/{weeklyTimesheetId}")
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
