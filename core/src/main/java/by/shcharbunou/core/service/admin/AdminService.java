package by.shcharbunou.core.service.admin;

import by.shcharbunou.core.exception.AdminNotFoundException;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.dal.entity.user.User;

public interface AdminService {
    User findAdminByUsername(String username) throws AdminNotFoundException;

    GroupService getGroupService();
}
