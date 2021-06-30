package br.com.projeto.service;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Aluno;
import br.com.projeto.model.Turma;
import br.com.projeto.repository.AlunoRepository;
import br.com.projeto.repository.TurmaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TurmaService {

    private static final TurmaRepository turmaRepository = new TurmaRepository();
    private static final AlunoRepository alunoRepository = new AlunoRepository();
    private static final AlunoService alunoService = new AlunoService();

    public Turma inserir(Turma turma) throws RegraNegocioException {
        if (turmaRepository.verificaSeNomeJaCadastrado(turma.getNome())){
            throw new RegraNegocioException("Já existe uma turma cadastrada com esse nome!");
        }

        for (Long id : turma.getAlunosIds()) {
            if(alunoRepository.verificaSeAlunoTemTurma(id)){
                throw new RegraNegocioException("A turma não pode ser cadastrada pois um ou mais alunos estão cadastrados em outras turmas!");
            }
        }
        criarSequencia(turma);
        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(alunoId -> turma.getAlunos().add(new Aluno(alunoId)));
        Turma turmas = turmaRepository.salvar(turma);
        alunoService.updateSequencia(turma.getAlunosIds(), 0L);

        return turmas;
    }

    private void criarSequencia(Turma turma) {
        Long result = turmaRepository.ultimaSequencia(turma).orElse(0L);
        turma.setSequencia(result + 1);
    }

    public String criarMatricula(Long cursoId) {
        Object[] sequenciaSiglaCurso = turmaRepository.consultarMaiorSequenciaDaTurmaESiglaDoCurso(cursoId);

        Long sequencia = (Long) sequenciaSiglaCurso[0];
        String siglaCurso = String.valueOf(sequenciaSiglaCurso[1]);

        return String.format("%s - %d", siglaCurso, ++sequencia);
    }

    public Turma consultar(Long id) {
        return turmaRepository.obter(id);
    }

    public List<Turma> listarTodas() {
        return turmaRepository.listarTodas();
    }

    public Turma editar(Turma turma) throws RegraNegocioException {
        if (turmaRepository.verificaSeNomeJaCadastradoESeEMesmoNome(turma.getNome(),turma.getId()).isPresent()){
            throw new RegraNegocioException("Já existe uma turma cadastrada com esse nome!");
        }

        if (!turma.getCurso().getId().equals(turmaRepository.consultarCursoDaTurma(turma.getId()))) {
            criarSequencia(turma);
        }

        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(id -> turma.getAlunos().add(new Aluno(id)));
        Long sequencialDosAlunos = turmaRepository.obterMaiorSequencialDoAlunosDeUmaTurma(turma.getId()).orElse(0L);
        List<Long> idsAlunosDaTurma = turmaRepository.obterIdAlunosDaTurma(turma.getId());
        verificaSeAlunoTemTurma(idsAlunosDaTurma,turma.getAlunosIds());
        List<Long> idsParaRemoverSequecia = obterIdsDosAlunosParaRemoverSequencia(idsAlunosDaTurma, turma.getAlunosIds());
        List<Long> idsParaInserirSequencia = obterIdsDosAlunosParaAdicionarSequencia(turma.getAlunosIds(), idsAlunosDaTurma);
        Turma turmaEditada = turmaRepository.editar(turma);
        alunoService.removerSequencias(idsParaRemoverSequecia);
        alunoService.updateSequencia(idsParaInserirSequencia, sequencialDosAlunos);

        return turmaEditada;
    }


    public void deletar(Long id) {
        List<Long> idsAlunosTurma = turmaRepository.obterIdAlunosDaTurma(id);
        turmaRepository.deletar(id);
        alunoService.removerSequencias(idsAlunosTurma);
    }

    private List<Long> obterIdsDosAlunosParaRemoverSequencia(List<Long> idsAlunosDaTurmaOriginal, List<Long> idsAlunosDaNovaTurma) {
        return idsAlunosDaTurmaOriginal.stream().filter(id -> !idsAlunosDaNovaTurma.contains(id)).collect(Collectors.toList());
    }

    private List<Long> obterIdsDosAlunosParaAdicionarSequencia(List<Long> idsAlunosDaNovaTurma, List<Long> idsAlunosDaTurma) {
        return idsAlunosDaNovaTurma.stream().filter(id -> !idsAlunosDaTurma.contains(id)).collect(Collectors.toList());
    }

    private void verificaSeAlunoTemTurma(List<Long> idsAlunosDaTurma, List<Long> alunosIds) throws RegraNegocioException {
        for (Long novoId : alunosIds) {
            if(alunoRepository.verificaSeAlunoTemTurma(novoId) && !idsAlunosDaTurma.contains(novoId)){
                throw new RegraNegocioException("A turma não pode ser editada: um ou mais alunos já estão matriculados em outra turma!");
            }

        }
    }
}
