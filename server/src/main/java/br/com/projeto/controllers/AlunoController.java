package br.com.projeto.controllers;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.handler.RegraNegocioHandler;
import br.com.projeto.model.Aluno;
import br.com.projeto.service.AlunoService;

import javax.validation.ConstraintViolationException;
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
        alunoService.editar(aluno);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
       try {
           alunoService.deletar(id);
           return Response.ok().build();
       } catch (Exception exception){
           return Response.status(500).entity(new RegraNegocioHandler("O aluno não pode ser deletado pois está sendo usado em outro local")).build();
       }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(alunoService.listarTodos()).build();
    }

}
