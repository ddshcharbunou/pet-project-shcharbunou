package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.entity.user.User;

public interface UserDao {
    User findByUsername(String username);
}
