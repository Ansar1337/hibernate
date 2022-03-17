package hibernate.dao.impl;

import hibernate.entity.Priority;
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import hibernate.dao.interfaces.objects.ActivityDAO;
import hibernate.entity.Activity;
import hibernate.entity.User;

import java.util.List;

// implementation of all DAO methods
public class ActivityDAOImpl implements ActivityDAO {

    @Override
    public List<Activity> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Activity> query = session.createQuery("FROM Activity");
        List<Activity> list = query.getResultList(); // получение коллекции должно быть до закрытия сессии
        session.close();
        return list;
    }

    @Override
    public List<Activity> findAll(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Activity> query = session.createQuery("FROM Activity a where a.user.email like :email");
        query.setParameter("email", "%" + email + "%");
        List<Activity> list = query.getResultList(); // получение коллекции должно быть до закрытия сессии
        session.close();
        return list;
    }

    @Override
    public Activity get(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Activity user = session.get(Activity.class, id); // получаем единичный объект
        session.close();
        return user;
    }

    // we can update any activity data (for example, update the activated field - thus the user will be activated or vice versa - deactivated)
    @Override
    public void update(Activity obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction(); // т.к. это опреация изменения - должны использовать транзакцию
        session.update(obj); // если id объекта НЕ будет заполнено - выйдет ошибка (Hibernate не поймет какую именно строку хотите обновить)
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        throw new IllegalStateException("you cant delete activity by yourself - only DB can");
    }

    @Override
    public void add(Activity obj) {
        throw new IllegalStateException("you cant add activity by yourself - only DB's trigger can");
    }

    // getting statistics of a specific user (by email)
    @Override
    public Activity getByUser(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Activity> query = session.createQuery("FROM Activity where user.email like :email");
        query.setParameter("email", "%" + email + "%");
        Activity stat = query.uniqueResult();
        session.close();
        return stat;
    }

    @Override
    public Activity getByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Activity> query = session.createQuery("FROM Activity where user.email like :email");
        query.setParameter("email", "%" + user.getEmail() + "%");
        Activity stat = query.uniqueResult();
        session.close();
        return stat;
    }
}