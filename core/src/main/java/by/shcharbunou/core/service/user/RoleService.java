package by.shcharbunou.core.service.user;

import by.shcharbunou.dal.dao.user.RoleDao;
import by.shcharbunou.dal.dao.user.impl.RoleDaoImpl;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> findByDesignation(RoleDesignation roleDesignation) {
        return roleDao.findByDesignation(roleDesignation);
    }

    public void save(Role role) {
        roleDao.save(role);
    }

    public void update(Role role) {
        roleDao.update(role);
    }

    public void delete(Role role) {
        roleDao.delete(role);
    }

    public List<Role> findAll(Class<Role> roleClass) {
        return roleDao.findAll(roleClass);
    }

    public void deleteAll(Class<Role> roleClass) {
        roleDao.deleteAll(roleClass);
    }
}
