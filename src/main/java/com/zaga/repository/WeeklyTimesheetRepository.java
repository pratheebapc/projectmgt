package com.zaga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.zaga.model.entity.WeeklyTimesheet;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class WeeklyTimesheetRepository implements PanacheMongoRepository<WeeklyTimesheet> {
    
    public WeeklyTimesheet getWeeklyTimesheetById(String weeklyTimesheetId){
        PanacheQuery<WeeklyTimesheet> timesheet = WeeklyTimesheet.find("weeklyTimesheetId=?1", weeklyTimesheetId);
        WeeklyTimesheet wt = timesheet.firstResult();
        return wt;
    }

    public List<WeeklyTimesheet> getWeeklyTimesheetByType(String Type, String projectId){
        List<WeeklyTimesheet> timesheet = WeeklyTimesheet.list("timesheetType=?1 and projectId=?2", Type, projectId);
        return timesheet;
    }
}
