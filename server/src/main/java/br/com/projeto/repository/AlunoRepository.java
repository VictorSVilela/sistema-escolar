package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Aluno;
import br.com.projeto.model.Turma;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class AlunoRepository extends BaseRepository<Aluno> {

    private TurmaRepository turmaRepository = new TurmaRepository();

    public AlunoRepository() {
        super(Aluno.class);
    }

    @SuppressWarnings("unchecked")
    public List<Aluno> listarTodos() {

        Criteria criteria = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Aluno.class, "bean")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.email").as("email"))
                        .add(Projections.property("bean.idade").as("idade"))
                        .add(Projections.property("bean.dataDaMatricula").as("dataDaMatricula"))
                        .add(Projections.property("bean.sequencia").as("sequencia"))
                ))
                .setResultTransformer(Transformers.aliasToBean(this.getTClass()));

        List<Aluno> alunos = criteria.list();

        alunos.forEach(aluno -> {
            aluno.setMatricula(gerarMatriculaAluno(aluno));
        });

        return alunos;
    }

    private String gerarMatriculaAluno(Aluno aluno){
        Turma turma = consultarTurmaDoAluno(aluno.getId());
        return turma.getMatricula() + " - " + aluno.getSequencia();
    }

    private Turma consultarTurmaDoAluno(Long alunoId){
        Criteria criteria = HibernateConfig.getSessionFactory().openSession().createCriteria(Turma.class, "bean");
        criteria.createAlias("bean.alunos", "alunos");

        criteria.add(Restrictions.eq("alunos.id", alunoId));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("bean.id").as("id"));
        projectionList.add(Projections.property("bean.matricula").as("matricula"));

        criteria.setProjection(projectionList);
        criteria.setResultTransformer(new AliasToBeanResultTransformer(Turma.class));
        return (Turma) criteria.uniqueResult();
    }

    public void updateSequencia(List<Long> ids, Long sequencial) {
        AtomicLong sequencia = new AtomicLong(sequencial);

        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        ids.forEach(id -> {
            Aluno alunoPersistent = session.find(this.getTClass(),id);
            alunoPersistent.setSequencia(sequencia.getAndSet(sequencia.get() + 1));
        });
        session.getTransaction().commit();
        this.session.close();
    }

    public void removerSequencias(List<Long> ids) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        ids.forEach(id -> {
            Aluno alunoPersistent = session.find(this.getTClass(),id);
            alunoPersistent.setSequencia(null);
        });
        session.getTransaction().commit();
        this.session.close();
    }

    public boolean verificaSeNomeJaCadastrado(String nome) {
        Long count = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome",nome))
                .setProjection(Projections.count("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return count > 0;
    }

    public boolean verificaSeEmailJaCadastrado(String email) {
        Long count = (Long)  HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.email",email))
                .setProjection(Projections.property("bean.email").as("email"))
                .setMaxResults(1)
                .uniqueResult();

        return count > 0;
    }
}

