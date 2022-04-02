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
}
