package hibernate.dao.interfaces.objects;

import hibernate.dao.interfaces.CommonDAO;
import hibernate.entity.Stat;
import hibernate.entity.User;

/*

We indicate which interfaces we will use (if necessary) - this means what features the object will have
You can also add any other specific object methods

In this interface, we do not inherit anything, because general statistics only get one value by email (it doesn't need other methods)

*/

public interface StatDAO {
    // obtaining statistics of a specific user (in several ways)
    Stat getByUser(String email);

    Stat getByUser(User user);
}