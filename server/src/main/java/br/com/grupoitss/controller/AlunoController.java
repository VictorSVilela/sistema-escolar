package br.com.grupoitss.controller;

import br.com.grupoitss.model.Aluno;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/alunos")
public class AlunoController {

    private static final Map<Long, Aluno> alunos = new HashMap<>();
    private static Long idGerador = 1L;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno adicionar(Aluno aluno) {
        aluno.setId(idGerador ++);
        alunos.put(aluno.getId(), aluno);
        return aluno;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        if(alunos.containsKey(id)){
            Aluno aluno = alunos.get(id);
            return Response.ok(aluno).build();
        }
       return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Aluno aluno) {
        if(alunos.containsKey(id)){
            aluno.setId(id);
            alunos.put(id, aluno);
            return Response.ok(alunos.get(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        if (alunos.containsKey(id)){
            alunos.remove(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok(alunos.values()).build();
    }

}
