package by.shcharbunou.jee.config;

import by.shcharbunou.dal.entity.enums.role.RoleDesignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SuccessAuthenticationHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasAuthority(RoleDesignation.ADMIN.name())
                .antMatchers("/office/**").hasAuthority(RoleDesignation.USER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/sign-in").loginProcessingUrl("/login").successHandler(successHandler())
                .and()
                .logout().logoutUrl("/sign-out").logoutSuccessUrl("/login")
                .and()
                .csrf().disable();
        http.userDetailsService(userDetailsService);
    }
}
