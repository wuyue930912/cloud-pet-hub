package com.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
@Slf4j
public class ConfigStarter {
    public static void main(String[] args) {
        SpringApplication.run(ConfigStarter.class, args);
        log.info("start running config server");
    }
}