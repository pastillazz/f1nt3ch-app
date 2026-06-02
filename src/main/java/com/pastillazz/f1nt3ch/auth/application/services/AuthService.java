package com.pastillazz.f1nt3ch.auth.application.services;

import com.pastillazz.f1nt3ch.auth.application.events.UserCreatedEvent;
import com.pastillazz.f1nt3ch.auth.infrastructure.dto.AuthResponse;
import com.pastillazz.f1nt3ch.auth.infrastructure.dto.LoginRequest;
import com.pastillazz.f1nt3ch.auth.infrastructure.dto.RegisterRequest;
import com.pastillazz.f1nt3ch.common.application.services.NotificationProducerService;
import com.pastillazz.f1nt3ch.common.infrastructure.configuration.application.JwtService;
import com.pastillazz.f1nt3ch.users.domain.model.Roles;
import com.pastillazz.f1nt3ch.users.domain.model.User;
import com.pastillazz.f1nt3ch.users.domain.port.UserRepository;
import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import com.pastillazz.f1nt3ch.users.infrastructure.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final NotificationProducerService notificationProducerService;

    public AuthResponse register(RegisterRequest request){
        if (userRepository.findByEmail(request.email()).isPresent()){
            throw new IllegalArgumentException("User already exists");
        }
      User user= User.builder()
                .email(request.email())
                .alias(request.alias())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .password(passwordEncoder.encode(request.password()))
                .roles(Roles.USER)
                .build();

        User savedUser=userRepository.save(user);

        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .alias(savedUser.alias())
                .email(savedUser.email())
                .title("User registered")
                .message("User registered successfully").build();

        notificationProducerService.sendMessage("user-topic",
                String.valueOf(savedUser.Id()), userCreatedEvent);

        UserEntity userEntity = userMapper.toEntity(savedUser);
        return new AuthResponse(jwtService.generateToken(userEntity));
    }

    public AuthResponse login(LoginRequest request){
    Authentication authentication=authenticationManager
            .authenticate
                    (new UsernamePasswordAuthenticationToken
                            (request.email(),
                            request.password()));

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String token = jwtService.generateToken(userDetails);
            return new AuthResponse(token);
        }

    throw new IllegalStateException("Error logging in");
    }
}
