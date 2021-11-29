package com.pet;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableAdminServer
@Slf4j
public class AdminStarter {
    public static void main(String[] args) {
        SpringApplication.run(AdminStarter.class, args);
        log.info("start running admin server");
    }
}