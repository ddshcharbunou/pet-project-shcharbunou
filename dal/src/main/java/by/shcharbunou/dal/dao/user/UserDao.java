package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.group.Group;
import by.shcharbunou.dal.entity.user.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    User findByUsername(String username);

    List<User> findByGroup(Group group);
}
