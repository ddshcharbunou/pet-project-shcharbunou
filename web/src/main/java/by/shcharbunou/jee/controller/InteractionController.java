package by.shcharbunou.jee.controller;

import by.shcharbunou.core.dto.user.request.UserRequest;
import by.shcharbunou.core.dto.user.response.GroupResponse;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.*;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.service.user.ClaimService;
import by.shcharbunou.core.service.user.GroupService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.core.service.user.impl.GroupServiceImpl;
import by.shcharbunou.dal.entity.enums.group.GroupAge;
import by.shcharbunou.dal.entity.user.Claim;
import by.shcharbunou.dal.entity.user.Group;
import by.shcharbunou.dal.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class InteractionController {
    public static final int PAGE_SIZE = 6;
    private final UserService userService;
    private final GroupService groupService;
    private final ClaimService claimService;

    @Autowired
    public InteractionController(UserService userService, GroupService groupService, ClaimService claimService) {
        this.userService = userService;
        this.groupService = groupService;
        this.claimService = claimService;
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
        assert userResponse != null;
        if (userResponse.getGroup() != null) {
            mav.addObject("group", userResponse.getGroup());
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
        paginateAgeGroups(age, mav, page);
        mav.setViewName("/office/groups/kids");
        return mav;
    }

    @GetMapping("/office/groups/teens/{page}")
    public ModelAndView getTeenGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        GroupAge age = GroupAge.TEENS;
        paginateAgeGroups(age, mav, page);
        mav.setViewName("/office/groups/teens");
        return mav;
    }

    @GetMapping("/office/groups/adults/{page}")
    public ModelAndView getAdultGroups(@PathVariable("page") int page) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("page", page);
        GroupAge age = GroupAge.ADULTS;
        paginateAgeGroups(age, mav, page);
        mav.setViewName("/office/groups/adults");
        return mav;
    }

    @GetMapping("/office/leave/{user}/{group}")
    public ModelAndView disconnectUserFromGroup(@PathVariable("user") String username,
                                                @PathVariable("group") UUID groupID,
                                                Authentication authentication) {
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
        return signInToOffice(authentication);
    }

    @GetMapping("/office/claims/create/{group}")
    public ModelAndView createUserGroupClaim(@PathVariable("group") UUID groupID,
                                             @SessionAttribute("user") UserResponse userResponse) {
        ModelAndView mav = new ModelAndView();
        User user;
        Claim claim;
        try {
            user = userService.findUserByUsername(userResponse.getUsername());
            user.setGroupClaim(groupID);
            userService.saveUser(user);
            userResponse = userService.findUserResponseByUsername(user.getUsername());
            claim = claimService.createClaim(user.getId(), groupID);
        } catch (UserNotFoundException | ClaimDuplicateException e) {
            mav.addObject("user", userResponse);
            mav.addObject("error", e.getMessage());
            mav.setViewName("/office/office");
            log.error("SOMETHING WRONG");
            return mav;
        }
        claimService.saveClaim(claim);
        mav.addObject("user", userResponse);
        mav.addObject("message", "Заявка успешно подана!");
        mav.setViewName("/office/office");
        return mav;
    }

    @GetMapping("/office/claims/delete/{group}")
    public ModelAndView deleteUserGroupClaim(@PathVariable("group") UUID groupID,
                                             @SessionAttribute("user") UserResponse userResponse) {
        ModelAndView mav = new ModelAndView();
        Claim claim = null;
        User user;
        try {
            user = userService.findUserByUsername(userResponse.getUsername());
            user.setGroupClaim(null);
            claim = claimService.findClaimByUserID(user.getId());
            userService.saveUser(user);
            userResponse = userService.findUserResponseByUsername(user.getUsername());
        } catch (UserNotFoundException e) {
            log.error("SOMETHING WRONG");
        }
        claimService.deleteClaim(claim);
        mav.addObject("user", userResponse);
        mav.addObject("message", "Заявка успешно отменена!");
        mav.setViewName("/office/office");
        return mav;
    }

    private void paginateAgeGroups(GroupAge age, ModelAndView mav, int page) {
        log.info("Group age: " + age);
        List<GroupResponse> groups = groupService.findGroupsByAgePageable(age, page - 1, PAGE_SIZE);
        if (groups.isEmpty() && page > 1) {
            page--;
            mav.addObject("page", page);
            paginateAgeGroups(age, mav, page);
        } else {
            int pagesNumber = GroupServiceImpl.totalAgePages;
            mav.addObject("pagesNumber", pagesNumber);
            mav.addObject("groups", groups);
        }
    }
}
