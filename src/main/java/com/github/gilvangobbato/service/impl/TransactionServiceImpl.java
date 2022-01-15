package com.github.gilvangobbato.service.impl;

import com.github.gilvangobbato.domain.OperationType;
import com.github.gilvangobbato.domain.Transaction;
import com.github.gilvangobbato.repository.OperationTypeRepository;
import com.github.gilvangobbato.repository.TransactionRepository;
import com.github.gilvangobbato.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;
    private OperationTypeRepository operationTypeRepository;

    @Override
    public Transaction create(Transaction transaction) {
        // Get OperationType to multiply de amount
        OperationType operationType = operationTypeRepository.findById(transaction.getOperationTypeId()).orElseThrow();
        BigDecimal amount = transaction.getAmount().abs().multiply(BigDecimal.valueOf(operationType.getMultiplier()));
        transaction.setAmount(amount);
        transaction.setEventDate(LocalDateTime.now());

        return repository.save(transaction);
    }

}
