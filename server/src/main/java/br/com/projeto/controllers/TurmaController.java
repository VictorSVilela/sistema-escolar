package br.com.projeto.controllers;

import br.com.projeto.model.Turma;
import br.com.projeto.service.TurmaService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/turmas")
public class TurmaController {

    private static final TurmaService turmaService = new TurmaService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluir(Turma turma) {
        Turma result = turmaService.inserir(turma);
        return Response.status(Response.Status.CREATED).entity(new Turma(result.getId())).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        return Response.ok().entity(turmaService.consultar(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(turmaService.listarTodas()).build();
    }

    @GET
    @Path("/gerar-matricula/{cursoId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response gerarMatricula(@PathParam("cursoId") Long cursoId) {
        return Response.ok().entity(turmaService.criarMatricula(cursoId)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(Turma turma) {
        return Response.ok().entity(turmaService.editar(turma)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        turmaService.deletar(id);
        return Response.ok().build();
    }

}