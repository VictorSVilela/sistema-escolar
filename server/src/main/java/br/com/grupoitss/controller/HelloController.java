package br.com.grupoitss.controller;

import br.com.grupoitss.config.CorsConfig;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/hello")
public class HelloController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar() {
        JSONObject result = new JSONObject();
        result.put("data","Hello World - Backend");
        return Response.ok(result.toString()).build();
    }
}
