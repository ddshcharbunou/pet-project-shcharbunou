package by.shcharbunou.dal.dao.user;

import by.shcharbunou.dal.dao.BaseDao;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;

import java.util.List;

public interface RoleDao extends BaseDao<Role> {
    List<Role> findByDesignation(RoleDesignation roleDesignation);
}
