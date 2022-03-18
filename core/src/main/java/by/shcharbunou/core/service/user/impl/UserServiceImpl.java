package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.core.exception.UserNotFoundException;
import by.shcharbunou.core.exception.message.UserMessage;
import by.shcharbunou.core.service.user.UserService;
import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
}
