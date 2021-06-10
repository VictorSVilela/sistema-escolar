package br.com.grupoitss.controllers;

import br.com.grupoitss.model.Materia;
import br.com.grupoitss.service.MateriaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/materias")
public class MateriaController {

    private static final MateriaService materiaService = new MateriaService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluir(Materia materia) {
        return Response.status(Response.Status.CREATED).entity(materiaService.inserir(materia)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        return Response.ok().entity(materiaService.consultar(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(materiaService.listarTodas()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Materia materia) {
        return Response.ok().entity(materiaService.editar(materia)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        materiaService.deletar(id);
        return Response.ok().build();
    }
}