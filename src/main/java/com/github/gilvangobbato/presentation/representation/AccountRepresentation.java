package com.github.gilvangobbato.presentation.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class AccountRepresentation {

    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("document_number")
    private String documentNumber;
    @JsonProperty("credit_limit")
    private BigDecimal limit;

}
