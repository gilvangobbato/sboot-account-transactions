package com.github.gilvangobbato.presentation.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionRepresentation {


    @JsonProperty("transaction_id")
    private Long transactionId;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("aperation_type_id")
    private Long operationTypeId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("event_date")
    private LocalDateTime eventDate;

}
