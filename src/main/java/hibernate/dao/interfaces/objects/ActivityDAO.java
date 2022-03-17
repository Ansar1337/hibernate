package hibernate.dao.interfaces.objects;

import hibernate.dao.interfaces.CommonDAO;
import hibernate.entity.Activity;
import hibernate.entity.Stat;
import hibernate.entity.User;

/*

We indicate which interfaces we will use - this means what features the object will have
You can also add any other specific object methods

*/

public interface ActivityDAO extends CommonDAO<Activity> {

    // getting the activity of a specific user (several ways)
    Activity getByUser(String email);
    Activity getByUser(User user);

}