package com.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MaintainStarter {
    public static void main(String[] args) {
        SpringApplication.run(MaintainStarter.class, args);
    }
}