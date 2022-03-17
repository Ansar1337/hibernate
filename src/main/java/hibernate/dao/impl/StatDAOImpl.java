package hibernate.dao.impl;


import hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import hibernate.dao.interfaces.objects.StatDAO;
import hibernate.entity.Stat;
import hibernate.entity.Task;
import hibernate.entity.User;

import java.util.List;


// implementation of all DAO methods
public class StatDAOImpl implements StatDAO {

    // getting statistics of a specific user (via email)
    @Override
    public Stat getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Stat> query = session.createQuery("FROM Stat where user.email like :email");
        query.setParameter("email", "%" + email + "%");
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }

    @Override
    public Stat getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Stat> query = session.createQuery("FROM Stat where user.email like :email");
        query.setParameter("email", "%" + user.getEmail() + "%");
        Stat stat = query.uniqueResult();
        session.close();
        return stat;
    }
}