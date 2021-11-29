package com.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class EurekaStarter {
    public static void main(String[] args) {
        SpringApplication.run(EurekaStarter.class, args);
        log.info("start running eureka server : 【{}】", "http://127.0.0.1:8888");
    }
}