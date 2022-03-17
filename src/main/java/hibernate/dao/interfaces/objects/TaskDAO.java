package hibernate.dao.interfaces.objects;

import hibernate.dao.interfaces.CommonDAO;
import hibernate.dao.interfaces.FindDAO;
import hibernate.entity.Task;

import java.util.List;

/*

We indicate which interfaces we will use - this means what features the object will have
You can also add any other specific object methods

*/

public interface TaskDAO extends CommonDAO<Task>, FindDAO<Task> {

    // find all completed or unfinished tasks for any user
    List<Task> find(boolean completed, String email);

}