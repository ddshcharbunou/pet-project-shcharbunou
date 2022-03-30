package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.dto.UserDto;
import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.ValidationException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.exception.message.ValidationMessage;
import by.shcharbunou.core.service.user.RoleService;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import by.shcharbunou.dal.entity.user.Role;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public User createUser(UserDto userDto) throws ValidationException {
        boolean isValidated = validate(userDto);
        Role userRole = roleService.findRoleByDesignation(RoleDesignation.USER);
        User user = new User();
        if (isValidated) {
            user.setEmail(userDto.getEmail());
            user.setPhone(userDto.getPhone());
            user.setName(userDto.getName());
            user.setSurname(userDto.getSurname());
            user.setUsername(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            user.setRole(userRole);
            user.setGroup(null);
        }
        userRole.connectUser(user);
        return user;
    }

    private boolean checkUsernameAvailability(UserDto userDto) {
        try {
            if (Objects.nonNull(findUserByUsername(userDto.getUsername()))) {
                return false;
            }
        } catch (UserNotFoundException e) {
            return true;
        }
        return false;
    }

    private boolean checkEmailAvailability(UserDto userDto) {
        try {
            if (Objects.nonNull(findUserByEmail(userDto.getEmail()))) {
                return false;
            }
        } catch (UserNotFoundException e) {
            return true;
        }
        return false;
    }

    private boolean validate(UserDto userDto) throws ValidationException {
        boolean usernameAdmitted = checkUsernameAvailability(userDto);
        boolean emailAdmitted = checkEmailAvailability(userDto);
        if (!usernameAdmitted) {
            throw new ValidationException(ValidationMessage.USERNAME_EXISTS.getMessage());
        }
        if (!emailAdmitted) {
            throw new ValidationException(ValidationMessage.EMAIL_EXISTS.getMessage());
        }
        if (Objects.isNull(userDto.getEmail()) || !(EMAIL_PATTERN.matcher(userDto.getEmail()).matches())) {
            throw new ValidationException(ValidationMessage.EMAIL_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userDto.getPhone()) || !(PHONE_PATTERN.matcher(userDto.getPhone()).matches())) {
            throw new ValidationException(ValidationMessage.PHONE_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userDto.getName()) || userDto.getName().length() < MIN_NAME_AND_SURNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.NAME_AND_SURNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userDto.getSurname()) || userDto.getSurname().length() < MIN_NAME_AND_SURNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.NAME_AND_SURNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userDto.getUsername()) || userDto.getUsername().length() < MIN_USERNAME_LENGTH) {
            throw new ValidationException(ValidationMessage.USERNAME_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userDto.getPassword()) || userDto.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new ValidationException(ValidationMessage.PASSWORD_VALIDATION_MESSAGE.getMessage());
        }
        if (Objects.isNull(userDto.getRepeated_password())
                || !userDto.getRepeated_password().equals(userDto.getPassword())) {
            throw new ValidationException(ValidationMessage.REPEATED_PASSWORD_VALIDATION_MESSAGE.getMessage());
        }
        return true;
    }
}
