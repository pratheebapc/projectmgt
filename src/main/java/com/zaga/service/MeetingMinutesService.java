package com.zaga.service;

import java.util.List;

// import com.zaga.model.dto.UpdateMeetingMinutesDto;
import com.zaga.model.entity.MeetingMinutes;

public interface MeetingMinutesService {
    List<MeetingMinutes> getMeetingMinutesList();

    MeetingMinutes getMeetingMinuteByMeetingMinutesId(String meetingMinutesId);

    MeetingMinutes updateMeetingMinutes(MeetingMinutes dto);

    void deleteMeetingMinutes(String meetingMinutesId);

    MeetingMinutes createMeetingMinutes(MeetingMinutes meetingMinutes);

}
