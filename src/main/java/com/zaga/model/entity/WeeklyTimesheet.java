package com.zaga.model.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("{id}")
@MongoEntity(collection = "WeeklyTimesheet", database = "ProjectManagement")
public class WeeklyTimesheet extends PanacheMongoEntity {

    public ObjectId id;
    public String projectId;
    public String employeeName;
    public String projectName;
    public String employeeRole;

    public String weeklyTimesheetId;
    public String duration;
    public LocalDate startDate;
    public LocalDate endDate;
    public List<DailyTimesheet> timesheets;
    public TimesheetType timesheetType;

}
