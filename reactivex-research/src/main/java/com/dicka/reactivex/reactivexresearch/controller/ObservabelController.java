package com.dicka.reactivex.reactivexresearch.controller;

import com.dicka.reactivex.reactivexresearch.service.ExampleReactiveObservableZip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/obs/zip")
public class ObservabelController {

    @Autowired
    private ExampleReactiveObservableZip exampleReactiveObservableZip;

    @GetMapping(value = "/merge")
    public String getMerge(){
        return exampleReactiveObservableZip.consumeZip();
    }
}
