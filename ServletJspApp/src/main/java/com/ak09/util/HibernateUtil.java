package com.ak09.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ak09.config.Config;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
        	Config config = new Config();

            Properties hibernateProperties = new Properties();
            hibernateProperties.put("hibernate.connection.driver_class", config.getProperty("driverClass"));
            hibernateProperties.put("hibernate.connection.url", config.getProperty("url"));
            hibernateProperties.put("hibernate.connection.username", config.getProperty("userName"));
            hibernateProperties.put("hibernate.connection.password", config.getProperty("passString"));

            // Load the hibernate.cfg.xml and apply dynamic properties
            Configuration configuration = new Configuration().configure();

            // Apply dynamic properties
            for (String key : hibernateProperties.stringPropertyNames()) {
                configuration.setProperty(key, hibernateProperties.getProperty(key));
            }

            // Build the session factory
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}