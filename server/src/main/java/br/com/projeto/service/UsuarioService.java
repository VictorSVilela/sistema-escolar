package br.com.projeto.service;

import br.com.projeto.handler.RegraNegocioHandler;
import br.com.projeto.model.Usuario;
import br.com.projeto.repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private static final UsuarioRepository usuarioRepository= new UsuarioRepository();

    public Usuario inserir (Usuario usuario) throws RegraNegocioHandler {
        if (usuarioRepository.verificaSeNomeJaCadastrado(usuario.getNome())){
            throw new RegraNegocioHandler("Já existe um usuário cadastrado com esse nome!");
        }
        return usuarioRepository.salvar(usuario);
    }

    public Usuario consultar(Long id) {
        Usuario usuario = usuarioRepository.obter(id);
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarioRepository.listar();
    }

    public Usuario editar(Usuario usuario) throws RegraNegocioHandler {
        if(usuarioRepository.verificaSeNomeJaCadastradoESeEMesmoNome(usuario.getNome(), usuario.getId())){
            throw new RegraNegocioHandler("Já existe um usuário com esse nome!");
        }
        return usuarioRepository.editar(usuario);
    }

    public void deletar(Long id){
        usuarioRepository.deletar(id);
    }
}
