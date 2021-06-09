package br.com.grupoitss.service;

import br.com.grupoitss.model.Usuario;
import br.com.grupoitss.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private static final UsuarioRepository usuarioRepository= new UsuarioRepository();

    public Usuario inserir (Usuario usuario) {
        return usuarioRepository.salvar(usuario);
    }

    public Usuario consultar(Long id) {
        Usuario usuario = usuarioRepository.obter(id);
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.listar();
    }

    public Usuario editar(Usuario usuario) {
        return usuarioRepository.editar(usuario);
    }

    public void deletar(Long id){
        usuarioRepository.deletar(id);
    }
}
