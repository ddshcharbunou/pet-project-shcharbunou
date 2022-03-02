package by.shcharbunou.dal.dao.role.impl;

import by.shcharbunou.dal.dao.impl.BaseDaoImpl;
import by.shcharbunou.dal.dao.role.RoleDao;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.role.Role;
import by.shcharbunou.dal.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Objects;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
    private static RoleDaoImpl instance = null;

    private RoleDaoImpl() {}

    public static RoleDaoImpl getInstance() {
        RoleDaoImpl localInstance = instance;
        if (Objects.isNull(localInstance)) {
            synchronized (RoleDaoImpl.class) {
                localInstance = instance;
                if (Objects.isNull(localInstance)) {
                    instance = localInstance = new RoleDaoImpl();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<Role> findByDesignation(RoleDesignation roleDesignation) {
        Session session = HibernateUtil.openSession();
        session.getTransaction().begin();

        List<Role> roles = session.createQuery("select r from Role r where r.roleDesignation=:roleDesignation",
                Role.class)
                .setParameter("roleDesignation", roleDesignation)
                .getResultList();

        session.close();
        return roles;
    }
}
