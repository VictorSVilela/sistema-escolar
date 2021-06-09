package br.com.grupoitss.config;

import br.com.grupoitss.model.Aluno;
import br.com.grupoitss.model.Escola;
import br.com.grupoitss.model.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static void initSessionFactory(){
        if(sessionFactory != null){
            System.out.println("SESSÃO JÁ INICIADA");
            return;
        }

        Configuration config = new Configuration();
        config.configure();
        config.addAnnotatedClass(Aluno.class);
        config.addAnnotatedClass(Usuario.class);
        config.addAnnotatedClass(Escola.class);
        sessionFactory = config.buildSessionFactory();
    }

    public static void endSessionFactory(){
        if (sessionFactory!=null){
            System.out.println("FINALIZANDO SESSÃO");
            sessionFactory.close();
        }
    }

    public static SessionFactory getSessionFactory (){
        return  sessionFactory;
    }

}