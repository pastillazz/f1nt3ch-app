package com.pastillazz.f1nt3ch.users.infrastructure.mapper;

import com.pastillazz.f1nt3ch.users.domain.model.User;
import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                null,
                user.username(),
                user.password(),
                user.email(),
                user.firstname(),
                user.lastname(),
                user.roles()
        );

    }

    public User toModel(UserEntity entity) {

        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getRoles()
        );
    }
}
