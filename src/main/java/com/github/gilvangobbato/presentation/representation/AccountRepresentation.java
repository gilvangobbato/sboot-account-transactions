package com.github.gilvangobbato.presentation.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRepresentation {

    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("document_number")
    private String documentNumber;

}
