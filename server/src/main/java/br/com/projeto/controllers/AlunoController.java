package br.com.projeto.controllers;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Aluno;
import br.com.projeto.service.AlunoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
public class AlunoController {

    private static final AlunoService alunoService = new AlunoService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionar(Aluno aluno) throws RegraNegocioException {
        return Response.status(Response.Status.CREATED).entity(alunoService.inserir(aluno)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        return Response.ok().entity(alunoService.consultar(id)).build();
    }

    @GET
    @Path("/gerar-matricula/{turmaId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response gerarMatricula(@PathParam("turmaId") Long turmaId) {
        return Response.ok().entity(alunoService.consultar(turmaId)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(Aluno aluno) throws RegraNegocioException {
        return Response.ok().entity(alunoService.editar(aluno)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        alunoService.deletar(id);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(alunoService.listarTodos()).build();
    }

}
