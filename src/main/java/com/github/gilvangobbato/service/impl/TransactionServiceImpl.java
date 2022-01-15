package com.github.gilvangobbato.service.impl;

import com.github.gilvangobbato.domain.entities.OperationType;
import com.github.gilvangobbato.domain.entities.Transaction;
import com.github.gilvangobbato.domain.repository.AccountRepository;
import com.github.gilvangobbato.domain.repository.OperationTypeRepository;
import com.github.gilvangobbato.domain.repository.TransactionRepository;
import com.github.gilvangobbato.service.TransactionService;
import com.github.gilvangobbato.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;
    private AccountRepository accountRepository;
    private OperationTypeRepository operationTypeRepository;

    /**
     * Creates a transaction, if the account or operationType does not exist will throw NoSuchElementException
     *
     * @param transaction
     * @return
     */
    @Override
    public Transaction create(Transaction transaction) {
        //Verify if the account exists
        if (!accountRepository.existsById(transaction.getAccountId())) {
            throw new NoSuchElementException(Constants.ACCOUNT_NOT_FOUND);
        }

        // Get OperationType to multiply de amount
        OperationType operationType = operationTypeRepository.findById(transaction.getOperationTypeId())
                .orElseThrow(() -> new NoSuchElementException(Constants.OPERATION_NOT_FOUND));

        // Convert the amount to positive to calculate with the multiplier registered in the operationType
        BigDecimal amount = transaction.getAmount().abs().multiply(BigDecimal.valueOf(operationType.getMultiplier()));
        transaction.setAmount(amount);
        transaction.setEventDate(LocalDateTime.now());

        return repository.save(transaction);
    }

}
