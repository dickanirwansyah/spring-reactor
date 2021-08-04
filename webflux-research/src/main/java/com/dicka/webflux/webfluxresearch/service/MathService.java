package com.dicka.webflux.webfluxresearch.service;

import com.dicka.webflux.webfluxresearch.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class MathService {

    public Response findSquare(int input){
        return Response.builder()
                .date(new Date())
                .ouput(input * input)
                .build();
    }

    public List<Response> multiplicationTable(int input){
        return IntStream.rangeClosed(1, 10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> log.info("math-service processing - {} ",i))
                .mapToObj(value -> Response.builder()
                        .date(new Date())
                        .ouput(value * input)
                        .build()).collect(Collectors.toList());
    }
}
