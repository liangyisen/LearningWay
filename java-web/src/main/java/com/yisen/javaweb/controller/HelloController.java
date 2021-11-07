package com.yisen.javaweb.controller;

import com.yisen.javaweb.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("helloService")
    public String helloService() {
        return helloService.hello();
    }
}
