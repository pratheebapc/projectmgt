package com.zaga.serviceImplementation;

import javax.enterprise.context.ApplicationScoped;

import com.oracle.svm.core.annotate.Inject;
import com.zaga.repository.MeetingMinutesRepository;
import com.zaga.service.MeetingMinutesService;

@ApplicationScoped
public class MeetingMinutesServiceImpl implements MeetingMinutesService {
    @Inject
    MeetingMinutesRepository meetingMinutesRepository;
    
}
