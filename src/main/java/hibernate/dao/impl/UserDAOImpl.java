package hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import hibernate.dao.interfaces.objects.UserDAO;
import hibernate.entity.Stat;
import hibernate.entity.User;

import java.util.List;

// implementation of all DAO methods
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("FROM User");
        List<User> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    // will search for all users that have a text entry in the email value
    @Override
    public List<User> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("FROM User where email like :email");
        query.setParameter("email", "%" + email + "%");
        List<User> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public User get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id); // get a single object
        session.close();
        return user;
    }

    @Override
    public void update(User obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // because this is a change operation - we must use a transaction
        session.update(obj); // if the id of the object is NOT filled, an error will be thrown (Hibernate will not understand which row you want to update)
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // because this is a change operation - we must use a transaction
        User u = new User(); // create a temporary object and fill it with id
        u.setId(id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(User obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // because this is a change operation - we must use a transaction
        session.save(obj); // if the object id is filled in, the database will overwrite this field
        session.getTransaction().commit();
        session.close();
    }

    // get a specific user by his mail (not a collection of users as in the find methods)
    @Override
    public User getByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<User> query = session.createQuery("FROM User where email = :email");
        query.setParameter("email", email);
        User user = query.uniqueResult();
        session.close();
        return user;
    }
}