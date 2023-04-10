package com.zaga.resource;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
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
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.media.Content;

import com.zaga.model.entity.DailyTimesheet;
import com.zaga.repository.DailyTimesheetRepository;
import com.zaga.service.DailyTimesheetService;

@Tag(name = "Daily Time Sheet", description = "CRUD Operations for Daily Time Sheet")
@Path("/zaga/projectManagement")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DailyTimesheetResource {

    @Inject
    DailyTimesheetService service;

    @Inject
    DailyTimesheetRepository repo;

    /**
     * 
     * @param dailyTimesheetId
     *                         this api for create daily time sheet
     */
    @POST
    @Path("/createDailyTimeSheet")
    @APIResponse(responseCode = "201", description = "Created a new Daily Time Sheet mongodb document in the mongodb collection - DailyTimesheet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DailyTimesheet.class)))
    public Response createDailyTimeSheet(DailyTimesheet dts) {
        try {
            DailyTimesheet dtss = service.createDailyTimesheet(dts);
            return Response.ok(dtss).build();

        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }

    }

    /**
     * 
     * @param dailyTimesheetId
     * @return view the particular daily time sheet (using id)
     */
    @Path("/viewDailyTimesheetsByProjectId/{projectId}")
    @GET
    @APIResponse(responseCode = "200", description = "Viewing DailyTimesheets by Project Id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DailyTimesheet.class)))
    public List<DailyTimesheet> getDailyTimesheetByProjectId(@PathParam("projectId") String projectId){
        List<DailyTimesheet> dt = DailyTimesheet.list("projectId=?1", projectId);
        return dt;
    }

    @Path("/viewDailyTimeSheetBydailyTimeSheetID/{dailyTimesheetId}")
    @GET
    @APIResponse(responseCode = "200", description = "Viewing Daily Time Sheet by dailyTimesheetId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DailyTimesheet.class)))
    public Response getDailyTimeSheet(@PathParam("dailyTimesheetId") String dailyTimesheetId) {
        try {
            DailyTimesheet dailyTimeSheet = service.getDailyTimeSheetBydailyTimesheetId(dailyTimesheetId);
            return Response.ok(dailyTimeSheet).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }

    }

    /**
     * 
     * @param dts
     * @return modify the existing details
     */
    @Path("/modifyDailyTimeSheet")
    @PUT
    @APIResponse(responseCode = "200", description = "Updated the Daily Time Sheet mongodb document in the mongodb database by dailyTimesheetId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DailyTimesheet.class)))
    public Response updateDailyTimeSheet(DailyTimesheet dts) {
        try {
            service.UpdateDailyTimeSheet(dts);
            return Response.ok(dts).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }

    }

    /**
     * 
     * @param dailyTimesheetId
     *                         delete the daily time sheet by using daily time sheet
     *                         id
     */
    @Path("/deleteDailyTimeSheet/{dailyTimesheetId}")
    @DELETE
    @APIResponse(responseCode = "204", description = "Deleted a Daily Time Sheet mongodb document in the mongodb database by dailyTimesheetId", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DailyTimesheet.class)))
    public void deleteDailyTimeSheet(@PathParam("dailyTimesheetId") String dailyTimesheetId) {
        service.deleteDailyTimeSheetBydailyTimesheetId(dailyTimesheetId);

    }

}