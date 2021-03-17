package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.LogLevelConstant;
import com.pet.po.SysUser;
import com.pet.service.SysUserService;
import com.pet.vo.ResponseResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/back/user")
public class SysUserController {

    private final SysUserService service;

    @TimeConsuming
    @LogController(description = "创建后台用户", logLevel = LogLevelConstant.NOTICE, method = "/create")
    @PostMapping("/create")
    public ResponseEntity<ResponseResultVO> createUser(@Valid @RequestBody SysUser sysUser) {
        log.info("/back/user = start create back user : [{}]", sysUser.toString());

        return new ResponseEntity(HttpStatus.OK);
    }
}
