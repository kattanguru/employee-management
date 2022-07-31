package com.greatlearning.employee.config;

import com.greatlearning.employee.entity.Role;
import com.greatlearning.employee.repository.RoleRepository;
import com.greatlearning.employee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN = "ADMIN";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/employees").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<com.greatlearning.employee.entity.User> users = userRepository.findAll();
        List<UserDetails> userDetails = new ArrayList<>();
        users.forEach(normalUser -> {
            List<Role> role = roleRepository.findByRoleId(String.valueOf(normalUser.getRoleId()));
            role.forEach(singleRole -> {
                if (ADMIN.equalsIgnoreCase(singleRole.getName())) {
                    UserDetails user = User.withDefaultPasswordEncoder()
                            .username(normalUser.getUserName())
                            .password(normalUser.getPassword())
                            .roles(singleRole.getName())
                            .build();
                    userDetails.add(user);
                }
            });
        });
        return new InMemoryUserDetailsManager(userDetails);
    }
}
