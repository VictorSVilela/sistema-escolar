package br.com.projeto.service;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Aluno;
import br.com.projeto.repository.AlunoRepository;
import br.com.projeto.repository.TurmaRepository;

import java.util.Date;
import java.util.List;

public class AlunoService{

    private static final AlunoRepository alunoRepository = new AlunoRepository();
    private static final TurmaRepository turmaRepository = new TurmaRepository();


    public Object inserir(Aluno aluno) throws RegraNegocioException {
        if (alunoRepository.verificaSeNomeJaCadastrado(aluno.getNome())) {
            throw new RegraNegocioException("Já existe um aluno cadastrado com esse nome!");
        }

        if (alunoRepository.verificaSeEmailJaCadastrado(aluno.getEmail())) {
            throw new RegraNegocioException("Já existe um aluno cadastrado com esse email!");
        }
        aluno.setDataDaMatricula(new Date());
        return alunoRepository.salvar(aluno);
    }

    public Aluno consultar(Long id) {
        Aluno aluno = alunoRepository.obter(id);
        aluno.setNomeTurma(turmaRepository.consultarNomeDaTurma(aluno.getId()));
        return aluno;
    }

    public List<Aluno> listarTodos(){
        return alunoRepository.listarTodos();
    }

    public Aluno editar(Aluno aluno) throws RegraNegocioException {
        if (alunoRepository.verificaSeNomeJaCadastradoESeEMesmoNome(aluno.getNome(), aluno.getId()).isPresent()) {
            throw new RegraNegocioException("Já existe um aluno cadastrado com esse nome!");
        }

        if (alunoRepository.verificaSeEmailJaCadastradoESeEMesmoEmail(aluno.getEmail(), aluno.getId()).isPresent()) {
            throw new RegraNegocioException("Já existe um aluno cadastrado com esse email!");
        }
        return alunoRepository.editar(aluno);
    }

    public void deletar(Long id) {
        alunoRepository.deletar(id);
    }

    public void updateSequencia(List<Long> ids, Long sequencial) {
        alunoRepository.updateSequencia(ids, sequencial + 1);
    }

    public void removerSequencias(List idsParaRemoverSequencias) {
        alunoRepository.removerSequencias(idsParaRemoverSequencias);
    }
}
