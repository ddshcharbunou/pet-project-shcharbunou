package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.entity.user.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, UUID> {
}
