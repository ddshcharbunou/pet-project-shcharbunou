package by.shcharbunou.learningmanagementsystem.dao.user;

import by.shcharbunou.learningmanagementsystem.entity.user.User;

public interface UserDao {
    User findByUsername(String username);
}
