package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.user.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Group entity.
 */
@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
