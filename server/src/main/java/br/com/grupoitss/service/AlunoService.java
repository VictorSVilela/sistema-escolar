package br.com.grupoitss.service;

import br.com.grupoitss.model.Aluno;
import br.com.grupoitss.repository.AlunoRepository;

import java.util.Date;
import java.util.List;

public class AlunoService {

    private static final AlunoRepository alunoRepository = new AlunoRepository();

    public Aluno inserir(Aluno aluno) {
        aluno.setDataDaMatricula(new Date());
        return alunoRepository.salvar(aluno);
    }

    public Aluno consultar(Long id) {
        Aluno aluno = alunoRepository.obter(id);
        return aluno;
    }

    public List<Aluno> listarTodos(){
        return alunoRepository.listarTodos();
    }

    public Aluno editar(Aluno aluno) {
        return alunoRepository.editar(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deletar(id);
    }

}
