package com.zaga.service;

import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;

public interface MeetingMinutesService {

    MeetingMinutes updateMeetingMinutes(UpdateMeetingMinutesDto dto);

    void deleteMeetingMinutes(String meetingMinutesId);

}
    
