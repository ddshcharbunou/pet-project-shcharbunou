package by.shcharbunou.jee.controller;

import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.dto.user.response.GroupResponse;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.*;
import by.shcharbunou.core.service.admin.AdminService;
import by.shcharbunou.core.service.user.ClaimService;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.core.service.user.impl.GroupServiceImpl;
import by.shcharbunou.core.service.user.impl.UserServiceImpl;
import by.shcharbunou.dal.entity.user.Claim;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.entity.user.office.homework.Homework;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Controller
@SessionAttributes({"user", "ROLE", "page"})
public class AdminController {
    private static final int PAGE_SIZE = 6;
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
        log.debug("AdminController initialized");
    }

    @GetMapping("/admin")
    public ModelAndView getAdminPanel(Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        User admin = null;
        try {
            admin = adminService.findAdminByUsername(authentication.getName());
        } catch (AdminNotFoundException e) {
            mav.addObject("error", e.getMessage());
        }
        mav.addObject("user", admin);
        mav.addObject("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        mav.setViewName("admin/admin");
        return mav;
    }

    @GetMapping("/admin/group/control")
    public ModelAndView getGroupAdminPanel() {
        return new ModelAndView("admin/group/group-adm");
    }

    @GetMapping("/admin/group/control/add-group")
    public ModelAndView getAddGroupPanel() {
        return new ModelAndView("admin/group/add-group");
    }

    @GetMapping("/admin/blog/control")
    public ModelAndView getBlogAdminPanel() {
        return new ModelAndView("admin/blog/blog-adm");
    }

    @GetMapping("/admin/homework/control")
    public ModelAndView getHomeworkAdminPanel() {
        return new ModelAndView("admin/homework/homework-adm");
    }

    @GetMapping("/admin/other/control")
    public ModelAndView getOtherAdminPanel() {
        return new ModelAndView("admin/other/other-adm");
    }

    @GetMapping("/admin/photo/control")
    public ModelAndView getPhotoAdminPanel() {
        return new ModelAndView("admin/photo/photo-adm");
    }

    @GetMapping("/admin/user/control")
    public ModelAndView getUserAdminPanel() {
        return new ModelAndView("admin/user/user-adm");
    }

    @GetMapping("/admin/group/control/add-information-group")
    public ModelAndView getAddInformationGroupPanel() {
        return new ModelAndView("admin/group/add-information-group");
    }

    @PostMapping("/admin/group/control/add-group")
    public ModelAndView addGroup(GroupRequest groupRequest) {
        ModelAndView mav = new ModelAndView();
        Group group;
        try {
            group = adminService.getGroupService().createGroup(groupRequest);
        } catch (TimeFormatException | GroupDuplicateException | UserNotFoundException
                | AdminNotFoundException e) {
            mav.addObject("error", e.getMessage());
            mav.setViewName("admin/group/add-group");
            return mav;
        }
        Group savedGroup = adminService.getGroupService().saveGroup(group);
        if (Objects.isNull(savedGroup)) {
            mav.setViewName("admin/group/add-group");
            mav.addObject("error", "Произошёл сбой: Группа не добавлена!");
        } else {
            mav.setViewName("admin/group/group-adm");
            mav.addObject("message", "Группа успешно добавлена!");
        }
        return mav;
    }

    @GetMapping("/admin/group/control/claims/{page}")
    public ModelAndView getClaimsManagement(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        paginateGroups(mav, page);
        mav.setViewName("/admin/group/claims");
        return mav;
    }

    @GetMapping("/admin/group/control/del-group/{page}")
    public ModelAndView getDeleteGroupPanel(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        paginateGroups(mav, page);
        mav.setViewName("/admin/group/del-group");
        return mav;
    }

    @GetMapping("/admin/group/control/del-user-group/{page}")
    public ModelAndView getDeleteUserGroupPanel(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        paginateGroups(mav, page);
        mav.setViewName("/admin/group/del-user-group");
        return mav;
    }

    @GetMapping("/admin/group/control/delete-user/users/{group}/{page}")
    public ModelAndView getGroupUsers(@PathVariable("group") UUID groupID,
                                      @PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        Group group = null;
        try {
            group = adminService.getGroupService().findGroupById(groupID);
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
        mav.addObject("page", page);
        mav.addObject("group", groupID);
        paginateGroupUsers(group, mav, page);
        mav.setViewName("/admin/group/group-users");
        return mav;
    }

    @GetMapping("/admin/group/control/delete/{group}/{page}")
    public ModelAndView deleteGroup(@PathVariable("group") UUID groupID,
                                    @PathVariable("page") int page) {
        GroupService groupService = adminService.getGroupService();
        UserService userService = adminService.getUserService();
        ClaimService claimService = adminService.getClaimService();
        List<Claim> testClaims = claimService.findClaimByGroupID(groupID);
        if (!testClaims.isEmpty()) {
            for (Claim claim : testClaims) {
                try {
                    User user = userService.findUserById(claim.getUserID());
                    user.setGroupClaim(null);
                    userService.saveUser(user);
                } catch (UserNotFoundException e) {
                    e.printStackTrace();
                }
                claimService.deleteClaim(claim);
            }
        }
        Group toDeleteGroup = null;
        try {
            toDeleteGroup = groupService.findGroupById(groupID);
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
        assert toDeleteGroup != null;
        if (!toDeleteGroup.getUsers().isEmpty()) {
            for (User user : toDeleteGroup.getUsers()) {
                user.setGroup(null);
                userService.saveUser(user);
            }
        }
        groupService.deleteGroup(toDeleteGroup);
        return getDeleteGroupPanel(page);
    }

    @GetMapping("/admin/group/control/claims/users/{group}/{page}")
    public ModelAndView getUsersClaimsManagement(@PathVariable("group") UUID groupID,
                                                 @PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        mav.addObject("group", groupID);
        paginateUsersClaims(groupID, mav, page);
        mav.setViewName("/admin/group/claims-users");
        return mav;
    }

    @GetMapping("/admin/group/control/show-groups/{page}")
    public ModelAndView showGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        paginateGroups(mav, page);
        mav.setViewName("/admin/group/show-groups");
        return mav;
    }

    @GetMapping("/admin/group/control/claims/users/delete/{user}/{group}/{page}")
    public ModelAndView deleteUserClaim(@PathVariable("user") String username,
                                        @PathVariable("group") UUID groupID,
                                        @PathVariable("page") int page) {
        UserService userService = adminService.getUserService();
        ClaimService claimService = adminService.getClaimService();
        User user = null;
        try {
            user = userService.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        assert user != null;
        Claim claim = claimService.findClaimByUserID(user.getId());
        user.setGroupClaim(null);
        userService.saveUser(user);
        claimService.deleteClaim(claim);
        return getUsersClaimsManagement(groupID, page);
    }

    @GetMapping("/admin/group/control/group-users/delete/{user}/{group}/{page}")
    public ModelAndView deleteUserFromGroup(@PathVariable("user") String username,
                                            @PathVariable("group") UUID groupID,
                                            @PathVariable("page") int page) {
        GroupService groupService = adminService.getGroupService();
        UserService userService = adminService.getUserService();
        User user = null;
        Group group = null;
        try {
            user = userService.findUserByUsername(username);
            group = groupService.findGroupById(groupID);
        } catch (UserNotFoundException | GroupNotFoundException e) {
            e.printStackTrace();
        }
        assert group != null;
        group.disconnectUser(user);
        userService.saveUser(user);
        groupService.saveGroup(group);
        return getGroupUsers(groupID, page);
    }

    @GetMapping("/admin/group/control/claims/users/accept/{user}/{group}/{page}")
    public ModelAndView acceptUserClaim(@PathVariable("user") String username,
                                        @PathVariable("group") UUID groupID,
                                        @PathVariable("page") int page) {
        UserService userService = adminService.getUserService();
        ClaimService claimService = adminService.getClaimService();
        GroupService groupService = adminService.getGroupService();
        User user = null;
        try {
            user = userService.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        assert user != null;
        Claim claim = claimService.findClaimByUserID(user.getId());
        Group group = null;
        try {
            group = groupService.findGroupById(claim.getGroupID());
        } catch (GroupNotFoundException e) {
            e.printStackTrace();
        }
        user.setGroupClaim(null);
        assert group != null;
        group.connectUser(user);
        userService.saveUser(user);
        claimService.deleteClaim(claim);
        return getUsersClaimsManagement(groupID, page);
    }

    @GetMapping("/admin/homework/control/add-homework/{page}")
    public ModelAndView showHomeworkGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        paginateGroups(mav, page);
        mav.setViewName("/admin/homework/homework-groups");
        return mav;
    }

    @GetMapping("/admin/homework/control/add-homework/{group}/{page}")
    public ModelAndView showAddHomeworkGroupPanel(@PathVariable("group") UUID groupID,
                                                  @PathVariable("page") int page) {
        return new ModelAndView("/admin/homework/add-homework");
    }

    @PostMapping("/admin/homework/control/{group}/{page}/themes")
    public ModelAndView addThemesToHomework(@PathVariable("group") UUID groupID,
                                            @PathVariable("page") int page,
                                            ServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Enumeration<String> arg = request.getAttributeNames();
        return null;
    }

    private void paginateGroups(ModelAndView mav, int page) {
        List<GroupResponse> groups = adminService.getGroupService().findAllGroupsPageable(page - 1, PAGE_SIZE);
        if (groups.isEmpty() && page > 1) {
            page--;
            mav.addObject("page", page);
            paginateGroups(mav, page);
        } else {
            int pagesNumber = GroupServiceImpl.totalPages;
            mav.addObject("pagesNumber", pagesNumber);
            mav.addObject("groups", groups);
        }
    }

    private void paginateUsersClaims(UUID claim, ModelAndView mav, int page) {
        List<UserResponse> users = adminService
                .getUserService()
                .findAllUsersByClaimPageable(claim, page - 1, PAGE_SIZE);
        if (users.isEmpty() && page > 1) {
            page--;
            mav.addObject("page", page);
            paginateUsersClaims(claim, mav, page);
        } else {
            int pagesNumber = UserServiceImpl.totalUserClaimPages;
            mav.addObject("pagesNumber", pagesNumber);
            mav.addObject("users", users);
        }
    }

    private void paginateGroupUsers(Group group, ModelAndView mav, int page) {
        List<UserResponse> users = adminService
                .getUserService()
                .findAllUsersByGroupPageable(group, page - 1, PAGE_SIZE);
        if (users.isEmpty() && page > 1) {
            page--;
            mav.addObject("page", page);
            paginateGroupUsers(group, mav, page);
        } else {
            int pagesNumber = UserServiceImpl.totalGroupUsersPages;
            mav.addObject("pagesNumber", pagesNumber);
            mav.addObject("users", users);
        }
    }
}
