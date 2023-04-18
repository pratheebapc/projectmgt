package com.zaga.service;

import java.time.LocalDate;
import java.util.List;

import com.zaga.model.entity.WeeklyTimesheet;

public interface WeeklyTimesheetService {

    WeeklyTimesheet createWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet);

    List<WeeklyTimesheet> getWeeklyTimesheets();

    List<WeeklyTimesheet> getWeeklyTimesheetsByProjectId(String projectId);

    List<WeeklyTimesheet> getWeeklyTimesheetByType(String timesheetType, String projectId);

    WeeklyTimesheet getWeeklyTimesheetById(String timesheetId);

    WeeklyTimesheet updateWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet);

    WeeklyTimesheet deleteWeeklyTimesheet(String timesheetId);

    public WeeklyTimesheet generateWeeeklyTimesheet(String projectId, LocalDate startDate, LocalDate endDate);

}
