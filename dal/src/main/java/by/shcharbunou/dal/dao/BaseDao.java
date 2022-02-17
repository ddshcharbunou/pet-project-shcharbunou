package by.shcharbunou.dal.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T extends Serializable> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();
}
