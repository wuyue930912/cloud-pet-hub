package com.pet.controller;

import com.pet.config.ServiceException;
import com.pet.constant.ErrorMsgConstant;
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
        log.error(ErrorMsgConstant.MAINTAIN_ERROR);
        throw new ServiceException(ErrorMsgConstant.MAINTAIN_ERROR);
    }

    @RequestMapping("/error1")
    public ResponseEntity<ResponseResultVO<String>> fallback1() {
        log.error(ErrorMsgConstant.WEBSOCKET_ERROR);
        throw new ServiceException(ErrorMsgConstant.WEBSOCKET_ERROR);
    }
}
