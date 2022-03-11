package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.dao.user.impl.UserDaoImpl;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDaoImpl userDao) {
        this.userDao = userDao;
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

    public List<User> findAll(Class<User> userClass) {
        return userDao.findAll(userClass);
    }

    public void deleteAll(Class<User> userClass) {
        userDao.deleteAll(userClass);
    }

    public List<User> findByGroup(Group group) {
        return userDao.findByGroup(group);
    }
}
