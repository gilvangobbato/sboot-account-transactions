package com.github.gilvangobbato.presentation.mapper;

import com.github.gilvangobbato.domain.entities.Transaction;
import com.github.gilvangobbato.presentation.representation.TransactionRepresentation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    TransactionMapper mapper = new TransactionMapper();

    @Test
    void toRepresentation() {
        Transaction transaction = Transaction.builder()
                .amount(BigDecimal.valueOf(10))
                .operationTypeId(1L)
                .accountId(1L)
                .transactionId(1L)
                .eventDate(LocalDateTime.now())
                .build();

        TransactionRepresentation representation = mapper.toRepresentation(transaction);

        assertNotNull(representation);
    }

    @Test
    void toEntity() {
        TransactionRepresentation representation = TransactionRepresentation.builder()
                .amount(BigDecimal.valueOf(10))
                .operationTypeId(1L)
                .accountId(1L)
                .build();

        Transaction transaction = mapper.toEntity(representation);

        assertNotNull(transaction);
    }
}