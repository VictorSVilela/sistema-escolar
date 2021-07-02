package br.com.projeto.controllers;

import br.com.projeto.handler.RegraNegocioHandler;
import br.com.projeto.model.Usuario;
import br.com.projeto.service.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioController {

    private static final UsuarioService usuarioService = new UsuarioService();


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluir(Usuario usuario) throws RegraNegocioHandler {
        return Response.status(Response.Status.CREATED).entity(usuarioService.inserir(usuario)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        return Response.ok().entity(usuarioService.consultar(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(usuarioService.listar()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(Usuario usuario) throws RegraNegocioHandler {
        return Response.ok().entity(usuarioService.editar(usuario)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        usuarioService.deletar(id);
        return Response.ok().build();
    }
}