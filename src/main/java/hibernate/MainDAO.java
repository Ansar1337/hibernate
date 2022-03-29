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
        
        // Updating the user information
        User u2 = new User();
        u2.setId(20036L);
        u2.setUsername("Jim");
        u2.setEmail("jim@yahoo.com");
        u2.setPassword("nopass1");
        userDAO.update(u2);

        // Checking its role(s)
        u2 = userDAO.get(20036L);
        log.info(u2.getRoles());

        HibernateUtil.close(); // close Session Factory - clears the 2nd level cache
    }
}
