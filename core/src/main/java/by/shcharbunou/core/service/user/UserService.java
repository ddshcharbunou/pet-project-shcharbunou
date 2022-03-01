package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.dao.user.impl.UserDaoImpl;
import by.shcharbunou.dal.entity.user.User;

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
}
