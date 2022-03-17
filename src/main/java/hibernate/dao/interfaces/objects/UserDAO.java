package hibernate.dao.interfaces.objects;

import hibernate.dao.interfaces.CommonDAO;
import hibernate.dao.interfaces.FindDAO;
import hibernate.entity.User;

/*

We indicate which interfaces we will use - this means what features the object will have
You can also add any other specific object methods

*/

public interface UserDAO extends CommonDAO<User>, FindDAO<User> {

    // getting only one user by email
    User getByEmail(String email);
}
