package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Aluno;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AlunoRepository extends BaseRepository<Aluno> {

    private TurmaRepository turmaRepository = new TurmaRepository();

    public AlunoRepository() {
        super(Aluno.class);
    }

    @SuppressWarnings("unchecked")
    public List<Aluno> listarTodos() {

        Criteria criteria = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(getTClass(), "bean")
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

        alunos.forEach(aluno ->{
            aluno.setMatricula(turmaRepository.consultarSiglaAluno(aluno.getId()));
        });

        return alunos;
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
}

