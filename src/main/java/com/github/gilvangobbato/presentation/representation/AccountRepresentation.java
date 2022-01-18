package com.github.gilvangobbato.presentation.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountRepresentation {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("document_number")
    private String documentNumber;
    @JsonProperty("credit_limit")
    private BigDecimal limit;

}
