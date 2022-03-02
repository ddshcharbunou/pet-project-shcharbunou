package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.user.User;

public interface UserDao extends BaseDao<User> {
    User findByUsername(String username);
}
