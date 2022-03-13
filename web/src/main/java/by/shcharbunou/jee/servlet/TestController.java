package by.shcharbunou.jee.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/pages/sign-up")
    public String handleSighUp() {
        return "sign-up";
    }
}
