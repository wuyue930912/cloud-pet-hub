package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.constant.LogLevelConstant;
import com.pet.convert.SysUserMapper;
import com.pet.dto.SysUserDTO;
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
import java.util.Optional;

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
        Optional<SysUserDTO> optional = service.create(sysUser);
        return optional.map(sysUserDTO -> new ResponseEntity<>(ResponseResultVO.builder()
                .data(SysUserMapper.INSTANCE.dto2vo(sysUserDTO))
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(ResponseResultVO.builder()
                .code(ErrorCodeConstant.USER_ALREADY_EXIST)
                .msg(ErrorMsgConstant.USER_ALREADY_EXIST)
                .build(), HttpStatus.BAD_REQUEST));
    }
}
