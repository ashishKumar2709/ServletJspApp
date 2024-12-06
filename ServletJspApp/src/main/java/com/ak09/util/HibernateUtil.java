package com.ak09.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;

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
            // Enable statistics globally
            Statistics stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError("SessionFactory creation failed: " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void logCacheStatistics() {
        Statistics stats = sessionFactory.getStatistics();
        System.out.println("Second-level cache hit count: " + stats.getSecondLevelCacheHitCount());
        System.out.println("Second-level cache miss count: " + stats.getSecondLevelCacheMissCount());
        System.out.println("Second-level cache put count: " + stats.getSecondLevelCachePutCount());
    }
}