package by.shcharbunou.dal.dao.user.impl;

import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User findByUsername(String username) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<User> findByGroup(Group group) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("select u from User u where u.group.id=:groupId", User.class)
                .setParameter("groupId", group.getId())
                .getResultList();
    }
}
