package by.shcharbunou.jee.controller;

import by.shcharbunou.core.dto.user.request.UserRequest;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.UserNotActivatedException;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Log4j2
@Controller
@SessionAttributes({"user", "ROLE"})
public class InteractionController {
    private final UserService userService;

    @Autowired
    public InteractionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ModelAndView contributeUser(UserRequest userRequest) {
        ModelAndView mav = new ModelAndView();
        try {
            User candidate = userService.createUser(userRequest);
            User testUser = userService.saveUser(candidate);
            boolean isSaved = testUser.equals(candidate);
            if (isSaved) {
                userService.sendActivationCode(candidate);
                mav.setViewName("sign-in");
                mav.addObject("message", "Для активации аккаунта перейдите" +
                        " по ссылке в вашем почтовом ящике");
            }
        } catch (ValidationException e) {
            mav.setViewName("sign-up");
            mav.addObject("error", e.getMessage());
            return mav;
        }
        return mav;
    }

    @GetMapping("/activate/{code}")
    public ModelAndView activateUser(@PathVariable String code) {
        ModelAndView mav = new ModelAndView();
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            mav.addObject("message", "Аккаунт подтверждён!");
        } else {
            mav.addObject("message", "Ошибка: Аккаунт уже активирован!");
        }
        mav.setViewName("sign-in");
        return mav;
    }

    @GetMapping("/office")
    public ModelAndView signInToOffice(Authentication authentication) {
        ModelAndView mav = new ModelAndView();
        UserResponse userResponse = null;
        try {
            userResponse = userService.findUserResponseByUsername(authentication.getName());
            if (Objects.nonNull(userResponse.getActivationCode())) {
                throw new UserNotActivatedException(UserMessage.USER_NOT_ACTIVATED.getMessage());
            }
        } catch (UserNotFoundException | UserNotActivatedException e) {
            mav.addObject("error", e.getMessage());
        }
        mav.addObject("user", userResponse);
        mav.addObject("ROLE", authentication.getAuthorities().stream().findFirst().orElseThrow());
        mav.setViewName("office/office");
        return mav;
    }
}
