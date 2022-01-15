package com.github.gilvangobbato.presentation.mapper;

import com.github.gilvangobbato.domain.Transaction;
import com.github.gilvangobbato.presentation.representation.TransactionRepresentation;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper implements RepresentationMapper<TransactionRepresentation, Transaction> {

    @Override
    public TransactionRepresentation toRepresentation(Transaction entity) {
        return TransactionRepresentation.builder()
                .transactionId(entity.getTransactionId())
                .accountId(entity.getAccountId())
                .operationTypeId(entity.getOperationTypeId())
                .amount(entity.getAmount())
                .eventDate(entity.getEventDate())
                .build();
    }

    @Override
    public Transaction toEntity(TransactionRepresentation representation) {
        return Transaction.builder()
                .accountId(representation.getAccountId())
                .operationTypeId(representation.getOperationTypeId())
                .amount(representation.getAmount())
                .build();
    }

}
