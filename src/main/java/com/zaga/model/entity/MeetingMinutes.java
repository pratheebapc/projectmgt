package com.zaga.model.entity;

import java.time.LocalDate;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zaga.model.dto.Agenda;
import com.zaga.model.dto.Attendees;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"id"})
@MongoEntity(collection = "MeetingMinutes" , database =  "ProjectManagement")
public class MeetingMinutes extends PanacheMongoEntity{
    public ObjectId id;
    public String employeeName;
    public String  projectName;
    public String projectId;
    public String meetingMinutesId;
    public String date;
    public String startTime;
    public String endTime;
    public String meetingObjective;
    public List<Attendees> attendeesPresent ;
    public List<Agenda> agenda ;
  
}
