package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Curso;
import br.com.projeto.model.Turma;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TurmaRepository extends BaseRepository<Turma> {

    public TurmaRepository() {
        super(Turma.class);
    }

    public List<Turma> listarTodas() {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(this.getTClass(), "bean");
        criteria.createAlias("bean.curso", "turmaCurso");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.nome").as("nome"));
        projectionList.add(Projections.property("bean.matricula").as("matricula"));
        projectionList.add(Projections.property("bean.sequencia").as("sequencia"));
        projectionList.add(Projections.property("turmaCurso.id").as("cursoId"));
        projectionList.add(Projections.property("turmaCurso.nome").as("cursoNome"));
        criteria.setProjection(Projections.distinct(projectionList));

        criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

        List<Map<String, Object>> result = criteria.list();

        List<Turma> turmas = new ArrayList<>();

        result.forEach(objectMap -> turmas.add(setTurma(objectMap)));
        this.session.close();
        return turmas;
    }

    private Turma setTurma(Map<String, Object> objectMap) {
        Turma turma = new Turma();
        Curso curso = new Curso();

        curso.setId((Long) objectMap.get("cursoId"));
        curso.setNome((String) objectMap.get("cursoNome"));

        turma.setId((Long) objectMap.get("id"));
        turma.setNome((String) objectMap.get("nome"));
        turma.setMatricula((String) objectMap.get("matricula"));
        turma.setCurso(curso);
        return turma;
    }

    public Optional<Long> ultimaSequencia(Turma turma) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.curso.id", turma.getCurso().getId()))
                .setProjection(Projections.max("bean.sequencia").as("sequencia"))
                .setMaxResults(1)
                .uniqueResult();

        return result == null ? Optional.empty() : Optional.of(result);
    }

    public Object[] consultarMaiorSequenciaDaTurmaESiglaDoCurso(Long cursoId) {
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.max("bean.sequencia"));
        projectionList.add(Projections.groupProperty("curso.sigla"));

        return (Object[]) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Turma.class, "bean")
                .createAlias("bean.curso", "curso")
                .add(Restrictions.eq("curso.id", cursoId))
                .setProjection(projectionList)
                .setMaxResults(1)
                .uniqueResult();
    }

    public Long consultarCursoDaTurma(Long id) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.curso", "curso")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.property("curso.id"))
                .setMaxResults(1)
                .uniqueResult();

        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Long> obterIdAlunosDaTurma(Long id) {
        List<Long> result = (List<Long>) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.property("alunos.id"))
                .list();

        return result;
    }


    public Optional<Long> obterMaiorSequencialDoAlunosDeUmaTurma(Long id) {
        Long result = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("bean.id", id))
                .setProjection(Projections.max("alunos.sequencia"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Turma editar(Turma turma) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        this.session.beginTransaction();
        Turma turmaEditada = (Turma) this.session.merge(turma);
        this.session.getTransaction().commit();
        turmaEditada.setAlunosIds(new ArrayList<>());
        turmaEditada.setCurso(turma.getCurso());
        turmaEditada.setAlunos(turma.getAlunos());
        turmaEditada.getAlunos().forEach(aluno -> {
            turmaEditada.getAlunosIds().add(aluno.getId());
        });
        turmaEditada.getCurso().setEscola(null);
        turmaEditada.getCurso().setCoordenador(null);
        turmaEditada.getCurso().setMaterias(null);
        turmaEditada.getCurso().setDescricao(null);
        turmaEditada.getCurso().setNome(null);
        turmaEditada.setAlunos(null);

        this.session.close();
        return turmaEditada;
    }

    @SuppressWarnings("unchecked")
    public Turma obter(Long id) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Turma turma = this.session.get(this.getTClass(), id);
        Optional.ofNullable(turma).ifPresent(elem -> {
            turma.setAlunosIds(new ArrayList<>());
            turma.setCurso(turma.getCurso());
            turma.setAlunos(turma.getAlunos());
            turma.getAlunos().forEach(aluno -> {
                turma.getAlunosIds().add(aluno.getId());
            });
            turma.getCurso().setEscola(null);
            turma.getCurso().setCoordenador(null);
            turma.getCurso().setMaterias(null);
            turma.getCurso().setDescricao(null);
            turma.getCurso().setNome(null);
            turma.setAlunos(null);
        });
        this.session.close();
        return turma;

    }

    public String consultarNomeDaTurma(Long id) {
        return (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .createAlias("bean.alunos", "alunos")
                .add(Restrictions.eq("alunos.id", id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();
    }

    public boolean verificaSeNomeJaCadastrado(String nome) {
        return validarPropriedadeUnica("nome", nome);
    }

    public boolean verificaSeNomeJaCadastradoESeEMesmoNome(String nome, Long id) {
        return validarPropriedadeUnica("nome", id, nome);
    }
}
