package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.service.user.RoleService;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;
import by.shcharbunou.dal.repository.user.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("roleService")
@Transactional(transactionManager = "transactionManager")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        log.debug("RoleService initialized");
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role findRoleByDesignation(RoleDesignation roleDesignation) {
        return roleRepository.findByDesignation(roleDesignation);
    }
}
