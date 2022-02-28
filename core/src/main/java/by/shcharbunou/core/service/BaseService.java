package by.shcharbunou.core.service;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BaseService<T extends BaseEntity> implements BaseDao<T> {
    @Override
    public void save(T entity) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        session.persist(entity);
        session.flush();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(T entity) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        session.merge(entity);
        session.flush();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(T entity) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        session.remove(entity);
        session.flush();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<T> findAll(Class<T> clazz) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<T> entityList = session.createQuery("select * from " + clazz.getSimpleName(), clazz).getResultList();

        session.getTransaction().commit();
        session.close();
        return entityList;
    }

    @Override
    public void deleteAll(Class<T> clazz) {
        List<T> entityList = findAll(clazz);

        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        for (T entity : entityList) {
            session.remove(entity);
        }
        session.flush();

        session.getTransaction().commit();
        session.close();
    }
}
