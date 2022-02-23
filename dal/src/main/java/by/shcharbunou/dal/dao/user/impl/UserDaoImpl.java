package by.shcharbunou.dal.dao.user.impl;

import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Objects;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        UserDaoImpl localInstance = instance;
        if (Objects.isNull(localInstance)) {
            synchronized (UserDaoImpl.class) {
                localInstance = instance;
                if (Objects.isNull(localInstance)) {
                    instance = localInstance = new UserDaoImpl();
                }
            }
        }
        return localInstance;
    }

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
}
