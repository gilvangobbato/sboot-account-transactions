package com.github.gilvangobbato.service.impl;

import com.github.gilvangobbato.domain.entities.Account;
import com.github.gilvangobbato.exceptions.AlreadyExistsException;
import com.github.gilvangobbato.domain.repository.AccountRepository;
import com.github.gilvangobbato.exceptions.PreconditionFailedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository repository;

    @InjectMocks
    private AccountServiceImpl service;

    @Test
    void create() {
        Account entity = Account.builder()
                .accountId(1L)
                .documentNumber("12365478998745")
                .limit(BigDecimal.valueOf(10))
                .build();

        when(repository.existsByDocumentNumber(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(entity);

        assertDoesNotThrow(() -> {
            Account account = service.create(entity);
            assertEquals(entity.getDocumentNumber(), account.getDocumentNumber());
        });

    }

    @Test
    void createAlreadyExistsException() {
        Account entity = Account.builder()
                .accountId(1L)
                .documentNumber("12365478998745")
                .limit(BigDecimal.valueOf(10))
                .build();

        when(repository.existsByDocumentNumber(any())).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> service.create(entity));
    }

    @Test
    void createPreconditionFailedException() {
        Account entity = Account.builder()
                .accountId(1L)
                .documentNumber("12365478998745")
                .limit(BigDecimal.valueOf(-10))
                .build();

        assertThrows(PreconditionFailedException.class, () -> service.create(entity));
    }

    @Test
    void findById() {
        Account entity = Account.builder()
                .accountId(1L)
                .documentNumber("12365478998745")
                .limit(BigDecimal.valueOf(10))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        Account account = service.findById(1L);

        assertNotNull(account);
        assertEquals(account.getDocumentNumber(), entity.getDocumentNumber());
    }

    @Test
    void findByIdReturnNull() {
        Account entity = Account.builder()
                .accountId(1L)
                .documentNumber("12365478998745")
                .limit(BigDecimal.valueOf(10))
                .build();

        when(repository.findById(1L)).thenReturn(Optional.empty());

        Account account = service.findById(1L);

        assertNull(account);
    }
}