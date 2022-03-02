package hibernate;

import hibernate.entity.User;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.NativeQuery;

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
        session.save(user);
        session.getTransaction().commit();
        
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
        User user2 = query4.uniqueResult();
        log.info(user2.getEmail());
        
//         native SQL
        String query5 = "select * from todolist.user_data";
        NativeQuery sqlQuery = session.createSQLQuery(query5);
        sqlQuery.addEntity(User.class);
        sqlQuery.setMaxResults(10);
        List<User> list = sqlQuery.list();
        log.info(list);
        
//         writing a more complex native sql query
        NativeQuery<Object[]> myQuery = session.createNativeQuery("select " +
                "       count(u.id), " +
                "       substring(u.email, position('@' in u.email)+1, length(u.email)) as domainemail " +
                "from todolist.user_data u  " +
                "where u.email like '%@%' " +
                "group by substring(u.email, position('@' in u.email)+1, length(u.email))");

        log.info(myQuery.getResultList());

        for (Object[] obj : myQuery.getResultList()) {
            log.info(obj[0]);
            log.info(obj[1]);
            log.info("-----");
        }
        
//          extracting information about the user using built-in methods      
        User temp = session.get(User.class, 10025L);
        User temp2 = session.find(User.class,10026L);
        User temp3 = session.load(User.class,10027L);
        log.info(temp);
        
//          extracting information about the user and storing it in the List
        Query query6 = session.createQuery("from User");
        query6.setMaxResults(5);
        List<User> users = query6.getResultList();
        for (User u : users) {
            log.info(u.getUsername());
        }
       
        session.close(); // close session; condition detached

        HibernateUtil.close();
    }
}
