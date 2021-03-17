package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.dto.SysUsersDTO;
import com.pet.service.SysUsersService;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 基础接口（全都要考虑到权限）
 * 1.创建用户（用户名（判重）/密码（加密）/手机号（正则校验）/邮箱（校验））
 * 2.修改用户（参考创建用户）/（修改是可以拓展增加一些不必要条件进去）
 * 3.删除用户（删除单个/多个,逻辑删除isDelete=true）
 * 4.查询用户（查询单个/多个）
 */
@Slf4j
@RestController
@RequestMapping("/sysUsers")
@AllArgsConstructor
public class SysUsersController {

    private final SysUsersService usersService;

    @PostMapping("/createUser")
    @LogController(description = "创建用户", method = "/createUser")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> createUser(@Valid @RequestBody SysUsersVo sysUsersVo) {
        log.info("UserManager = start create user [{}] pwd [{}]", sysUsersVo.getUserName(), sysUsersVo.getUserPwd());

        return ResponseEntity.ok(usersService.createUser(sysUsersVo).orElse(ResponseResultVO.builder()
                .code(ErrorCodeConstant.SYSTEM_ERROR)
                .msg(ErrorMsgConstant.SYSTEM_ERROR)
                .build()));
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
    @LogController(description = "查询用户", method = "/findUsers")
    @TimeConsuming
    public ResponseResultVO findUsers(@RequestBody List<String> userId) {
        log.info("UserManager = start find userId [{}] ", userId);
        return ResponseResultVO.builder()
                .data(usersService.findUsers(userId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户查询成功")
                .build();
    }

    //给用户分配角色
    @PostMapping("/assignRolesToUsers")
    @LogController(description = "给用户分配角色", method = "/assignRolesToUsers")
    @TimeConsuming
    public ResponseResultVO assignRolesToUsers(@RequestBody SysUsersVo sysUsersVo) {
        String userId = sysUsersVo.getUserId();
        List<String> roleId = sysUsersVo.getRoleId();
        log.info("UserManager = start assignRolesToUsers userId [{}] roleId [{}]", userId,roleId);
        return ResponseResultVO.builder()
                .data(usersService.assignRolesToUsers(userId,roleId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户分配角色成功")
                .build();
    }
}
