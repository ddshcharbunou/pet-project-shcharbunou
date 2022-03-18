package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.entity.user.User;

import java.util.UUID;

/**
 * Service for User entity.
 */
public interface UserService {
    /**
     * Save user.
     * @param user {@link User}
     * @return user {@link User}
     */
    User saveUser(User user);

    /**
     * Find user by id.
     * @param id id
     * @return user {@link User}
     */
    User findUserById(UUID id);

    /**
     * Delete user;
     * @param user {@link User}
     */
    void deleteUser(User user);

    /**
     * Find user by username.
     * @param username username
     * @return user {@link User}
     */
    User findUserByUsername(String username);

    /**
     * Find user by username and email.
     * @param username username
     * @param email email
     * @return user {@link User}
     */
    User findUserByUsernameAndEmail(String username, String email);
}
