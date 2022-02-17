package hibernate;

import hibernate.entity.User;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Log4j2
public class Main {
    public static void main(String[] args) {

        log.info("Hibernate tutorial started");

//         we get a ready SessionFactory and immediately create a session
        Session session = HibernateUtil.getSessionFactory().openSession();

//         opening a transaction
        session.getTransaction().begin();

//         creating a new user in the database
//         id is not filled in because it is generated automatically
        User user = new User(); // state NEW (transient)
        user.setEmail("test1@gmail.com");
        user.setUsername("test");
        user.setPassword("111");

        session.close(); // close session; condition detached

        HibernateUtil.close();
    }
}