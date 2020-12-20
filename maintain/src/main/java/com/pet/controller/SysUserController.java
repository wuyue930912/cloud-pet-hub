package com.pet.controller;

import com.pet.entity.SysUser;
import com.pet.service.SysUserService;
import com.pet.vo.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/create")
    public ResponseEntity<ResponseResult> createUser(@Valid @RequestBody SysUser sysUser){
        return service.create(sysUser);
    }
}
