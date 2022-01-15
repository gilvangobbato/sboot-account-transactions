package com.github.gilvangobbato.presentation.representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseRepresentation {

    private Integer codigoStatus;
    private String mensagem;
    private String data;

}
