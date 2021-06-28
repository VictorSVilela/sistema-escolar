package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UsuarioRepository extends BaseRepository<Usuario>{

    public UsuarioRepository() {
        super(Usuario.class);
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        List<Usuario> usuarios = criteria.list();

        return usuarios;
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
}
