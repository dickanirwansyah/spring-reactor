package com.dicka.reactivex.reactivexresearch.service;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class ExampleReactiveObservableZipImpl implements ExampleReactiveObservableZip{

    private static final String URI = "http://localhost:80/test/valid/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String consumeZip() {

        Observable<String> observableGreet = Observable
                .fromCallable(this::consumGreet)
                .map(response -> {
                    if (response.equals("hallo")){
                        return "hello hello nya 2x";
                    }else{
                        return "random hello";
                    }
                })
                .subscribeOn(Schedulers.newThread());

        Observable<String> observableName = Observable
                .fromCallable(this::consumeName)
                .subscribeOn(Schedulers.newThread());

        String responseZip = Observable.zip(observableGreet, observableName,
                        this::merge)
                .blockingFirst();

        return responseZip;
    }


    private String merge(String obsGreet, String obsName){
        log.info("## call method merge");
        log.info("-obsGreet : {}", obsGreet);
        log.info("-obsName: {}",obsName);
        return "Assalamualaikum "+obsGreet+", "+obsName;
    }

    private String consumGreet(){
        log.info("## call api greet");
        return restTemplate.getForEntity(URI+"get-greet", String.class).getBody();
    }

    private String consumeName(){
        log.info("## call api name");
        return restTemplate.getForEntity(URI+"get-name", String.class).getBody();
    }
}
