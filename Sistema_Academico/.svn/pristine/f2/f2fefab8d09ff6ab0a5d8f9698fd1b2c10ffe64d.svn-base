package net.uch.commonService;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ServiceSession {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure("WEB-INF/hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            //"WEB-INF/applicationContext-hibernate.xml"
            // Make sure you log the exception, as it might be swallowed
            // Log the exception.
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
