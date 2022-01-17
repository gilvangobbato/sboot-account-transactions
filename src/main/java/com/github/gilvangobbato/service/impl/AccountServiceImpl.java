package com.github.gilvangobbato.service.impl;

import com.github.gilvangobbato.domain.entities.Account;
import com.github.gilvangobbato.exceptions.AlreadyExistsException;
import com.github.gilvangobbato.domain.repository.AccountRepository;
import com.github.gilvangobbato.exceptions.PreconditionFailedException;
import com.github.gilvangobbato.service.AccountService;
import com.github.gilvangobbato.util.Constants;
import com.github.gilvangobbato.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private AccountRepository repository;

    /**
     * Create an account, if the account number is already registered will throw AlreadyExistsException
     *
     * @param account
     * @return
     */
    @Override
    public Account create(Account account) {
        this.validate(account);
        return repository.save(account);
    }

    /**
     * Find an account by accountId, if not exist will return null
     *
     * @param accountId
     * @return
     */
    @Override
    public Account findById(Long accountId) {
        return repository.findById(accountId).orElse(null);
    }

    /*
     * Validate account details
     */
    private void validate(Account account) {
        if (account.getDocumentNumber() == null
                || account.getDocumentNumber().isEmpty()
                || (account.getDocumentNumber().length() != 11 && account.getDocumentNumber().length() != 14)) {
            throw new PreconditionFailedException(Constants.ACCOUNT_DOCUMENT_INVALID_MESSAGE);
        }
        if (!StringUtils.isOnlyNumbers(account.getDocumentNumber())) {
            throw new PreconditionFailedException(Constants.ACCOUNT_DOCUMENT_ONLY_NUMBERS_MESSAGE);
        }
        if (account.getLimit() == null || account.getLimit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PreconditionFailedException(Constants.ACCOUNT_LIMIT_INVALID_MESSAGE);
        }
        if (repository.existsByDocumentNumber(account.getDocumentNumber())) {
            throw new AlreadyExistsException(Constants.ACCOUNT_ALREADY_EXISTS_MESSAGE);
        }
    }
}
