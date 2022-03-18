package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.regex.Pattern;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$");

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(UUID id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User findUserByUsernameAndEmail(String username, String email) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndEmail(username, email);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User createUser(HttpServletRequest request) {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeated_password");
        boolean isValidated = validate(email, phone, name, surname, username, password, repeatedPassword);
        return new User();
    }

    private boolean validate(String email, String phone, String name, String surname,
                             String username, String password, String repeatedPassword) {
        return true;
    }
}
