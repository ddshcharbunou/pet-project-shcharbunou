package by.shcharbunou.dal.dao.group.impl;

import by.shcharbunou.dal.dao.group.GroupDao;
import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.group.Group;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {
    private static GroupDaoImpl instance = null;

    private GroupDaoImpl() {}

    public static GroupDaoImpl getInstance() {
        GroupDaoImpl localInstance = instance;
        if (Objects.isNull(localInstance)) {
            synchronized (GroupDaoImpl.class) {
                localInstance = instance;
                if (Objects.isNull(localInstance)) {
                    instance = localInstance = new GroupDaoImpl();
                }
            }
        }
        return localInstance;
    }

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
