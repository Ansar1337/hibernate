package hibernate;

import hibernate.dao.impl.*;
import hibernate.entity.*;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public class MainDAO {

    public static void main(String[] args) {

        UserDAOImpl userDAO = new UserDAOImpl();
        ActivityDAOImpl activityDAO = new ActivityDAOImpl();
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
        PriorityDAOImpl priorityDAO = new PriorityDAOImpl();
        TaskDAOImpl taskDAO = new TaskDAOImpl();
        StatDAOImpl statDAO = new StatDAOImpl();

        // Creating a new user in the database using DAO
        User u = new User();
        u.setPassword("pass5665");
        u.setUsername("Michael");
        u.setEmail("michaelbusiness@username.com");
        userDAO.add(u);

        HibernateUtil.close(); // close Session Factory - clears the 2nd level cache
    }
}