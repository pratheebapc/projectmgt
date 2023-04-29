package com.zaga.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agenda {
     public String topic;
     public String timeTaken;
     public String presentedBy;
     public String description;

}
