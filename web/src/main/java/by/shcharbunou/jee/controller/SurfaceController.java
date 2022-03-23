package by.shcharbunou.jee.controller;

import by.shcharbunou.dal.entity.user.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
public class SurfaceController {
    @GetMapping("/")
    public ModelAndView getIndexPage() {
        return new ModelAndView("index");
    }

    @GetMapping("main")
    public ModelAndView getMainPage() {
        return new ModelAndView("index");
    }

    @GetMapping("/courses")
    public ModelAndView getCoursesPage() {
        return new ModelAndView("courses");
    }

    @GetMapping("/about")
    public ModelAndView getAboutPage() {
        return new ModelAndView("about");
    }

    @GetMapping("/blog")
    public ModelAndView getBlogPage() {
        return new ModelAndView("blog");
    }

    @GetMapping("/contacts")
    public ModelAndView getContactsPage() {
        return new ModelAndView("contacts");
    }

    @GetMapping("/sign-in")
    public ModelAndView getSignInPage() {
        return new ModelAndView("sign-in");
    }

    @GetMapping("/sign-up")
    public ModelAndView getSignUpPage() {
        ModelAndView mav = new ModelAndView("sign-up");
        mav.addObject("user", new User());
        return mav;
    }

    @GetMapping("/test")
    public ModelAndView getTestPage() {
        return new ModelAndView("test");
    }
}
