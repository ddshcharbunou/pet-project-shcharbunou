package by.shcharbunou.dal.dao.user.impl;

import by.shcharbunou.dal.dao.user.GroupDao;
import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.enums.group.GroupDesignation;
import by.shcharbunou.dal.entity.enums.group.GroupLevel;
import by.shcharbunou.dal.entity.user.Group;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("groupDao")
@Transactional
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {
    @Override
    public List<Group> findByDesignation(GroupDesignation designation) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("select g from Group g where g.designation=:designation", Group.class)
                .setParameter("designation", designation)
                .getResultList();
    }

    @Override
    public List<Group> findByAge(GroupAge age) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("select g from Group g where g.age=:age", Group.class)
                .setParameter("age", age)
                .getResultList();
    }

    @Override
    public List<Group> findByLevel(GroupLevel level) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("select g from Group g where g.level=:level", Group.class)
                .setParameter("level", level)
                .getResultList();
    }
}
