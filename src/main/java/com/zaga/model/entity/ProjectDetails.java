package com.zaga.model.entity;

import java.time.LocalDate;
import java.util.*;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"id"})
@MongoEntity(collection = "ProjectDetails" , database =  "ProjectManagement")
public class ProjectDetails extends PanacheMongoEntity {
    
    public ObjectId id;

    //employee datass
    public String employeeName;
    public String employeeEmail;
    public String employeeNumber;
    public String employeeId;
    public String employeeRole;
    public boolean projectAssignmentStatus;

    //basic project datas
    public String projectManager ,projectName;
    public String projectId;
    public String clientName;
    public String clientCountry;
    public String clientTimezone;
    public String clientAddress;
    public String clientEmail;
    public String duration; //reference for period
    public String startDate;
    public String endDate;

    //quote datas
    public String quoteStatus;
    public String quoteId;
    public String date; //start date
    public String validDate; //endDate
    public String from; // companyAddress
    public String to; // clientAddress
    public String serviceDescription;
    public String quantity;
    public String unitPrice;
    public Currency clientCurrency;
    public String totalAmount;

    //po datas
    public String po;
    public String sfdc;
    public String pa;

}

