package br.com.grupoitss.dao;

import br.com.grupoitss.model.Escola;
import org.hibernate.*;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EscolaDao {

    private final EntityManagerFactory emf;

    private final EntityManager em;

    public EscolaDao(){
        this.emf = Persistence.createEntityManagerFactory("persistence_unit_sistema_escolar");

        this.em = this.emf.createEntityManager();
    }

    public void cadastrar(Escola objeto) {
        this.em.getTransaction().begin();
        this.em.persist(objeto);
        this.em.getTransaction().commit();
    }

    public void atualizar(Escola objeto) {
        this.em.getTransaction().begin();
        this.em.merge(objeto);
        this.em.getTransaction().commit();
    }

    public List<Escola> todasEscolas(){
        Session session = em.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Escola.class)
                .addOrder(Order.asc("nome"));

        return criteria.list();
    }

    public Escola getEscola(Long id){
        return this.em.find(Escola.class, id);
    }

    public void remover(Long id) {
        Escola pessoa = this.getEscola(id);
        this.em.getTransaction().begin();
        this.em.remove(pessoa);
        this.em.getTransaction().commit();
    }

}
