package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Optional;

public abstract class BaseRepository<T> {

    protected Session session;
    private final Class<T> tClass;

    public BaseRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    public Class<T> getTClass() {
        return tClass;
    }

    public T salvar(T object) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    public T obter(Long id) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        T object = (T) session.get(getTClass(), id);
        session.getTransaction().commit();
        session.close();
        return object;
    }

    public T editar(T objectSave) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        this.session.beginTransaction();
        T result = (T) session.merge(objectSave);
        this.session.getTransaction().commit();
        this.session.close();
        return result;

    }

    public void deletar(Long id) {
        Object object = obter(id);
        this.session = HibernateConfig.getSessionFactory().openSession();
        this.session.beginTransaction();
        Optional.ofNullable(object).ifPresent(presentObject -> session.delete(presentObject));
        session.getTransaction().commit();
        session.close();
    }

    public boolean validarPropriedadeUnica(String atributo, Long id, Object valor){
        Criteria criteria = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean." + atributo, valor))
                .setProjection(Projections.count("bean." + atributo))
                .setMaxResults(1);

        if(id != null){
            criteria.add(Restrictions.ne("bean.id",id));
        }

        Long count = (Long) criteria.uniqueResult();
        return count > 0;
    }
    public boolean validarPropriedadeUnica(String atributo, Object valor){
        return  validarPropriedadeUnica(atributo, null, valor);
    }

    public void openSession() {
        this.session = HibernateConfig.getSessionFactory().openSession();
        this.session.beginTransaction();
    }

    public void commitAndClose() {
        this.session.getTransaction().commit();
        this.session.close();
    }

}
