package br.com.grupoitss.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.grupoitss.repository.EscolaRepository;
import br.com.grupoitss.model.Escola;

@Path("/escolas")
public class EscolaController {

    private static final Map<Long, Escola> escolas = new HashMap<>();

    private final EscolaRepository repository = new EscolaRepository();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Escola cadastrar(Escola escola){
        escola.setId(1l);
        return escolas.put(escola.getId(), escola);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String atualizar(Escola escola){

        Escola entity = new Escola();

        try {

            entity.setNome(escola.getNome());

            repository.atualizar(entity);

            return "Registro atualizado com sucesso!";

        } catch (Exception e) {

            return "Erro ao atualizar o registro " + e.getMessage();

        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Escola> todasEscolas(){

        List<Escola> escolas =  new ArrayList<Escola>();

        List<Escola> listaEntityEscolas = repository.todasEscolas();

        for (Escola entity : listaEntityEscolas) {

            escolas.add(new Escola(entity.getId(), entity.getNome()));
        }

        return escolas;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Escola consultar(@PathParam("id") Long id){

        Escola entity = repository.getEscola(id);

        if(entity != null)
            return new Escola(entity.getId(), entity.getNome());

        return null;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String remover(@PathParam("id") Long id){

        try {

            repository.remover(id);

            return "Registro excluido com sucesso!";

        } catch (Exception e) {

            return "Erro ao excluir o registro! " + e.getMessage();
        }

    }

}
