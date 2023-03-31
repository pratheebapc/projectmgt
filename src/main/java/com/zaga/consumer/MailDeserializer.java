package com.zaga.consumer;

import com.zaga.model.entity.ProjectDetails;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MailDeserializer  extends ObjectMapperDeserializer<ProjectDetails>{

    public MailDeserializer() {
        
        super(ProjectDetails.class);
        
    }
    
}
