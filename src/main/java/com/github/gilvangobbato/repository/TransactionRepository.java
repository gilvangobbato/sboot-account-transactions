package com.github.gilvangobbato.repository;

import com.github.gilvangobbato.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
