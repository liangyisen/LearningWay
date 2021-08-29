package com.yisen.webflux;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 **/
@RestController
public class HelloWebFluxController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, WebFlux !";
    }

    /**
     * Mono：返回 0 或 1 个元素，即单个对象。
     * Flux：返回 N 个元素，即 List 列表对象。
     *
     * @return
     */
    @GetMapping("/user")
    public Mono<SecurityProperties.User> getUser() {
        SecurityProperties.User user = new SecurityProperties.User();
        user.setName("犬小哈");
        user.setPassword("欢迎关注我的公众号: 小哈学Java");
        return Mono.just(user);
    }


}

