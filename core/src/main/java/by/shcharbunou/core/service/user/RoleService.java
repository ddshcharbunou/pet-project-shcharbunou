package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;

public interface RoleService {
    Role saveRole(Role role);

    void deleteRole(Role role);

    Role findRoleByDesignation(RoleDesignation roleDesignation);
}
