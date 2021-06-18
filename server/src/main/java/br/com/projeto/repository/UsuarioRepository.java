package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;

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
}
