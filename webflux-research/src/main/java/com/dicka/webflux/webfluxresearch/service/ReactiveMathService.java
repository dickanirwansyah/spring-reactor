package com.dicka.webflux.webfluxresearch.service;


import com.dicka.webflux.webfluxresearch.dto.MultiplyRequest;
import com.dicka.webflux.webfluxresearch.dto.Response;
import com.dicka.webflux.webfluxresearch.exception.InputValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Slf4j
@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input){
        return Mono.fromSupplier(() -> input * input)
                .map(v -> Response.builder()
                        .date(new Date())
                        .ouput(input)
                        .build());
    }

    public Flux<Response> multiTable(int input){
        return Flux.range(1, 10)
                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> log.info("math-reactive processing : {}", i))
                .map(value -> Response.builder()
                        .ouput(value * input)
                        .date(new Date())
                        .build());
    }

    public Mono<Response> multiply(Mono<MultiplyRequest> dtoMono){
        return dtoMono
                //.map(dto -> dto.getFirst() * dto.getSecond())
                .map(value -> Response.builder()
                        .date(new Date())
                        .ouput(value.getFirst() * value.getSecond())
                        .build());
    }

    public Mono<Response> checkValid(int input){
        if (input >=100){
            throw new InputValidationException(input);
        }
        return findSquare(input);
    }

    public Mono<Response> checkValidation(int input){
        return Mono.just(input)
                .handle((integer, sink) -> {
                    if (integer >= 100)
                        sink.error(new InputValidationException(integer));
                    else
                        sink.next(integer);
                }).cast(Integer.class)
                .flatMap(i -> this.findSquare(i));
    }
}
