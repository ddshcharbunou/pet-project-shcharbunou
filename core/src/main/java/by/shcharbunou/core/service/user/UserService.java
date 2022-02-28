package by.shcharbunou.core.service.user;

import by.shcharbunou.core.service.BaseService;
import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Objects;

public class UserService extends BaseService<User> implements UserDao {
    private static UserService instance = null;

    private UserService() {}

    public static UserService getInstance() {
        UserService localInstance = instance;
        if (Objects.isNull(localInstance)) {
            synchronized (UserService.class) {
                localInstance = instance;
                if (Objects.isNull(localInstance)) {
                    instance = localInstance = new UserService();
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
