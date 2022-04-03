package by.shcharbunou.core.service.admin;

import by.shcharbunou.core.exception.AdminNotFoundException;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
    User findAdminByUsername(String username) throws AdminNotFoundException;

    Group testCreate(HttpServletRequest request);
}
