package com.pastillazz.f1nt3ch.wallet.infrastructure.repositories;

import com.pastillazz.f1nt3ch.wallet.infrastructure.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySQLWalletRepository extends JpaRepository<WalletEntity, Long> {

    List<WalletEntity> findAllByUserId(Long userId);

}
