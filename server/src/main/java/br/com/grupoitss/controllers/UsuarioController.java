package br.com.grupoitss.controllers;

import br.com.grupoitss.model.Usuario;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    public Response consultar(Long id) {
        return null;
    }


}
