package com.pastillazz.f1nt3ch.users.domain.model;

public record User(String username, String password,
                   String email,
                   String firstname,
                   String lastname,
                   String role) {
}
