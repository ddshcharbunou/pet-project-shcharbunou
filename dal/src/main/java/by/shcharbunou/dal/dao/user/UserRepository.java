package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, UUID> {
}
