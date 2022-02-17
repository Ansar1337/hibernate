package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

// java class for Hibernate initialization
public class HibernateUtil {

    //     session creation factory
    private static final SessionFactory sessionFactory = initSessionFactory();

    //     this method is called automatically because it is called from a static variable
    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            initSessionFactory();
        }
        return sessionFactory;
    }

    //   closing all connections with SessionFactory
    public static void close() {
        getSessionFactory().close();
    }
}