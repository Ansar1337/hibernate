package hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import hibernate.dao.interfaces.objects.CategoryDAO;
import hibernate.entity.Category;
import hibernate.entity.User;

import java.util.List;

// implementation of all DAO methods
public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category");
        List<Category> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public List<Category> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Category> query = session.createQuery("FROM Category c where c.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Category> list = query.getResultList(); // getting the collection must be before the session is closed
        session.close();
        return list;
    }

    @Override
    public Category get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Category user = session.get(Category.class, id); // we get a single object
        session.close();
        return user;
    }

    @Override
    public void update(Category obj) {
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
        Category u = new Category();// create a temporary object and fill it with id
        u.setId(id);
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void add(Category obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // because this is a change operation - we must use a transaction
        session.save(obj); // if the object id is filled in, the database will overwrite this field
        session.getTransaction().commit();
        session.close();
    }
}