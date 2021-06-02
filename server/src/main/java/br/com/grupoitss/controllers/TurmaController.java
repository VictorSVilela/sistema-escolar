package br.com.grupoitss.controllers;

import br.com.grupoitss.model.Aluno;
import br.com.grupoitss.model.Turma;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Path("/turmas")
public class TurmaController {

    private static final Map<Long, Turma> turmas = new HashMap<>();
    private static Long idGerador = 1L;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Turma incluir(Turma turma) {
        Set<Aluno> alunos = new HashSet<>();
        for (Long aLong : turma.getAlunosIds()) {
            Aluno aluno = new Aluno(aLong);
            alunos.add(aluno);
        }

        turma.setAlunos(alunos);

        turma.setId(idGerador++);
        turmas.put(turma.getId(), turma);
        return turma;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        if (turmas.containsKey(id)) {
            Turma turma = turmas.get(id);
            return Response.ok(turma).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok(turmas.values()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Turma turma) {
        if (turmas.containsKey(id)) {
            turma.setId(id);
            turmas.put(id, turma);
            return Response.ok(turmas.get(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        if (turmas.containsKey(id)) {
            turmas.remove(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}