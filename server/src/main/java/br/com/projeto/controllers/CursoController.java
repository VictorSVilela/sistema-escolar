package br.com.projeto.controllers;

import br.com.projeto.handler.RegraNegocioHandler;
import br.com.projeto.model.Curso;
import br.com.projeto.service.CursoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cursos")
public class CursoController {

    private static final CursoService cursoService = new CursoService();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluir(Curso curso) throws RegraNegocioHandler {
        return Response.status(Response.Status.CREATED).entity(cursoService.inserir(curso)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        return Response.ok().entity(cursoService.consultar(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(cursoService.listarTodos()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Curso curso) throws RegraNegocioHandler {
        return Response.ok().entity(cursoService.editar(curso)).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        cursoService.deletar(id);
        return Response.ok().build();
    }

}