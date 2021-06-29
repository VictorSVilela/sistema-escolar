package br.com.projeto.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Escola;
import br.com.projeto.service.EscolaService;

@Path("/escolas")
public class EscolaController {

    private static final EscolaService escolaService = new EscolaService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response incluir(Escola escola) throws RegraNegocioException {
        if (escola.getDiretor() == null) {
            return Response.status(400).build();
        }

        if (escola.getNome().length() > 255) {
            escola.setNome(escola.getNome().substring(0, 254));
        }

        if (escola.getDescricao().length() > 300) {
            escola.setDescricao(escola.getDescricao().substring(0, 299));
        }

        return Response.status(Response.Status.CREATED).entity(escolaService.inserir(escola)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id") Long id) {
        return Response.ok().entity(escolaService.consultar(id)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        return Response.ok().entity(escolaService.listarTodas()).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response alterar(Escola escola) throws RegraNegocioException {
        return Response.ok().entity(escolaService.editar(escola)).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletar(@PathParam("id") Long id) {
        escolaService.deletar(id);
        return Response.ok().build();
    }


}
