package br.com.projeto.service;

import br.com.projeto.model.Escola;
import br.com.projeto.repository.EscolaRepository;

import java.util.List;

public class EscolaService {

    private static final EscolaRepository escolaRepository= new EscolaRepository();

    public Escola inserir (Escola escola){
        escola.setStatus(true);
        return escolaRepository.salvar(escola);
    }

    public Escola consultar(Long id) {
        Escola escola = escolaRepository.obter(id);
        return escolaRepository.obter(id);
    }

    public List<Escola> listarTodas(){
        return escolaRepository.listar();
    }

    public Escola editar(Escola escola) {
        return escolaRepository.editar(escola);
    }

    public void deletar(Long id){
        escolaRepository.deletar(id);
    }

}
