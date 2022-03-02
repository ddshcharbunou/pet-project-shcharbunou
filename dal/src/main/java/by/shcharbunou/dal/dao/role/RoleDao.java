package by.shcharbunou.dal.dao.role;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.role.Role;

import java.util.List;

public interface RoleDao extends BaseDao<Role> {
    List<Role> findByDesignation(RoleDesignation roleDesignation);
}
