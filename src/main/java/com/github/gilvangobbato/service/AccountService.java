package com.github.gilvangobbato.service;

import com.github.gilvangobbato.domain.entities.Account;

public interface AccountService {
    Account create(Account account);
    Account findById(Long accountId);
}
