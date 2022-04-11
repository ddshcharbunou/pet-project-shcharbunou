package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.repository.user.custom.CustomGroupRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for Group entity.
 */
public interface GroupRepository extends JpaRepository<Group, UUID>, CustomGroupRepository {
    Page<Group> findByAge(GroupAge age, Pageable pageable);
}
