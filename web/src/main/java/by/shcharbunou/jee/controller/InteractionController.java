package by.shcharbunou.jee.controller;

import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
public class InteractionController {
    private final UserService userService;

    @Autowired
    public InteractionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ModelAndView contributeUser(HttpServletRequest request, @ModelAttribute("user") User user) {
        ModelAndView mav = new ModelAndView();
        try {
            User candidate = userService.createUser(request, user);
            boolean usernameAdmitted = userService.checkUsernameAvailability(candidate);
            boolean emailAdmitted = userService.checkEmailAvailability(candidate);
            if (usernameAdmitted && emailAdmitted) {
                User testUser = userService.saveUser(candidate);
                boolean isSaved = testUser.equals(candidate);
                if (isSaved) {
                    mav.setViewName("sign-in");
                    mav.addObject("message", "Вы зарегестрированы!");
                }
            }
        } catch (ValidationException e) {
            mav.setViewName("sign-up");
            mav.addObject("error", e.getMessage());
            return mav;
        }
        return mav;
    }
}
