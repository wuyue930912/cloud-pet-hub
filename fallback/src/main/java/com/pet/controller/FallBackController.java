package com.pet.controller;

import com.pet.config.ServiceException;
import com.pet.vo.ResponseResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @RequestMapping("/error")
    public ResponseEntity<ResponseResultVO<String>> fallback() {
        log.error("MAINTAIN服务熔断");
        throw new ServiceException("MAINTAIN服务熔断");
    }

    @RequestMapping("/error1")
    public ResponseEntity<ResponseResultVO<String>> fallback1() {
        log.error("WEBSOCKET服务熔断");
        throw new ServiceException("WEBSOCKET服务熔断");
    }
}
