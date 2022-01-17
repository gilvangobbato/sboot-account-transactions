package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "SELECT SUM(amount) FROM transactions t WHERE t.account_id = :accountId")
    BigDecimal getTotalTransactions(Long accountId);

}
