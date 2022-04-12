package by.shcharbunou.core.service.admin.impl;

import by.shcharbunou.core.exception.AdminNotFoundException;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.message.AdminMessage;
import by.shcharbunou.core.service.admin.AdminService;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("adminService")
@Transactional(transactionManager = "transactionManager")
public class AdminServiceImpl implements AdminService {
    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public AdminServiceImpl(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
        log.debug("AdminService initialized");
    }

    @Override
    public User findAdminByUsername(String username) throws AdminNotFoundException {
        User admin;
        try {
            admin = userService.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            throw new AdminNotFoundException(AdminMessage.ADMIN_NOT_FOUND.getMessage());
        }
        log.info("Admin founded: " + admin.getUsername());
        return admin;
    }

    @Override
    public GroupService getGroupService() {
        return groupService;
    }
}
