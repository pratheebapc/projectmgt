package com.zaga.model.dto;

import com.zaga.model.entity.MeetingMinutes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMeetingMinutesDto {

    public String meetingMinutesId;
    public MeetingMinutes meetingMinutes;
  
}
