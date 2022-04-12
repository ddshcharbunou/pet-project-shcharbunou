package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.dto.user.request.UserRequest;
import by.shcharbunou.core.dto.user.response.UserResponse;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.exception.message.ValidationMessage;
import by.shcharbunou.core.mapper.user.UserMapper;
import by.shcharbunou.core.service.mail.MailSender;
import by.shcharbunou.core.service.user.RoleService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

@Slf4j
@Service("userService")
@Transactional(transactionManager = "transactionManager")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;
    private static final int MIN_NAME_AND_SURNAME_LENGTH = 2;
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[+]{1}[0-9]{3}([\\s-]?\\d{2}|[(]?[0-9]{2}[)])?([\\s-]?[0-9]){6,7}$");

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserMapper userMapper,
                           PasswordEncoder passwordEncoder, MailSender mailSender) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        log.debug("UserService initialized");
    }

    @Value("${activation.link}")
    private String activationLink;

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
    public UserResponse findUserResponseByUsername(String username) throws UserNotFoundException {
        UserResponse userResponse = userMapper.userToUserResponse(userRepository.findByUsername(username));
        if (Objects.nonNull(userResponse)) {
            return userResponse;
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
    public User createUser(UserRequest userRequest) throws ValidationException {
        boolean isValidated = validate(userRequest);
        Role userRole = roleService.findRoleByDesignation(RoleDesignation.USER);
        User user = null;
        if (isValidated) {
            user = userMapper.userRequestToUser(userRequest);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setRole(userRole);
            user.setActivationCode(UUID.randomUUID().toString());
            user.setGroup(null);
        }
        userRole.connectUser(user);
        log.info("User created: " + user);
        return user;
    }

    @Override
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (Objects.isNull(user)) {
            return false;
        }
        user.setActivationCode(null);

        userRepository.save(user);

        log.info("User activated: " + user);
        return true;
    }

    @Override
    public void sendActivationCode(User user) {
        String message = String.format(
                "Hi, %s!\n" +
                        "Thank you for registering in our school :)\n" +
                        "To verify your account, go to the following link:\n" +
                        activationLink + "%s",
                user.getName(),
                user.getActivationCode()
        );

        mailSender.send(user.getEmail(), "Activation", message);
    }

    private boolean checkUsernameAvailability(UserRequest userRequest) {
        try {
            if (Objects.nonNull(findUserByUsername(userRequest.getUsername()))) {
                return false;
            }
        } catch (UserNotFoundException e) {
            return true;
        }
        return false;
    }

    private boolean checkEmailAvailability(UserRequest userRequest) {
        try {
            if (Objects.nonNull(findUserByEmail(userRequest.getEmail()))) {
                return false;
            }
        } catch (UserNotFoundException e) {
            return true;
        }
        return false;
    }

    private boolean validate(UserRequest userRequest) throws ValidationException {
        boolean usernameAdmitted = checkUsernameAvailability(userRequest);
        boolean emailAdmitted = checkEmailAvailability(userRequest);
        if (!usernameAdmitted) {
            throw new ValidationException(ValidationMessage.USERNAME_EXISTS.getMessage());
        }
        if (!emailAdmitted) {
            throw new ValidationException(ValidationMessage.EMAIL_EXISTS.getMessage());
        }
        if (Objects.isNull(userRequest.getEmail()) || !(EMAIL_PATTERN.matcher(userRequest.getEmail()).matches())) {
            throw new ValidationException(ValidationMessage.EMAIL_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userRequest.getPhone()) || !(PHONE_PATTERN.matcher(userRequest.getPhone()).matches())) {
            throw new ValidationException(ValidationMessage.PHONE_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userRequest.getName()) || userRequest.getName().length() < MIN_NAME_AND_SURNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.NAME_AND_SURNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userRequest.getSurname()) || userRequest.getSurname().length() < MIN_NAME_AND_SURNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.NAME_AND_SURNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userRequest.getUsername()) || userRequest.getUsername().length() < MIN_USERNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.USERNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userRequest.getPassword()) || userRequest.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new ValidationException(ValidationMessage.PASSWORD_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userRequest.getRepeated_password())
                || !userRequest.getRepeated_password().equals(userRequest.getPassword())) {
            throw new ValidationException(ValidationMessage.REPEATED_PASSWORD_VALIDATION_MESSAGE.getMessage());
        }
        log.info("User validated: " + userRequest);
        return true;
    }
}
