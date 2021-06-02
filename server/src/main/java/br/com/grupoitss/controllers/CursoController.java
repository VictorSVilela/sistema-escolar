package br.com.grupoitss.controllers;

import br.com.grupoitss.model.Curso;
import br.com.grupoitss.model.Materia;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/cursos")
public class CursoController {

    private static final Map<Long, Curso> cursos = new HashMap<>();
    private static Long idGerador = 1L;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Curso incluir(Curso curso) {
        /**
         * Java 8
         * Stream API
         * Optional API
         */
        Set<Materia> materias = new HashSet<>();
        for (Long aLong : curso.getMateriasIds()) {
            Materia materia = new Materia(aLong);
            materias.add(materia);
        }

        curso.setMaterias(materias);

        curso.setId(idGerador++);
        cursos.put(curso.getId(), curso);
        return curso;//DTO
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        if (cursos.containsKey(id)) {
            Curso curso = cursos.get(id);
            return Response.ok(curso).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok(cursos.values()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Curso curso) {
        if (cursos.containsKey(id)) {
            curso.setId(id);
            cursos.put(id, curso);
            return Response.ok(cursos.get(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        if (cursos.containsKey(id)) {
            cursos.remove(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}