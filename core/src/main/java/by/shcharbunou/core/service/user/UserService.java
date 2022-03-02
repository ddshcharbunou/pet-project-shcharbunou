package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.dao.user.impl.UserDaoImpl;
import by.shcharbunou.dal.entity.user.User;

import java.util.List;
import java.util.Objects;

public class UserService {
    private static UserService instance = null;
    private final UserDao userDao;

    private UserService() {
        userDao = UserDaoImpl.getInstance();
    }

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

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(User user) {
        userDao.delete(user);
    }

    public List<User> findAll(Class<User> clazz) {
        return userDao.findAll(clazz);
    }

    public void deleteAll(Class<User> clazz) {
        userDao.deleteAll(clazz);
    }
}
