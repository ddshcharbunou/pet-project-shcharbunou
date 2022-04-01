package by.shcharbunou.jee.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Log4j2
@Controller
@SessionAttributes({"user", "ROLE"})
public class AdminController {
}
