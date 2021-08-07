package com.yisen.javaweb;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/")
    public String hi() {
        System.out.println("hi");
        return "hi";
    }


    @GetMapping("/springSPItest")
    public String springSPItest() {
        List<SpringSPITestInterface> springSPITestInterfaces = SpringFactoriesLoader.loadFactories(SpringSPITestInterface.class, this.getClass().getClassLoader());
        for (SpringSPITestInterface spiTestInterface : springSPITestInterfaces) {
            spiTestInterface.test();
        }
        return "succser";
    }

}

