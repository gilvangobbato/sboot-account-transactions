package com.github.gilvangobbato.presentation.controller;

import com.github.gilvangobbato.domain.entities.Transaction;
import com.github.gilvangobbato.presentation.mapper.TransactionMapper;
import com.github.gilvangobbato.presentation.representation.TransactionRepresentation;
import com.github.gilvangobbato.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    @Autowired
    private TransactionMapper mapper;
    @Autowired
    private TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionRepresentation> transaction(@RequestBody TransactionRepresentation transaction) {

        Transaction entity = service.create(mapper.toEntity(transaction));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toRepresentation(entity));
    }

}
