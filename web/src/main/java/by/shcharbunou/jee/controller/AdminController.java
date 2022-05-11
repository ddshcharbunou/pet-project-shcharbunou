package by.shcharbunou.jee.controller;

import by.shcharbunou.core.dto.user.ClaimDto;
import by.shcharbunou.core.dto.user.request.GroupRequest;
import by.shcharbunou.core.dto.user.response.GroupResponse;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.*;
import by.shcharbunou.core.service.admin.AdminService;
import by.shcharbunou.core.service.user.ClaimService;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.core.service.user.impl.ClaimServiceImpl;
import by.shcharbunou.core.service.user.impl.GroupServiceImpl;
import by.shcharbunou.core.service.user.impl.UserServiceImpl;
import by.shcharbunou.dal.entity.user.Claim;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/admin/group/control/add-user-group")
    public ModelAndView getAddUserGroupPanel() {
        return new ModelAndView("admin/group/add-user-group");
    }

    @GetMapping("/admin/group/control/del-group")
    public ModelAndView getDeleteGroupPanel() {
        return new ModelAndView("admin/group/del-group");
    }

    @GetMapping("/admin/group/control/del-user-group")
    public ModelAndView getDeleteUserGroupPanel() {
        return new ModelAndView("admin/group/del-user-group");
    }

    @GetMapping("/admin/group/control/show-groups")
    public ModelAndView showGroups() {
        return new ModelAndView("admin/group/show-groups");
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

    @PostMapping("/admin/group/control/add-group")
    public ModelAndView addGroup(GroupRequest groupRequest) throws UserNotFoundException {
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

    private void paginateGroups(ModelAndView mav, int page) {
        List<GroupResponse> groups = adminService.getGroupService().findAllGroupsPageable(page - 1, PAGE_SIZE);
        int pagesNumber = GroupServiceImpl.totalPages;
        mav.addObject("pagesNumber", pagesNumber);
        mav.addObject("groups", groups);
    }

    private void paginateUsersClaims(UUID claim, ModelAndView mav, int page) {
        List<UserResponse> users = adminService
                .getUserService()
                .findAllUsersByClaimPageable(claim, page - 1, PAGE_SIZE);
        int pagesNumber = UserServiceImpl.totalUserClaimPages;
        mav.addObject("pagesNumber", pagesNumber);
        mav.addObject("users", users);
    }
}
