package by.shcharbunou.jee.controller;

import by.shcharbunou.core.dto.UserDto;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class InteractionController {
    private final UserService userService;

    @Autowired
    public InteractionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ModelAndView contributeUser(UserDto userDto) {
        ModelAndView mav = new ModelAndView();
        try {
            User candidate = userService.createUser(userDto);
            User testUser = userService.saveUser(candidate);
            boolean isSaved = testUser.equals(candidate);
            if (isSaved) {
                mav.setViewName("sign-in");
                mav.addObject("message", "Вы зарегестрированы!");
            }
        } catch (ValidationException e) {
            mav.setViewName("sign-up");
            mav.addObject("error", e.getMessage());
            return mav;
        }
        return mav;
    }
}
