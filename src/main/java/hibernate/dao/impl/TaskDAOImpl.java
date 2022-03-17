package hibernate.dao.impl;

import hibernate.dao.interfaces.objects.TaskDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import hibernate.entity.Task;

import java.util.List;

// implementation of all DAO methods

public class TaskDAOImpl implements TaskDAO {

    @Override
    public List<Task> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task");
        List<Task> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public List<Task> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task t where t.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Task> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public Task get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Task user = session.get(Task.class, id); // get a single object
        session.close();
        return user;
    }

    @Override
    public void update(Task obj) {
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
        Task u = new Task();// create a temporary object and fill it with id
        u.setId(id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Task obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // because this is a change operation - we must use a transaction
        session.save(obj); // if the object id is filled in, the database will overwrite this field
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Task> find(boolean completed, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Task> query = session.createQuery("FROM Task t where t.user.email like :email and t.completed = :completed");
        query.setParameter("email", "%" + email + "%");
        query.setParameter("completed", completed);
        List<Task> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }
}