package com.zaga.client;

import java.util.Map;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.kie.api.runtime.process.ProcessInstance;

import com.zaga.model.entity.ProjectDetails;

@RegisterRestClient(configKey = "kie-server")
@Path("/kie-server/services/rest/server")
public interface PoWorkflow {
    @POST
    @Path("/containers/{containerId}/processes/{processId}/instances")
    @Produces(MediaType.APPLICATION_JSON)
    Integer startProcess(
            @HeaderParam("Authorization") String authorization,
            @PathParam("containerId") String containerId,
            @PathParam("processId") String processId,
            Map<String, Object> Data);
}
