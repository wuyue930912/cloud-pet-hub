package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.dto.SysUsersDTO;
import com.pet.service.SysUsersService;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/sysUsers")
@AllArgsConstructor
public class SysUsersController {

    private final SysUsersService usersService;

    @PostMapping("/createUser")
    @LogController(description = "创建用户", method = "/createUser")
    @TimeConsuming
    public ResponseResultVO createUser(@Valid @RequestBody SysUsersVo sysUsersVo) {
        log.info("UserManager = start create user [{}] pwd [{}]", sysUsersVo.getUserName(), sysUsersVo.getUserPwd());
        return ResponseResultVO.builder()
                .data(usersService.createUser(sysUsersVo).orElse(new SysUsersDTO()))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户创建成功")
                .build();
    }

    @PostMapping("/deleteUsers")
    @LogController(description = "删除用户", method = "/deleteUsers")
    @TimeConsuming
    public ResponseResultVO deleteUsers(@RequestBody List<String> userId) {
        log.info("UserManager = start delete userIdLists [{}] ", userId);
        return ResponseResultVO.builder()
                .data(usersService.deleteUsers(userId).orElse("error"))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户删除成功")
                .build();
    }

    @PostMapping("/editsUser")
    @LogController(description = "修改用户", method = "/editsUser")
    @TimeConsuming
    public ResponseResultVO editsUser(@Valid @RequestBody SysUsersVo sysUsersVo) {
        log.info("UserManager = start edits user [{}] ", sysUsersVo);
        return ResponseResultVO.builder()
                .data(usersService.editsUser(sysUsersVo))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户修改成功")
                .build();
    }


    @PostMapping("/findUsers")
    @LogController(description = "修改用户", method = "/findUsers")
    @TimeConsuming
    public ResponseResultVO findUsers(@RequestBody List<String> userId) {
        log.info("UserManager = start find userId [{}] ", userId);
        return ResponseResultVO.builder()
                .data(usersService.findUsers(userId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户查询成功")
                .build();
    }
}
