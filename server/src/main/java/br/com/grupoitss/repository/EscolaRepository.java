package br.com.grupoitss.repository;

import br.com.grupoitss.config.HibernateConfig;
import br.com.grupoitss.model.Escola;
import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EscolaRepository extends BaseRepository<Escola> {

    public EscolaRepository() {
        super(Escola.class);
    }

    public List<Escola> listar() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Escola.class, "bean")
                .createAlias("bean.diretor", "diretorEscola")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("diretorEscola.nome").as("diretor.nome"))
                        .add(Projections.property("bean.ativa").as("ativa"))
                        .add(Projections.property("bean.descricao").as("descricao"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Escola> escolas = new ArrayList<>();

        return escolas;
    }

}
