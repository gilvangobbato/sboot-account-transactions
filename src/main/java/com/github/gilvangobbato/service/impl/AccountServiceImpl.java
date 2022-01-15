package com.github.gilvangobbato.service.impl;

import com.github.gilvangobbato.domain.Account;
import com.github.gilvangobbato.exceptions.AlreadyExistsException;
import com.github.gilvangobbato.repository.AccountRepository;
import com.github.gilvangobbato.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String ALREADY_EXISTS_MESSAGE = "Conta já cadastrada para o documento informado.";

    private AccountRepository repository;

    @Override
    public Account create(Account account){
        if(repository.existsByDocumentNumber(account.getDocumentNumber())){
            throw new AlreadyExistsException(ALREADY_EXISTS_MESSAGE);
        }

        return repository.save(account);
    }

    @Override
    public Account findById(Long accountId) {
        return repository.getByAccountId(accountId);
    }
}
