package com.pastillazz.f1nt3ch.users.infrastructure.repositories;


import com.pastillazz.f1nt3ch.users.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface MySQLRepository extends JpaRepository <UserEntity, Long> {
    Optional<UserEntity> findByUserName(String userName);
}
