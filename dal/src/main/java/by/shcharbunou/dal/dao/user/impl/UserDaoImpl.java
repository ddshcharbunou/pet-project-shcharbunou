package by.shcharbunou.dal.dao.user.impl;

import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.entity.group.Group;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    @Override
    public User findByUsername(String username) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        User user = session.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();

        session.close();
        return user;
    }

    @Override
    public List<User> findByGroup(Group group) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<User> users = session.createQuery("select u from User u where u.group.id=:groupId", User.class)
                .setParameter("groupId", group.getId())
                .getResultList();

        session.close();
        return users;
    }
}
