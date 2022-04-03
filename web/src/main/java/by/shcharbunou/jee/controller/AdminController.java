package by.shcharbunou.jee.controller;

import by.shcharbunou.core.exception.AdminNotFoundException;
import by.shcharbunou.core.service.admin.AdminService;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@SessionAttributes({"user", "ROLE"})
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
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

    @GetMapping("/admin/group/control/claims")
    public ModelAndView getClaimsPanel() {
        return new ModelAndView("admin/group/claims");
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
}
