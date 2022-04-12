package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;

/**
 * Service for Role entity.
 */
public interface RoleService {
    /**
     * Save role entity.
     * @param role role
     * @return role {@link Role}
     */
    Role saveRole(Role role);

    /**
     * Delete role entity.
     * @param role role
     */
    void deleteRole(Role role);

    /**
     * Find role by designation.
     * @param roleDesignation role designation
     * @return role {@link Role}
     */
    Role findRoleByDesignation(RoleDesignation roleDesignation);
}
