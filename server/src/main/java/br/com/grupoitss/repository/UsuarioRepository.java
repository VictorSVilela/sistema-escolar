package br.com.grupoitss.repository;

import br.com.grupoitss.config.HibernateConfig;
import br.com.grupoitss.model.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class UsuarioRepository extends BaseRepository<Usuario>{

    public UsuarioRepository() {
        super(Usuario.class);
    }

    public List<Usuario> listar() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Usuario.class);
        List<Usuario> usuarios = criteria.list();

        return usuarios;
    }
}
