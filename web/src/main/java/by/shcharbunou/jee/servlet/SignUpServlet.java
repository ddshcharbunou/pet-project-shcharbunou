package by.shcharbunou.jee.servlet;

import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.config.DalConfig;
import by.shcharbunou.dal.entity.user.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SignUpServlet", value = "/pages/sign-up")
public class SignUpServlet extends HttpServlet {
    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new AnnotationConfigApplicationContext(DalConfig.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone_number");
        String password = req.getParameter("password");
        UserService userService = context.getBean("userService", UserService.class);
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setGroup(null);
        user.setRole(null);
        userService.save(user);
        getServletContext().getRequestDispatcher("/pages/sign-in.jsp").forward(req, resp);
    }
}
