package com.zaga.model.entity;

import java.time.LocalDate;
import java.util.TimeZone;

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
    public String projectManager ,projectName;
//     public String projectName;
    public String projectId;
    public String clientName;
    public String clientCountry;
    public String clientTimezone;
    public String clientAddress;
    public String clientEmail;
    public String duration;
    public String serviceDescription;
    public String startDate;
    public String endDate;
    public String quoteStatus;
    public String quoteId;
    public String date;
    public String validDate;
    public String from;
    public String to;
    public String qty;
    public String unitPrice;
    public String totalAmount;
    public String po;
    public String sfdc;
    public String pa;

}

