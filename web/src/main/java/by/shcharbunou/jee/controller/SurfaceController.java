package by.shcharbunou.jee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"user", "ROLE"})
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
        return new ModelAndView("sign-up");
    }

    @GetMapping("/test")
    public ModelAndView getTestPage() {
        return new ModelAndView("test");
    }
}
