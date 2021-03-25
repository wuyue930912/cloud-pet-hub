package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.dto.SysUsersDTO;
import com.pet.service.LoginService;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/loginBack")
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
    public String loginUser(@RequestBody SysUsersVo usersVo) {
        if (usersVo.getUserName().equals("")
                || usersVo.getUserPwd().equals("")) {
            return "请输入用户名和密码!";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(usersVo.getUserName(), usersVo.getUserPwd());
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        return "login success";
    }
}
