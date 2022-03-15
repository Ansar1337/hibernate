package hibernate.dao.interfaces;

import java.util.List;

/*

basic operations for all entity objects
CRUD - Create, Read, Update, Delete
general data search

 */

public interface CommonDAO<T> {

    // get absolutely all values
    List<T> findAll();

    // get all values by mail value
    List<T> findAll(String email);

    // get one value by id
    T get(long id);

    // update value
    void update(T obj);

    // delete value by id
    void delete(long id);

    // add value
    void add(T obj);
}