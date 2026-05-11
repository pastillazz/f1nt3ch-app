package com.pastillazz.f1nt3ch.users.domain.port;

import com.pastillazz.f1nt3ch.users.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
}
