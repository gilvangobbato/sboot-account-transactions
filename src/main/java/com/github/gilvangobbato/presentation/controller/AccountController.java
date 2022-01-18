package com.github.gilvangobbato.presentation.controller;

import com.github.gilvangobbato.domain.entities.Account;
import com.github.gilvangobbato.presentation.mapper.AccountMapper;
import com.github.gilvangobbato.presentation.representation.AccountRepresentation;
import com.github.gilvangobbato.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts", description = "Create and maintain accounts")
@RestController
@RequestMapping(value = "v1/accounts",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private AccountMapper mapper;
    @Autowired
    private AccountService service;

    @PostMapping
    public ResponseEntity<AccountRepresentation> create(@RequestBody AccountRepresentation account) {
        Account entity = service.create(mapper.toEntity(account));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toRepresentation(entity));
    }

    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountRepresentation> getAccountDetails(@PathVariable("accountId") Long accountId) {
        Account account = service.findById(accountId);
        if(account == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mapper.toRepresentation(account));
    }
}
