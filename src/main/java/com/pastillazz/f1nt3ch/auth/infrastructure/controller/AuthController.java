package com.pastillazz.f1nt3ch.auth.infrastructure.controller;

import com.pastillazz.f1nt3ch.auth.application.AuthService;
import com.pastillazz.f1nt3ch.auth.infrastructure.dto.AuthResponse;
import com.pastillazz.f1nt3ch.auth.infrastructure.dto.LoginRequest;
import com.pastillazz.f1nt3ch.auth.infrastructure.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(LoginRequest  loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
