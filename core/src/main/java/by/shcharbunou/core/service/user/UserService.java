package by.shcharbunou.core.service.user;

import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.dal.entity.user.User;

import javax.servlet.http.HttpServletRequest;
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
     * @throws UserNotFoundException user not found
     */
    User findUserById(UUID id) throws UserNotFoundException;

    /**
     * Delete user;
     * @param user {@link User}
     */
    void deleteUser(User user);

    /**
     * Find user by username.
     * @param username username
     * @return user {@link User}
     * @throws UserNotFoundException user not found
     */
    User findUserByUsername(String username) throws UserNotFoundException;

    /**
     * Find user by username and email.
     * @param username username
     * @param email email
     * @return user {@link User}
     * @throws UserNotFoundException user not found
     */
    User findUserByUsernameAndEmail(String username, String email) throws UserNotFoundException;

    User findUserByEmail(String email) throws UserNotFoundException;

    User createUser(HttpServletRequest request, User user) throws ValidationException;

    boolean checkUsernameAvailability(User user) throws ValidationException;

    boolean checkEmailAvailability(User user) throws ValidationException;
}
