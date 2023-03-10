package com.yisen.javaweb.controller;

import com.yisen.javaweb.service.HelloService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("helloService")
    public String helloService() {
        return helloService.hello();
    }

    @GetMapping("helloService1")
    public String helloService(@RequestBody UserInfo userInfo) {

        String userId = request.getParameter("userId");
        Object userId1 = request.getAttribute("userId");

        return userInfo.getName() + userId + userId1;
    }

    @GetMapping("testHtml")
    private String testHtml() {
        System.out.println("helloService = " + 1231);
        return "<html><h>AAA</h></html> ";
    }

    @Data
    private static class UserInfo implements Serializable {

        private String name;
    }
}
