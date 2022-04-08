package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.repository.user.custom.CustomGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Group entity.
 */
public interface GroupRepository extends JpaRepository<Group, UUID>, CustomGroupRepository {

}
