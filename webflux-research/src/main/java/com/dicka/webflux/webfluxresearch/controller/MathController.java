package com.dicka.webflux.webfluxresearch.controller;

import com.dicka.webflux.webfluxresearch.dto.MultiplyRequest;
import com.dicka.webflux.webfluxresearch.dto.Response;
import com.dicka.webflux.webfluxresearch.service.MathService;
import com.dicka.webflux.webfluxresearch.service.ReactiveMathService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/math")
public class MathController {

    @Autowired
    private MathService mathService;

    @Autowired
    private ReactiveMathService reactiveMathService;

    @GetMapping(value = "/find-square/{input}")
    private Response findSquare(@PathVariable("input")Integer input){
        return mathService.findSquare(input);
    }

    @GetMapping(value = "/multi-table/{input}")
    public List<Response> multiTable(@PathVariable("input")Integer input){
        return mathService.multiplicationTable(input);
    }

    @GetMapping(value = "/reactive-find-square/{input}")
    public Mono<Response> reactiveFindSquare(@PathVariable("input")Integer input){
        return reactiveMathService.findSquare(input);
    }

    @GetMapping(value = "/reactive-multitable/{input}")
    public Flux<Response> reactiveMultiTable(@PathVariable("input")Integer input){
        return reactiveMathService.multiTable(input);
    }

    @GetMapping(value = "/stream-reactive-multitable/{input}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Response> streamreactiveMultiTable(@PathVariable("input")Integer input){
        return reactiveMathService.multiTable(input);
    }

    @PostMapping(value = "/multiply")
    public Mono<Response> multiply(@RequestBody Mono<MultiplyRequest> requestMono,
                                   @RequestHeader Map<String, String> reqHeaders){
        log.info("headers : {} ",reqHeaders);
        return reactiveMathService.multiply(requestMono);
    }
}
