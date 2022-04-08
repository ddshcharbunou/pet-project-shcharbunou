package by.shcharbunou.core.service.user.impl;

import by.shcharbunou.dal.entity.user.User;
import by.shcharbunou.dal.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service("userDetailsService")
@Transactional(transactionManager = "transactionManager")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User temporaryUser = userRepository.findByUsername(username);
        if (Objects.isNull(temporaryUser)) {
            throw new UsernameNotFoundException("Ошибка: Пользователя с таким юзернеймом не существует");
        }
        if (Objects.nonNull(temporaryUser.getActivationCode())) {
            throw new UsernameNotFoundException("Ошибка: Аккаунт не активирован");
        }
        return new org.springframework.security.core.userdetails.User(temporaryUser.getUsername(),
                temporaryUser.getPassword(), getUserAuthorities(temporaryUser));
    }

    private Set<GrantedAuthority> getUserAuthorities(User user) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getDesignation().name()));
        return grantedAuthorities;
    }
}
