package com.github.gilvangobbato.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Account")
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "document_number", length = 14, nullable = false)
    private String documentNumber;

    @Column(name="credit_limit", nullable = false)
    private BigDecimal limit;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId) && documentNumber.equals(account.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, documentNumber);
    }
}
