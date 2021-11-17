package com.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@EnableScheduling
@Slf4j
public class MaintainStarter {
    public static void main(String[] args) {
        SpringApplication.run(MaintainStarter.class, args);
        log.info("swagger 3 : [{}]", "http://127.0.0.1:8080/swagger-ui/index.html#/");
    }
}