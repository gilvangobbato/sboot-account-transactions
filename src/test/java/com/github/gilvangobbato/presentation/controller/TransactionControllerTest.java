package com.github.gilvangobbato.presentation.controller;

import com.github.gilvangobbato.domain.Transaction;
import com.github.gilvangobbato.presentation.mapper.TransactionMapper;
import com.github.gilvangobbato.presentation.representation.TransactionRepresentation;
import com.github.gilvangobbato.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService service;
    @Mock
    private TransactionMapper mapper;

    @InjectMocks
    private TransactionController controller;

    @Test
    void transaction() {
        TransactionRepresentation representation = TransactionRepresentation.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(20.22))
                .build();
        Transaction entity = Transaction.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(20.22))
                .build();

        when(mapper.toEntity(any())).thenReturn(entity);
        when(service.create(any())).thenReturn(entity);
        when(mapper.toRepresentation(any())).thenReturn(representation);

        ResponseEntity<TransactionRepresentation> response = controller.transaction(representation);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(representation.getAmount(), response.getBody().getAmount());
    }
}