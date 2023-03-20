package com.zaga.model.entity;

import java.time.LocalDate;
import java.util.TimeZone;

import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MongoEntity(collection = "ProjectDetails" , database =  "ProjectManagement")
public class ProjectDetails {
    public String employeeName;
    public Integer employeeId;
    public String projectName;
    public Integer projectId;
    public LocalDate startDate;
    public LocalDate endDate;
    public Integer duration;
    public String client;
    public String clientCountry;
    public String clientTimezone;
    public Integer billingRate;
}
