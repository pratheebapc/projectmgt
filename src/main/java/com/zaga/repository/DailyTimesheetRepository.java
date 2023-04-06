package com.zaga.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import com.zaga.model.entity.DailyTimesheet;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;

@ApplicationScoped
public class DailyTimesheetRepository implements PanacheMongoRepository<DailyTimesheet> {

    public DailyTimesheet getDailyTimeSheets(String dailyTimesheetId){
        PanacheQuery<DailyTimesheet> id = DailyTimesheet.find("dailyTimesheetId=?1",dailyTimesheetId);
        DailyTimesheet Idd = id.firstResult();
        return Idd;
        
    }

    public void deleteDailyTimeSheetBydailyTimesheetId(String dailyTimesheetId){
        DailyTimesheet.delete("dailyTimesheetId=?1",dailyTimesheetId);
    }
    
}
