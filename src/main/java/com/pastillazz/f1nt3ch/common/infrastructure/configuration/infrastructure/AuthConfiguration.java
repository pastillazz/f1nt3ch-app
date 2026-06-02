package com.pastillazz.f1nt3ch.common.infrastructure.configuration.infrastructure;

import com.pastillazz.f1nt3ch.users.infrastructure.repositories.MySQLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration {

    private final MySQLRepository mySQLRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> mySQLRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider =
                new DaoAuthenticationProvider(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }
    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration configuration) {
    return configuration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

}
