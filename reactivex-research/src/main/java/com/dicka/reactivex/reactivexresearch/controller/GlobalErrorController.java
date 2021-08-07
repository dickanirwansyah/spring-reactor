package com.dicka.reactivex.reactivexresearch.controller;

import com.dicka.reactivex.reactivexresearch.exception.ErrorException;
import com.dicka.reactivex.reactivexresearch.model.ErrorCustom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorController {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorCustom> errorCustom(ErrorException e){
        return ResponseEntity.badRequest()
                .body(ErrorCustom.builder()
                        .code(e.getErrorCode())
                        .message(e.getMessage())
                        .build());
    }
}
