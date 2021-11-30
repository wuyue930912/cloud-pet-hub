package com.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.pet")
@EnableScheduling
@Slf4j
public class FallBackStarter {
    public static void main(String[] args) {
        SpringApplication.run(FallBackStarter.class, args);
        log.info("start running fall back server");
    }
}