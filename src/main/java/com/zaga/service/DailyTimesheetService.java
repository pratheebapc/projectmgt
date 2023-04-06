package com.zaga.service;

import java.util.List;

import com.zaga.model.entity.DailyTimesheet;

public interface DailyTimesheetService {
    DailyTimesheet createDailyTimesheet(DailyTimesheet dts);

   List<DailyTimesheet> getDailyTimeSheet();

   DailyTimesheet UpdateDailyTimeSheet(DailyTimesheet dts);

   DailyTimesheet getDailyTimeSheetBydailyTimesheetId(String dailyTimesheetId);

   void deleteDailyTimeSheetBydailyTimesheetId(String dailyTimesheetId);

}
