package com.github.gilvangobbato.repository;

import com.github.gilvangobbato.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByDocumentNumber(String documentNumber);

    Account getByAccountId(Long accountId);


}
