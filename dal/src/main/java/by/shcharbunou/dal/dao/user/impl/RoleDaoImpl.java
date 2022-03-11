package by.shcharbunou.dal.dao.user.impl;

import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.dao.user.RoleDao;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("roleDao")
@Transactional
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    @Override
    public List<Role> findByDesignation(RoleDesignation roleDesignation) {
        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("select r from Role r where r.roleDesignation=:roleDesignation",
                Role.class)
                .setParameter("roleDesignation", roleDesignation)
                .getResultList();
    }
}
