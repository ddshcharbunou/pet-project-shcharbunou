package by.shcharbunou.dal.dao.impl;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BaseDaoImpl<T extends BaseEntity> implements BaseDao<T> {
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
    public List<T> findAll() {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<T> entityList = null;

        session.getTransaction().commit();
        session.close();
        return null;
    }

    @Override
    public void deleteAll() {
        List<T> entityList = findAll();

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
