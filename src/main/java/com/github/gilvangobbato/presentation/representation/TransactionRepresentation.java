package com.github.gilvangobbato.presentation.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionRepresentation {


    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("transaction_id")
    private Long transactionId;
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("aperation_type_id")
    private Long operationTypeId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("event_date")
    private LocalDateTime eventDate;

}
