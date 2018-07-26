package com.maurofokker.poc.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RefreshScope           // to refresh config on the fly
@RestController
public class SpringMicroservicesConfigClientApplication {

    @Value("${message}")    // this is provided by configuration server
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroservicesConfigClientApplication.class, args);
    }

    @RequestMapping("/message")
    public String message() {
        return this.message;
    }
}
