package by.shcharbunou.core.service.user;

import by.shcharbunou.core.dto.UserDto;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
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

    /**
     * Find user by email.
     * @param email email
     * @return user {@link User}
     * @throws UserNotFoundException user not found
     */
    User findUserByEmail(String email) throws UserNotFoundException;

    /**
     * Create user with help of user DTO.
     * @param userDto userDto
     * @return user {@link User}
     * @throws ValidationException validation exception
     */
    User createUser(UserDto userDto) throws ValidationException;
}
