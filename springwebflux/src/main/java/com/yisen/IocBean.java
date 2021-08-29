package com.yisen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IocBean {

    @Bean
    public WebFlux getWebFlux() {
        return new WebFlux();
    }
}
