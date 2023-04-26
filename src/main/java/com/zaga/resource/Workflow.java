package com.zaga.resource;

import java.util.Base64;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.zaga.client.PoWorkflow;

//import com.zaga.client.PoWorkflow;

@Tag(name = "Po workflow", description = "Workflow operations")
@Path("/poWorkflow")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Workflow {

    @RestClient
    @Inject
    PoWorkflow workflow;

    String username = "rhpamAdmin";
    String password = "Admin@123";

    @POST
    public void startProcess(@HeaderParam("Authorization") String authorization,
            @QueryParam("containerId") String containerId,
            @QueryParam("processID") String processID,
            String payload) {
        System.out.println(authorization);
        workflow.startProcess(authorization, containerId, processID, payload);
    }

    private String encodeCredentials(String username, String password) {
        String credentials = username + ":" + password;
        return Base64.getEncoder().encodeToString(credentials.getBytes());
    }
}
