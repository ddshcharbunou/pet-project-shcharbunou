package by.shcharbunou.dal.dao.user.impl;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.dao.user.UserDao;
import by.shcharbunou.dal.entity.user.User;

import java.util.List;

public class UserDaoImpl implements BaseDao<User>, UserDao {
    private static UserDaoImpl instance = null;

    private UserDaoImpl() {}

    public static UserDaoImpl getInstance() {
        UserDaoImpl localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDaoImpl.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UserDaoImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public User findByUsername(String username) {
        return null;
    }
}
