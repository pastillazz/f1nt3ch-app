package com.pastillazz.f1nt3ch.users.infrastructure.mapper;

import com.pastillazz.f1nt3ch.users.domain.model.User;
import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(null)
                .userName(user.username())
                .password(user.password())
                .email(user.email())
                .firstName(user.firstname())
                .lastName(user.lastname())
                .roles(user.roles())
                .build();
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
