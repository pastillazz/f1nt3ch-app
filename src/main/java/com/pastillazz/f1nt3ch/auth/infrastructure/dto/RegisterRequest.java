package com.pastillazz.f1nt3ch.auth.infrastructure.dto;

public record RegisterRequest(String username,
                              String password,
                              String email,
                              String firstname,
                              String lastname) {
}
