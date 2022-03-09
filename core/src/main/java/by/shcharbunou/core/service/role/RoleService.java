package by.shcharbunou.core.service.role;

import by.shcharbunou.dal.dao.role.RoleDao;
import by.shcharbunou.dal.dao.role.impl.RoleDaoImpl;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.role.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleDao roleDao = RoleDaoImpl.getInstance();

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
