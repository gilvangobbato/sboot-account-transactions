package com.github.gilvangobbato.exceptions;

import com.github.gilvangobbato.presentation.representation.ResponseRepresentation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseRepresentation handleAlreadyExists(AlreadyExistsException exception) {
        return ResponseRepresentation.builder()
                .codigoStatus(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .mensagem(exception.getMessage())
                .data(LocalDateTime.now().toString())
                .build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ResponseRepresentation handleNoSuchElementException(NoSuchElementException exception) {
        return ResponseRepresentation.builder()
                .codigoStatus(HttpStatus.PRECONDITION_FAILED.value())
                .mensagem(exception.getMessage())
                .data(LocalDateTime.now().toString())
                .build();
    }


}
