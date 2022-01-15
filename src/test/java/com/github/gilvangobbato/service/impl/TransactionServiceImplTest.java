package com.github.gilvangobbato.service.impl;

import com.github.gilvangobbato.domain.entities.OperationType;
import com.github.gilvangobbato.domain.entities.Transaction;
import com.github.gilvangobbato.domain.repository.AccountRepository;
import com.github.gilvangobbato.domain.repository.OperationTypeRepository;
import com.github.gilvangobbato.domain.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

    @Mock
    private TransactionRepository repository;
    @Mock
    private OperationTypeRepository operationTypeRepository;
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private TransactionServiceImpl service;

    /*
        When the account does not exist needs to throw NoSuchElementException
     */
    @Test
    void createNoSuchAccount() {
        OperationType operationType = OperationType.builder()
                .operationTypeId(10L)
                .build();
        when(accountRepository.existsById(any())).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> service.create(Transaction.builder().build()));
    }

    /*
        When the operationType does not exist needs to throw NoSuchElementException
     */
    @Test
    void createNoSuchOperationType() {
        OperationType operationType = OperationType.builder()
                .operationTypeId(10L)
                .build();
        when(accountRepository.existsById(any())).thenReturn(true);
        when(operationTypeRepository.findById(any())).thenThrow(new NoSuchElementException());

        assertThrows(NoSuchElementException.class, () -> service.create(Transaction.builder().build()));
    }

    /*
        When the operation has a negative multiplier the transaction needs to be save with negative amount
     */
    @Test
    void createNegativeValue() {
        OperationType operationType = OperationType.builder()
                .operationTypeId(1L)
                .multiplier(-1)
                .build();
        Transaction spyTransaction = Mockito.spy(Transaction.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(20.22))
                .build());

        when(accountRepository.existsById(any())).thenReturn(true);
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(operationType));
        when(repository.save(any())).thenReturn(spyTransaction);

        Transaction entity = service.create(spyTransaction);

        Mockito.verify(spyTransaction).setAmount(BigDecimal.valueOf(-20.22));
    }

    /*
        When the operation has a positive multiplier the transaction needs to be save with positive amount
     */
    @Test
    void createPositiveValue() {
        OperationType operationType = OperationType.builder()
                .operationTypeId(4L)
                .multiplier(1)
                .build();
        Transaction spyTransaction = Mockito.spy(Transaction.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(-20.22))
                .build());

        when(accountRepository.existsById(any())).thenReturn(true);
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(operationType));
        when(repository.save(any())).thenReturn(spyTransaction);

        Transaction entity = service.create(spyTransaction);

        Mockito.verify(spyTransaction).setAmount(BigDecimal.valueOf(20.22));
    }
}