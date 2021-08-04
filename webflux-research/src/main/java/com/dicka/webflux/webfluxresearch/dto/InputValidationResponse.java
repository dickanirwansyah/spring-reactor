package com.dicka.webflux.webfluxresearch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InputValidationResponse {

    private int errorCode;
    private int input;
    private String message;
}
