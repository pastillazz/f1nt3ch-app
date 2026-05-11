package com.pastillazz.f1nt3ch.users.domain.model;

import lombok.Builder;

@Builder
public record User(Long Id,
                   String alias,
                   String password,
                   String email,
                   String firstname,
                   String lastname,
                   Roles roles) {
}
