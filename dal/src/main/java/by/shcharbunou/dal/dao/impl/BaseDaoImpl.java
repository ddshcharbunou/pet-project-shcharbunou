package by.shcharbunou.dal.dao.impl;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.BaseEntity;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Transactional
public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void save(T entity) {
        Session session = sessionFactory.openSession();

        session.persist(entity);
        session.flush();

        session.close();
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.openSession();

        session.merge(entity);
        session.flush();

        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.openSession();

        session.remove(entity);
        session.flush();

        session.close();
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        Session session = sessionFactory.openSession();

        List<T> entityList = session.createQuery("select * from " + clazz.getSimpleName(), clazz).getResultList();

        session.close();
        return entityList;
    }

    @Override
    public void deleteAll(Class<T> clazz) {
        List<T> entityList = findAll(clazz);

        Session session = sessionFactory.openSession();

        for (T entity : entityList) {
            session.remove(entity);
        }
        session.flush();

        session.close();
    }
}
