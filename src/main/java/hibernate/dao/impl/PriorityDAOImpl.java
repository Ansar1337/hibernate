package hibernate.dao.impl;

import hibernate.dao.interfaces.objects.PriorityDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import hibernate.dao.interfaces.objects.PriorityDAO;
import hibernate.entity.Priority;

import java.util.List;

// implementation of all DAO methods
public class PriorityDAOImpl implements PriorityDAO {

    @Override
    public List<Priority> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Priority> query = session.createQuery("FROM Priority");
        List<Priority> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public List<Priority> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Priority> query = session.createQuery("FROM Priority p where p.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Priority> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public Priority get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Priority user = session.get(Priority.class, id); // get a single object
        session.close();
        return user;
    }

    @Override
    public void update(Priority obj) {
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
        Priority u = new Priority();// create a temporary object and fill it with id
        u.setId(id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Priority obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); //  because this is a change operation - we must use a transaction
        session.save(obj); // if the object id is filled in, the database will overwrite this field
        session.getTransaction().commit();
        session.close();
    }
}