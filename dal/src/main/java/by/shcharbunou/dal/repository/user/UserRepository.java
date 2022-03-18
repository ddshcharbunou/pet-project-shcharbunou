package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.user.User;
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
}
