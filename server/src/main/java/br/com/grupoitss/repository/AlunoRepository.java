package br.com.grupoitss.repository;

import br.com.grupoitss.config.HibernateConfig;
import br.com.grupoitss.model.Aluno;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.List;
import java.util.Optional;

public class AlunoRepository extends BaseRepository<Aluno> {

    public AlunoRepository() {
        super(Aluno.class);
    }

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

        return alunos;
    }
}

