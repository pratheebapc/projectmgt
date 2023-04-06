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

import com.zaga.model.entity.DailyTimesheet;
import com.zaga.repository.DailyTimesheetRepository;
import com.zaga.service.DailyTimesheetService;

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
     *  this api for create daily time sheet 
     */
    @Path("/createDailyTimeSheet")
    @POST
    public Response createDailyTimeSheet(DailyTimesheet dts) {
        DailyTimesheet dtss = service.createDailyTimesheet(dts);
        return Response.ok(dtss).build();
    }
    /**
     * 
     * @param dailyTimesheetId
     * @return view the particular daily time sheet (using id)
     */

    @Path("/viewDailyTimeSheetBydailyTimeSheetID/{dailyTimesheetId}")
    @GET
    public Response getDailyTimeSheet(@PathParam ("dailyTimesheetId") String dailyTimesheetId) {
     DailyTimesheet dailyTimeSheet = service.getDailyTimeSheetBydailyTimesheetId(dailyTimesheetId);
     return Response.ok(dailyTimeSheet).build();
    }
 
    /**
     * 
     * @param dts
     * @return modify the existing details 
     */
    @Path("/modifyDailyTimeSheet")
    @PUT
    public Response updateDailyTimeSheet(DailyTimesheet dts){
     service.UpdateDailyTimeSheet(dts);
     return Response.ok(dts).build();
    }

    /**
     * 
     * @param dailyTimesheetId
     * delete the daily time sheet by using daily time sheet id 
     */
    @Path ("/deleteDailyTimeSheet/{dailyTimesheetId}")
    @DELETE
    public void deleteDailyTimeSheet(@PathParam ("dailyTimesheetId")String dailyTimesheetId){
     service.deleteDailyTimeSheetBydailyTimesheetId(dailyTimesheetId);

    }



}