package hibernate.dao.interfaces;

import java.util.List;

// various searches that return collections
public interface FindDAO<T> {

    // get absolutely all values
    List<T> findAll();

    // get all values by mail value
    // the word find - most often means that a collection of objects will be returned
    List<T> findAll(String email);
}