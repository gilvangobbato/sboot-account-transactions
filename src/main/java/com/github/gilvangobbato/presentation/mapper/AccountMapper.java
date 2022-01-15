package com.github.gilvangobbato.presentation.mapper;

import com.github.gilvangobbato.domain.Account;
import com.github.gilvangobbato.presentation.representation.AccountRepresentation;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper implements RepresentationMapper<AccountRepresentation, Account> {

    @Override
    public AccountRepresentation toRepresentation(Account entity) {

        return AccountRepresentation.builder()
                .accountId(entity.getAccountId())
                .documentNumber(entity.getDocumentNumber())
                .build();
    }

    @Override
    public Account toEntity(AccountRepresentation representation) {
        return Account.builder()
                .accountId(representation.getAccountId())
                .documentNumber(representation.getDocumentNumber())
                .build();
    }
}
