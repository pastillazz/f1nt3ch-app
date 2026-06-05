package com.pastillazz.f1nt3ch.transactions.infrastructure.repositories;

import com.pastillazz.f1nt3ch.transactions.infrastructure.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MySQLTransactionRepository extends JpaRepository<TransactionEntity, UUID> {
 List<TransactionEntity> findAllByFromWalletId(Long fromWalletId);

 List<TransactionEntity> findAllByToWalletId(Long toWalletId);

}
