package com.zaga.service;

import java.util.List;

import com.zaga.model.entity.MeetingMinutes;

public interface MeetingMinutesService {
    List<MeetingMinutes> getMeetingMinutesList();

    MeetingMinutes getMeetingMinuteById(String projectId);
}
