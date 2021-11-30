package com.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableDiscoveryClient
@SpringBootApplication
@EnableOpenApi
@EnableScheduling
@EnableFeignClients(basePackages = "com.pet")
@Slf4j
public class MaintainStarter {
    public static void main(String[] args) {
        SpringApplication.run(MaintainStarter.class, args);
        log.info("swagger 3 : [{}]", "http://127.0.0.1/maintain/swagger-ui/index.html#/");
    }
}