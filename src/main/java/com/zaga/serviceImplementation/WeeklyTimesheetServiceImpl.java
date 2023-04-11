package com.zaga.serviceImplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.zaga.model.entity.DailyTimesheet;
import com.zaga.model.entity.WeeklyTimesheet;
import com.zaga.repository.DailyTimesheetRepository;
import com.zaga.repository.SequenceRepository;
import com.zaga.repository.WeeklyTimesheetRepository;
import com.zaga.service.WeeklyTimesheetService;

@ApplicationScoped
public class WeeklyTimesheetServiceImpl implements WeeklyTimesheetService {

    @Inject
    Logger logger;

    @Inject
    SequenceRepository sequenceRepository;

    @Inject
    WeeklyTimesheetRepository weeklyTimesheetRepository;

    @Inject
    DailyTimesheetRepository drepo;

    @Override
    public WeeklyTimesheet createWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        String seqNo = sequenceRepository.getSequenceCounter("WeeklyTimesheet");
        weeklyTimesheet.setWeeklyTimesheetId(seqNo);
        WeeklyTimesheet.persist(weeklyTimesheet);
        return weeklyTimesheet;
    }

    @Override
    public List<WeeklyTimesheet> getWeeklyTimesheets() {
        List<WeeklyTimesheet> timesheets = WeeklyTimesheet.listAll();
        return timesheets;
    }

    @Override
    public List<WeeklyTimesheet> getWeeklyTimesheetByType(String timesheetType, String projectId) {
        List<WeeklyTimesheet> timesheets = weeklyTimesheetRepository.getWeeklyTimesheetByType(timesheetType, projectId);
        return timesheets;

    }

    @Override
    public WeeklyTimesheet getWeeklyTimesheetById(String timesheetId) {
        WeeklyTimesheet weeklyTimesheet2 = weeklyTimesheetRepository.getWeeklyTimesheetById(timesheetId);
        return weeklyTimesheet2;
    }

    @Override
    public WeeklyTimesheet updateWeeklyTimesheet(WeeklyTimesheet weeklyTimesheet) {
        WeeklyTimesheet weeklyTimesheet2 = weeklyTimesheetRepository
                .getWeeklyTimesheetById(weeklyTimesheet.getWeeklyTimesheetId());
        weeklyTimesheet.setId(weeklyTimesheet2.getId());
        weeklyTimesheet.update();
        return weeklyTimesheet;
    }

    @Override
    public WeeklyTimesheet deleteWeeklyTimesheet(String timesheetId) {
        WeeklyTimesheet weeklyTimesheet2 = weeklyTimesheetRepository.getWeeklyTimesheetById(timesheetId);
        weeklyTimesheet2.delete();
        return weeklyTimesheet2;
    }

    @Override
    public List<WeeklyTimesheet> getWeeklyTimesheetsByProjectId(String projectId) {
        // TODO Auto-generated method stub
        List<WeeklyTimesheet> result = WeeklyTimesheet.list("projectId=?1", projectId);
        return result;
    }

    public WeeklyTimesheet generateWeeeklyTimesheet(String projectId, LocalDate startDate, LocalDate endDate) {
        WeeklyTimesheet result = new WeeklyTimesheet();
        List<DailyTimesheet> data = drepo.getDailyTimesheetsByProjectId(projectId);
        List<DailyTimesheet> filteredData = data.stream()
                .filter(timesheet -> timesheet.getDate().isAfter(startDate.minusDays(1))
                        && timesheet.getDate().isBefore(endDate.plusDays(1)))
                .collect(Collectors.toList());

        result.setTimesheets(filteredData);
        System.out.println(result);
        return result;
    }
}
