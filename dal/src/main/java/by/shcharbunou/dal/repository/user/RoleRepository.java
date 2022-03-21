package by.shcharbunou.dal.repository.user;

import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository for Role entity.
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, UUID> {
    /**
     * Find role by designation.
     * @param roleDesignation role designation
     * @return role {@link Role}
     */
    Role findByDesignation(RoleDesignation roleDesignation);
}
