package br.com.grupoitss.controllers;

import br.com.grupoitss.model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/usuarios")
public class UsuarioController {

    private static final Map<Long, Usuario> usuarios = new HashMap<>();
    private static Long idGerador = 1L;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario incluir(Usuario usuario) {
        usuario.setId(idGerador++);
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        if(usuarios.containsKey(id)) {
            Usuario usuario = usuarios.get(id);
            return Response.ok(usuario).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok(usuarios.values()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(@PathParam("id") Long id, Usuario usuario) {
        if(usuarios.containsKey(id)){
            usuario.setId(id);
            usuarios.put(id, usuario);
            return Response.ok(usuarios.get(id)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        if (usuarios.containsKey(id)){
            usuarios.remove(id);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}