package com.yisen.webflux.api;

import com.yisen.webflux.domain.User;
import com.yisen.webflux.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 **/
@RestController
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    /**
     * Mono：返回 0 或 1 个元素，即单个对象。
     * Flux：返回 N 个元素，即 List 列表对象。
     *
     * @return
     */
    @GetMapping("/user")
    public Mono<SecurityProperties.User> getUser() {
        SecurityProperties.User user = new SecurityProperties.User();
        user.setName("yisen");
        user.setPassword("123");
        return Mono.just(user);
    }

    @GetMapping("/hello")
    // 返回类型为Mono<String>
    public Mono<String> hello() {
        // 使用Mono.just生成响应式数据
        return Mono.just("Hello world");
    }

    @GetMapping("{id}")
    public Mono<User> query(@PathVariable("id") Long id) {
        return Mono.just(userService.findById(id));
    }

    @GetMapping("getAll")
    public Flux<User> getAll() {
        return Flux.fromIterable(userService.getAll());
    }

}

