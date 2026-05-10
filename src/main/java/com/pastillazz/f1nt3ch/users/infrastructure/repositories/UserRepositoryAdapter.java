package com.pastillazz.f1nt3ch.users.infrastructure.repositories;

import com.pastillazz.f1nt3ch.users.domain.model.User;
import com.pastillazz.f1nt3ch.users.domain.port.UserRepository;
import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import com.pastillazz.f1nt3ch.users.infrastructure.mapper.UserMapper;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public class UserRepositoryAdapter implements UserRepository {
    private MySQLRepository mySQLRepository;
    private UserMapper userMapper;

    public UserRepositoryAdapter(MySQLRepository mySQLRepository,
                                 UserMapper userMapper) {
        this.mySQLRepository = mySQLRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = mySQLRepository.save(userEntity);
        return userMapper.toModel(savedEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return mySQLRepository.findByUserName(username)
                .map(user-> userMapper.toModel(user));

    }
}
