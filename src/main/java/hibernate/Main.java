package hibernate;

import hibernate.entity.User;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

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
        
//         basic SQL SELECT statement using HQL         
        Query query = session.createQuery("FROM User u where u.email like :text");
        query.setParameter("text", "%a%");
        log.info(query);
        
//         getting first 10 values from the database      
        Query query2 = session.createQuery("FROM User");
        query2.setFirstResult(1);
        query2.setMaxResults(10);
        List<User> users = query2.getResultList();
        log.info(users);
 //        aggregate functions
        Query<Long> query3 = session.createQuery("SELECT " + "COUNT(u.id) FROM User u " + "WHERE email like :text");
        query3.setParameter("text", "%email%");
        Long count = query3.uniqueResult();
        log.info(count);
        
//         creating User object
        Query<User> query4 = session.createQuery("SELECT new User(u.id,u.email) FROM User u WHERE id = :id");
        query4.setParameter("id", 20030L);
        User user = query4.uniqueResult();
        log.info(user.getEmail());

        session.close(); // close session; condition detached

        HibernateUtil.close();
    }
}
