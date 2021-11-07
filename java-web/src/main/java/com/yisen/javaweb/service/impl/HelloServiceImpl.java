package com.yisen.javaweb.service.impl;

import com.yisen.javaweb.service.HelloService;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(){
        return "service";
    }
}
