package by.shcharbunou.core.service.user;

import by.shcharbunou.core.dto.user.request.UserRequest;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;

import java.util.List;
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
     * Find user response by username.
     * @param username username
     * @return user response {@link UserResponse}
     * @throws UserNotFoundException user not found
     */
    UserResponse findUserResponseByUsername(String username) throws UserNotFoundException;

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
     * @param userRequest userRequest
     * @return user {@link User}
     * @throws ValidationException validation exception
     */
    User createUser(UserRequest userRequest) throws ValidationException;

    /**
     * Activate user.
     * @param code activation code
     * @return activated or not
     */
    boolean activateUser(String code);

    /**
     * Send activation code.
     * @param user user
     */
    void sendActivationCode(User user);

    /**
     * Find all users by claim pageable.
     * @param claim claim
     * @param page page
     * @param pageSize page size
     * @return list of user responses {@link UserResponse}
     */
    List<UserResponse> findAllUsersByClaimPageable(UUID claim, int page, int pageSize);

    /**
     * Find all users by group pageable.
     * @param group group
     * @param page group
     * @param pageSize page size
     * @return list of user responses {@link UserResponse}
     */
    List<UserResponse> findAllUsersByGroupPageable(Group group, int page, int pageSize);
}
