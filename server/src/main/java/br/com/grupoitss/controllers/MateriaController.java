package br.com.grupoitss.controllers;

import br.com.grupoitss.model.Materia;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/materias")
public class MateriaController {

    private static final Map<Long, Materia> materias = new HashMap<>();
    private static Long idGerador = 1L;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Materia incluir(Materia materia) {
        materia.setId(idGerador++);
        materias.put(materia.getId(), materia);
        return materia;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        if (materias.containsKey(id)) {
            Materia materia = materias.get(id);
            return Response.ok(materia).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok(materias.values()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Materia materia) {
        if (materias.containsKey(id)) {
            materia.setId(id);
            materias.put(id, materia);
            return Response.ok(materias.get(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        if (materias.containsKey(id)) {
            materias.remove(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}