package com.github.gilvangobbato.presentation.mapper;

import com.github.gilvangobbato.domain.Account;
import com.github.gilvangobbato.presentation.representation.AccountRepresentation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    private AccountMapper mapper = new AccountMapper();

    @Test
    void toRepresentation() {
        Account account = Account.builder()
                .accountId(1L)
                .documentNumber("123321")
                .build();

        AccountRepresentation representation = mapper.toRepresentation(account);

        assertNotNull(representation);
    }

    @Test
    void toEntity() {
        AccountRepresentation representation = AccountRepresentation.builder()
                .accountId(1L)
                .documentNumber("123321")
                .build();

        Account account = mapper.toEntity(representation);

        assertNotNull(account);
    }
}