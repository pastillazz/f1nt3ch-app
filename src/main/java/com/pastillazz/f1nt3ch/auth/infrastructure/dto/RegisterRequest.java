package com.pastillazz.f1nt3ch.auth.infrastructure.dto;

public record RegisterRequest(String alias,
                              String password,
                              String email,
                              String firstname,
                              String lastname) {
}
