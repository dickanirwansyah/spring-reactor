package com.dicka.webflux.webfluxresearch.controller;

import com.dicka.webflux.webfluxresearch.dto.Response;
import com.dicka.webflux.webfluxresearch.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/test/valid")
public class ReactiveValidateController {

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping(value = "/input/{input}")
    public Mono<Response> checkValid(@PathVariable("input")int input){
        return reactiveMathService.checkValid(input);
    }

    @GetMapping(value = "/inputs/{inputs}")
    public Mono<Response> checkValidation(@PathVariable("inputs")int input){
        return reactiveMathService.checkValidation(input);
    }
}
