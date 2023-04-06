package com.zaga.producer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// import org.eclipse.microprofile.reactive.messaging.Channel;
// import org.eclipse.microprofile.reactive.messaging.Emitter;
// import org.eclipse.microprofile.reactive.messaging.Message;
// import org.eclipse.microprofile.reactive.messaging.Outgoing;

//import com.oracle.svm.core.annotate.Inject;
import com.zaga.model.entity.ProjectDetails;

@Path("/test")
public class MailProducer {

    // @Inject
    // @Channel("mail-out")
    // Emitter<ProjectDetails> emitter;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ProjectDetails MailProducer(ProjectDetails dto) {

        // emitter.send(dto);

        // Message<ProjectDetails> op = emitter.;

        return dto;
    }

}
