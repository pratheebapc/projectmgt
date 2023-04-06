package com.zaga.service;

import java.util.List;

import com.zaga.model.entity.WeeklyTimesheet;

public interface WeeklyTimesheetService {
    
    WeeklyTimesheet createWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet);
    
    List<WeeklyTimesheet> getWeeklyTimesheets();

    List<WeeklyTimesheet> getWeeklyTimesheetByType(String timesheetType);

    WeeklyTimesheet getWeeklyTimesheetById(String timesheetId);

    WeeklyTimesheet updateWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet);

    WeeklyTimesheet deleteWeeklyTimesheet(String timesheetId);
}
