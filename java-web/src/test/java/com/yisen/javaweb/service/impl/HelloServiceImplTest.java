package com.yisen.javaweb.service.impl;

import com.yisen.javaweb.DemoApplicationTest;
import com.yisen.javaweb.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

class HelloServiceImplTest extends DemoApplicationTest {

    @Autowired
    private HelloService helloService;

    @Test
    @DisplayName("helloService")
    void helloService() {
        Assertions.assertEquals("service", helloService.hello());
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("helloController")
    void helloController() throws Exception {

        String viewName = Objects.requireNonNull(mockMvc.perform(get("/helloService").contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getModelAndView()).getViewName();
        Assertions.assertEquals("service", viewName);
    }
}