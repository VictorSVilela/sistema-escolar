package br.com.grupoitss.service;

import br.com.grupoitss.model.Aluno;
import br.com.grupoitss.model.Turma;
import br.com.grupoitss.repository.AlunoRepository;
import br.com.grupoitss.repository.TurmaRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TurmaService {

    private static final TurmaRepository turmaRepository = new TurmaRepository();
    private static final AlunoService alunoService = new AlunoService();
    private static final AlunoRepository alunoRepository = new AlunoRepository();

    public Turma inserir(Turma turma) {
        criarSequencia(turma);
        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(alunoId -> turma.getAlunos().add(new Aluno(alunoId)));
        Turma  turmas = turmaRepository.salvar(turma);
        alunoService.updateSequencia(turma.getAlunosIds(), 0L);

        return turmas;
    }

    private void criarSequencia(Turma turma) {
        Long result = turmaRepository.ultimaSequencia(turma).orElse(0L);
        turma.setSequencia(result + 1);
    }

    public String criarMatricula(String sigla, Long id) {
        Long result;

        if (Optional.ofNullable(id).isPresent() && turmaRepository.consultarSiglaCurso(sigla, id).isPresent()) {
            result = turmaRepository.consultarSequencia(id);
            return String.format("%s - %d", sigla, result);
        }

        result = turmaRepository.ultimaSequencia(sigla).orElse(0L);
        return String.format("%s - %d", sigla, result + 1);
    }

    public Turma consultar(Long id) {
        Turma turma = turmaRepository.obter(id);
        return turma;
    }


    public List<Turma> listarTodas() {
        return turmaRepository.listarTodas();
    }

    public Turma editar(Turma turma) {

        if (!turma.getCurso().getId().equals(turmaRepository.consultarCursoDaTurma(turma.getId()))) {
            criarSequencia(turma);
        }

        turma.setAlunos(new HashSet<>());
        turma.getAlunosIds().forEach(id -> turma.getAlunos().add(new Aluno(id)));
        Long sequencialDosAlunos = turmaRepository.obterMaiorSequencialDoAlunosDeUmaTurma(turma.getId()).orElse(0L);
        List<Long> idsAlunosDaTurmaOriginal = turmaRepository.obterIdAlunosDaTurmaOriginal(turma.getId());
        List<Long> idsParaRemoverSequecia = obterIdsDosAlunosParaRemoverSequencia(idsAlunosDaTurmaOriginal, turma.getAlunosIds());
        List<Long> idsParaInserirSequencia = obterIdsDosAlunosParaAdicionarSequencia(turma.getAlunosIds(), idsAlunosDaTurmaOriginal);
        Turma turmaEditada = turmaRepository.editar(turma);
        alunoService.removerSequencias(idsParaRemoverSequecia);
        alunoService.updateSequencia(idsParaInserirSequencia, sequencialDosAlunos);

        return turmaEditada;
    }


    public void deletar(Long id) {
        List<Long> idsAlunosTurma = turmaRepository.obterIdAlunosDaTurmaOriginal(id);
        turmaRepository.deletar(id);
        alunoService.removerSequencias(idsAlunosTurma);
    }

    private List<Long> obterIdsDosAlunosParaRemoverSequencia(List<Long> idsAlunosDaTurmaOriginal, List<Long> idsAlunosDaNovaTurma) {
        return idsAlunosDaTurmaOriginal.stream().filter(id -> !idsAlunosDaNovaTurma.contains(id)).collect(Collectors.toList());
    }

    private List<Long> obterIdsDosAlunosParaAdicionarSequencia(List<Long> idsAlunosDaNovaTurma, List<Long> idsAlunosDaTurma) {
        return idsAlunosDaNovaTurma.stream().filter(id -> !idsAlunosDaTurma.contains(id)).collect(Collectors.toList());
    }
}
