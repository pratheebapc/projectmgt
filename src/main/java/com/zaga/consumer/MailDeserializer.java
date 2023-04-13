package com.zaga.consumer;

import com.zaga.event.EventDto;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MailDeserializer  extends ObjectMapperDeserializer<EventDto> {

    public MailDeserializer() {
        super(EventDto.class);
    }
    
}
