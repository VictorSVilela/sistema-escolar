package br.com.grupoitss.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.grupoitss.model.Escola;

@Path("/escolas")
public class EscolaController {

    private static final Map<Long, Escola> escolas = new HashMap<>();
    private static Long idGerador = 1L;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Escola incluir(Escola escola) {
        escola.setId(idGerador++);
        escola.setStatus(true);
        escolas.put(escola.getId(), escola);
        return escola;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        if (escolas.containsKey(id)) {
            Escola escola = escolas.get(id);
            return Response.ok(escola).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok(escolas.values()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Escola escola) {
        if(escolas.containsKey(id)){
            escola.setId(id);
            escolas.put(id, escola);
            return Response.ok(escolas.get(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        if (escolas.containsKey(id)){
            escolas.remove(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
