package com.zaga.model.entity;

import java.time.LocalDate;
import org.bson.types.ObjectId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties({ "id" })
@MongoEntity(collection = "ProjectDetails", database = "ProjectManagement")
public class ProjectDetails extends PanacheMongoEntity {

    public ObjectId id;

    // employee datass
    public String employeeName;
    public String employeeEmail;
    public String employeeNumber;
    public String employeeId;
    public String employeeRole;
    public boolean projectAssignmentStatus;

    // basic project datas
    public String projectManager, projectName;
    public String projectId;
    public String clientName;
    public String clientCountry;
    public String clientTimezone;
    public String clientAddress;
    public String clientEmail;
    public String duration; // reference for period
    public LocalDate startDate;
    public LocalDate endDate;

    // quote datas
    public String quoteStatus;
    public String quoteId;
    public String date; // start date
    public String validDate; // endDate
    public String from; // companyAddress
    public String to; // clientAddress
    public String serviceDescription;
    public Float totalManDays; // changed fieldname
    public Float unitPrice; // changed fieldname
    public Currency clientCurrency;
    public Float totalAmount; // changed datatype

    // po datas
    public String po;
    public String sfdc;
    public String pa;

    // ProjectType
    public ProjectType projectType;

    public ProjectDetails() {}
}
