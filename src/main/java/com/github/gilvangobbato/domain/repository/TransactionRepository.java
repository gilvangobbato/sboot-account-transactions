package com.github.gilvangobbato.domain.repository;

import com.github.gilvangobbato.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
