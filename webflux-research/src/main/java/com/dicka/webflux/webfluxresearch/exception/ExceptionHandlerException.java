package com.dicka.webflux.webfluxresearch.exception;

import com.dicka.webflux.webfluxresearch.dto.InputValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerException {

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<InputValidationResponse> handleException(InputValidationException ex){
        return ResponseEntity.badRequest()
                .body(InputValidationResponse.builder()
                        .errorCode(ex.getErrorCode())
                        .input(ex.getInput())
                        .message(ex.getMessage())
                        .build());
    }
}
