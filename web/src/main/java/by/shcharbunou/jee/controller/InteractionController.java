package by.shcharbunou.jee.controller;

import by.shcharbunou.core.dto.user.request.UserRequest;
import by.shcharbunou.core.dto.user.response.GroupResponse;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.UserNotActivatedException;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.core.service.user.impl.GroupServiceImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@SessionAttributes({"user", "ROLE", "page"})
public class InteractionController {
    public static final int PAGE_SIZE = 6;
    private final UserService userService;
    private final GroupService groupService;

    @Autowired
    public InteractionController(UserService userService, GroupService groupService) {
        this.userService = userService;
        this.groupService = groupService;
        log.debug("InteractionController initialized");
    }

    @PostMapping("sign-in")
    public ModelAndView getSignInFail() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("error", "Ошибка входа: Проверьте данные");
        mav.setViewName("sign-in");
        return mav;
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
            mav.addObject("error", "Ошибка: Аккаунт уже активирован!");
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

    @GetMapping("/office/groups/kids/{page}")
    public ModelAndView getKidGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        GroupAge age = GroupAge.KIDS;
        paginateGroups(age, mav, page);
        mav.setViewName("/office/groups/kids");
        return mav;
    }

    @GetMapping("/office/groups/teens/{page}")
    public ModelAndView getTeenGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        GroupAge age = GroupAge.TEENS;
        paginateGroups(age, mav, page);
        mav.setViewName("/office/groups/teens");
        return mav;
    }

    @GetMapping("/office/groups/adults/{page}")
    public ModelAndView getAdultGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        GroupAge age = GroupAge.ADULTS;
        paginateGroups(age, mav, page);
        mav.setViewName("/office/groups/adults");
        return mav;
    }

    private void paginateGroups(GroupAge age, ModelAndView mav, int page) {
        log.info("Group age: " + age);
        List<GroupResponse> groups = groupService.findGroupsByAgePageable(age, page - 1, PAGE_SIZE);
        int pagesNumber = GroupServiceImpl.totalPages;
        mav.addObject("pagesNumber", pagesNumber);
        mav.addObject("groups", groups);
    }
}
