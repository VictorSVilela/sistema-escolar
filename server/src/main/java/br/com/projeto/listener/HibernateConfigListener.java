package br.com.projeto.listener;

import br.com.projeto.config.HibernateConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class HibernateConfigListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        HibernateConfig.initSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateConfig.endSessionFactory();
    }
}
