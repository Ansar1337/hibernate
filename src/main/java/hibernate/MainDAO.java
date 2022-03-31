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
        
        // Testing other userDAO methods
        userDAO.delete(20036L);
        log.info(userDAO.findAll("@email.com"));
        log.info(userDAO.findAll());
        
        /*  SCENARIO (one of the possible options - you can come up with your own):
        1. create a user (triggers are created immediately test data)
        2. activate the user (the field is activated)
        3. create several new tasks (besides tests) with added category and priority
        complete the table
        4. delete brushes
        5. read statistics for any category of users and calculate the overall user statistics
      */

        // create a user (triggers are created immediately test data)
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testuser");
        user.setEmail("testuser@gmail.com");

        UserDAOImpl userDAO2 = new UserDAOImpl();
        userDAO2.add(user);

        // activate the user (the field is activated)
        activityDAO = new ActivityDAOImpl();
        Activity activity = activityDAO.getByUser(user);
        activity.setActivated(true);
        activityDAO.update(activity);

        // create several new tasks (besides tests) with added category and priority
        Priority priority = new Priority();
        priority.setColor("#fff");
        priority.setTitle("New Priority");
        priority.setUser(user);
        priorityDAO.add(priority);

        Category category = new Category();
        category.setTitle("New category");
        category.setUser(user);
        categoryDAO.add(category);

        Task task = new Task();
        task.setUser(user);
        task.setTitle("New Task2");
        task.setCategory(category);
        task.setPriority(priority);
        task.setTaskDate(new Date());
        task.setCompleted(false);
        taskDAO.add(task);

        task.setCompleted(true);
        taskDAO.update(task);

        // deletion
        taskDAO.delete(task.getId());

        // read statistics for any category of users and calculate the overall user statistics
        Stat stat = statDAO.getByUser(user);

        log.info(stat.getCompletedTotal());
        log.info(category.getCompletedCount());

        HibernateUtil.close(); // close Session Factory - clears the 2nd level cache
    }
}
