package com.zaga.model.entity;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyTimesheetDto {
    public String projectId;
    public String employeeName;
    public String weeklyTimesheetId;
    public String duration;
    public LocalDate startDate;
    public LocalDate endDate;
    public List<DailyTimesheet> timesheets;
    public TimesheetType timesheetType;
}
