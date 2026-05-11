package com.pastillazz.f1nt3ch.users.domain.model;

public record User(Long Id,
                   String alias,
                   String password,
                   String email,
                   String firstname,
                   String lastname,
                   Roles roles) {
}
