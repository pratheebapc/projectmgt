package com.zaga.consumer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.zaga.event.EventDto;
import com.zaga.model.entity.ProjectDetails;

@Path("kafkaTest")
public class MailConsumer {
    

   List<EventDto> projectDetails = new ArrayList<>();


   
     @GET
     public List<EventDto> consumer(){
       return projectDetails;
    }
    
    @Incoming("mail-in")
    public void projectlist(EventDto dto){
          projectDetails.add(dto);
    }

}
