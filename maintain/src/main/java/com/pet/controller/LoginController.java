package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.dto.SysUsersDTO;
import com.pet.service.LoginService;
import com.pet.utils.ControllerUtil;
import com.pet.vo.LoginVO;
import com.pet.vo.ResponseResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Slf4j
@RestController
@RequestMapping("/api/v2/login")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/findUser")
    @LogController(description = "查询该用户的所有角色和权限", method = "/findUser")
    @TimeConsuming
    public SysUsersDTO findUser(@RequestParam String userId) {
        log.info("LoginUserManager = start find userId [{}] ", userId);
        return loginService.findUser(userId);
    }

    @PostMapping("/loginUser")
    @LogController(description = "用户登录", method = "/loginUser")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> loginUser(@Validated @RequestBody LoginVO vo, HttpSession session) {
        log.info("LoginUserManager = start login userName [{}] ", vo.getUserName());
        return ResponseEntity.ok(loginService.login(vo, session).orElseGet(ControllerUtil::getErrorResultVO));
    }
}
