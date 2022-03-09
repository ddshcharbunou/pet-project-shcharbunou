package by.shcharbunou.dal.dao.group.impl;

import by.shcharbunou.dal.dao.group.GroupDao;
import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.group.Group;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {
    @Override
    public List<Group> findByDesignation(GroupDesignation designation) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<Group> groups = session.createQuery("select g from Group g where g.designation=:designation", Group.class)
                .setParameter("designation", designation)
                .getResultList();

        session.close();
        return groups;
    }

    @Override
    public List<Group> findByAge(GroupAge age) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<Group> groups = session.createQuery("select g from Group g where g.age=:age", Group.class)
                .setParameter("age", age)
                .getResultList();

        session.close();
        return groups;
    }

    @Override
    public List<Group> findByLevel(GroupLevel level) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<Group> groups = session.createQuery("select g from Group g where g.level=:level", Group.class)
                .setParameter("level", level)
                .getResultList();

        session.close();
        return groups;
    }
}
