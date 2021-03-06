package com.github.gilvangobbato.presentation.controller;

import com.github.gilvangobbato.domain.entities.Account;
import com.github.gilvangobbato.exceptions.AlreadyExistsException;
import com.github.gilvangobbato.presentation.mapper.AccountMapper;
import com.github.gilvangobbato.presentation.representation.AccountRepresentation;
import com.github.gilvangobbato.service.AccountService;
import com.github.gilvangobbato.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountMapper mapper;

    @Mock
    private AccountService service;

    @InjectMocks
    private AccountController controller;

    @Test
    void create() {
        AccountRepresentation representation = AccountRepresentation.builder()
                .documentNumber("12344321")
                .build();
        Account account = Account.builder()
                .accountId(1L)
                .documentNumber("12344321")
                .build();

        when(mapper.toEntity(any())).thenReturn(account);
        when(service.create(any())).thenReturn(account);
        when(mapper.toRepresentation(any())).thenReturn(representation);

        ResponseEntity<AccountRepresentation> response = controller.create(representation);

        assertNotNull(response);
        assertEquals("12344321", response.getBody().getDocumentNumber());
    }

    @Test
    void createAlreadyExistsException() {
        AccountRepresentation representation = AccountRepresentation.builder()
                .documentNumber("12344321")
                .build();
        Account account = Account.builder()
                .accountId(1L)
                .documentNumber("12344321")
                .build();

        when(mapper.toEntity(any())).thenReturn(account);
        when(service.create(any())).thenThrow(new AlreadyExistsException(Constants.ACCOUNT_ALREADY_EXISTS_MESSAGE));

        assertThrows(AlreadyExistsException.class, () -> controller.create(representation));

    }

    @Test
    void getAccountDetails() {
        Account account = Account.builder()
                .accountId(1L)
                .documentNumber("12344321")
                .build();
        AccountRepresentation representation = AccountRepresentation.builder()
                .accountId(1L)
                .documentNumber("12344321")
                .build();

        when(service.findById(any())).thenReturn(account);
        when(mapper.toRepresentation(any())).thenReturn(representation);

        ResponseEntity<AccountRepresentation> response = controller.getAccountDetails(1L);

        assertNotNull(response);
        assertEquals(1L, response.getBody().getAccountId());
    }

    @Test
    void getAccountDetailsNull() {
        when(service.findById(any())).thenReturn(null);

        ResponseEntity response = controller.getAccountDetails(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}