package br.com.grupoitss.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsConfig implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin","*");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers","*");
        containerRequestContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE, HEAD");
    }
}
