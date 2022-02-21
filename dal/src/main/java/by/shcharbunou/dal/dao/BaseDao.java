package by.shcharbunou.dal.dao;

import by.shcharbunou.dal.entity.BaseEntity;

import java.util.List;

public interface BaseDao<T extends BaseEntity> {
    void save(T entity);

    void update(T entity);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();
}
