package br.com.projeto.service;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Aluno;
import br.com.projeto.model.Turma;
import br.com.projeto.repository.AlunoRepository;
import br.com.projeto.repository.TurmaRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        aluno.setTurmas(listarTurmasDoAluno(aluno.getId()));
        return aluno;
    }

    private Set<Turma> listarTurmasDoAluno(Long alunoId){
        Criteria criteria = HibernateConfig.getSessionFactory().openSession().createCriteria(Aluno.class, "bean");
        criteria.createAlias("bean.turmas", "turmas");

        criteria.add(Restrictions.eq("bean.id", alunoId));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("turmas.id").as("id"));
        projectionList.add(Projections.property("turmas.nome").as("nome"));
        projectionList.add(Projections.property("turmas.matricula").as("matricula"));
        criteria.setProjection(projectionList);

        criteria.setResultTransformer(new AliasToBeanResultTransformer(Turma.class));
        List<Turma> turmas = criteria.list();
        return turmas != null ? new HashSet<>(turmas) : new HashSet<>();
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

    public void removerSequencias(List<Long> idsParaRemoverSequencias) {
        alunoRepository.removerSequencias(idsParaRemoverSequencias);
    }
}
