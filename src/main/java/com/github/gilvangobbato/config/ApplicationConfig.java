package com.github.gilvangobbato.config;

import com.github.gilvangobbato.repository.AccountRepository;
import com.github.gilvangobbato.repository.OperationTypeRepository;
import com.github.gilvangobbato.repository.TransactionRepository;
import com.github.gilvangobbato.service.AccountService;
import com.github.gilvangobbato.service.TransactionService;
import com.github.gilvangobbato.service.impl.AccountServiceImpl;
import com.github.gilvangobbato.service.impl.TransactionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public AccountService accountService(AccountRepository repository) {
        return new AccountServiceImpl(repository);
    }


    @Bean
    public TransactionService transactionService(TransactionRepository repository,
                                                 OperationTypeRepository operationTypeRepository,
                                                 AccountRepository accountRepository) {
        return new TransactionServiceImpl(repository, accountRepository, operationTypeRepository);
    }
}
