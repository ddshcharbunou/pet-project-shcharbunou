package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.exception.message.ValidationMessage;
import by.shcharbunou.core.service.user.RoleService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Service("userService")
@Transactional(transactionManager = "transactionManager")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private static final int MIN_NAME_AND_SURNAME_LENGTH = 2;
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$");

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
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
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User findUserByUsernameAndEmail(String username, String email) throws UserNotFoundException {
        User user = userRepository.findByUsernameAndEmail(username, email);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UserNotFoundException(UserMessage.USER_NOT_FOUND.getMessage());
    }

    @Override
    public User createUser(HttpServletRequest request, User user) throws ValidationException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeated_password");
        boolean isValidated = validate(email, phone, name, surname, username, password, repeatedPassword);
        if (isValidated) {
            user.setEmail(email);
            user.setPhone(phone);
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(roleService.findRoleByDesignation(RoleDesignation.USER));
            user.setGroup(null);
        }
        return user;
    }

    @Override
    public boolean checkUsernameAvailability(User user) throws ValidationException {
        try {
            if (Objects.nonNull(findUserByUsername(user.getUsername()))) {
                throw new ValidationException(ValidationMessage.USERNAME_EXISTS.getMessage());
            }
        } catch (UserNotFoundException e) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmailAvailability(User user) throws ValidationException {
        try {
            if (Objects.nonNull(findUserByEmail(user.getEmail()))) {
                throw new ValidationException(ValidationMessage.EMAIL_EXISTS.getMessage());
            }
        } catch (UserNotFoundException e) {
            return true;
        }
        return false;
    }

    private boolean validate(String email, String phone, String name, String surname, String username,
                             String password, String repeatedPassword) throws ValidationException {
        if (Objects.isNull(email) || !(EMAIL_PATTERN.matcher(email).matches())) {
            throw new ValidationException(ValidationMessage.EMAIL_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(phone) || !(PHONE_PATTERN.matcher(phone).matches())) {
            throw new ValidationException(ValidationMessage.PHONE_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(name) || name.length() < MIN_NAME_AND_SURNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.NAME_AND_SURNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(surname) || surname.length() < MIN_NAME_AND_SURNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.NAME_AND_SURNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(username) || username.length() < MIN_USERNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.USERNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(password) || password.length() < MIN_PASSWORD_LENGTH) {
            throw new ValidationException(ValidationMessage.PASSWORD_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(repeatedPassword) || !repeatedPassword.equals(password)) {
            throw new ValidationException(ValidationMessage.REPEATED_PASSWORD_VALIDATION_MESSAGE.getMessage());
        }
        return true;
    }
}
