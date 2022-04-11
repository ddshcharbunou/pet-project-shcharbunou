package by.shcharbunou.jee.config;

import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SuccessAuthenticationHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new FailureAuthenticationHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAuthority(RoleDesignation.ADMIN.name())
                .antMatchers("/office/**").hasAuthority(RoleDesignation.USER.name())
                .and()
                .formLogin()
                .loginPage("/sign-in").loginProcessingUrl("/sign-in").successHandler(successHandler()).failureHandler(failureHandler())
                .and()
                .logout().logoutUrl("/sign-out").logoutSuccessUrl("/sign-in")
                .and()
                .csrf().disable();
        http.userDetailsService(userDetailsService);
    }
}
