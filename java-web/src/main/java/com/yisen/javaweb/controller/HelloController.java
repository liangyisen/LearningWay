package com.yisen.javaweb.controller;

import com.yisen.javaweb.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("helloService")
    public String helloService() {
        return helloService.hello();
    }


    @GetMapping("testHtml")
    private String testHtml() {
        return "<html><h>AAA</h></html>";
    }
}
