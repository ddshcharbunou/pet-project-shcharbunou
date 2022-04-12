package by.shcharbunou.core.service.admin;

import by.shcharbunou.core.exception.AdminNotFoundException;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.dal.entity.user.User;

public interface AdminService {
    /**
     * Find admin using username and user service.
     * @param username username
     * @return user {@link User}
     * @throws AdminNotFoundException admin not found
     */
    User findAdminByUsername(String username) throws AdminNotFoundException;

    /**
     * Return group service.
     * @return group service {@link GroupService}
     */
    GroupService getGroupService();
}
