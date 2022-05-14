package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for User entity.
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * Find user by username.
     * @param username username
     * @return user {@link User}
     */
    User findByUsername(String username);

    /**
     * Find user by username and email.
     * @param username username
     * @param email email
     * @return user {@link User}
     */
    User findByUsernameAndEmail(String username, String email);

    /**
     * Find user by email.
     * @param email email
     * @return user {@link User}
     */
    User findByEmail(String email);

    /**
     * Find user by activation code.
     * @param code activation code
     * @return user {@link User}
     */
    User findByActivationCode(String code);

    /**
     * Find all users by claims.
     * @param claim claim
     * @return page of users {@link User}
     */
    Page<User> findByGroupClaim(UUID claim, Pageable pageable);

    /**
     * Find all users by groups.
     * @param group group
     * @param pageable pageable
     * @return page of users {@link User}
     */
    Page<User> findByGroup(Group group, Pageable pageable);
}
