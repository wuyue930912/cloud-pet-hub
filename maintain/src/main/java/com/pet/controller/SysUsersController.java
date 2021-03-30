package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.service.SysUsersService;
import com.pet.utils.ControllerUtil;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysUsersVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/v2/users")
@AllArgsConstructor
public class SysUsersController {

    private final SysUsersService usersService;

    @PostMapping("/createUser")
    @LogController(description = "创建用户", method = "/createUser")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> createUser(@Valid @RequestBody SysUsersVO sysUsersVo) {
        log.info("UserManager = start create user [{}] pwd [{}]", sysUsersVo.getUserName(), sysUsersVo.getUserPwd());
        return ResponseEntity.ok(usersService.createUser(sysUsersVo).orElseGet(ControllerUtil::getErrorResultVO));
    }

    @DeleteMapping("/deleteUserById/{userId}")
    @LogController(description = "删除用户", method = "/deleteUserById")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> deleteUsers(@PathVariable String userId) {
        log.info("UserManager = start delete userIdLists [{}] ", userId);
        return ResponseEntity.ok(usersService.deleteUserById(userId).orElseGet(ControllerUtil::getErrorResultVO));
    }

    @PostMapping("/deleteUsers")
    @LogController(description = "删除用户", method = "/deleteUsers")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> deleteUsers(@RequestBody List<String> userId) {
        log.info("UserManager = start delete userIdLists [{}] ", userId);
        return ResponseEntity.ok(usersService.deleteUsers(userId).orElseGet(ControllerUtil::getErrorResultVO));
    }

    @PostMapping("/editsUser")
    @LogController(description = "修改用户", method = "/editsUser")
    @TimeConsuming
    public ResponseResultVO editsUser(@Valid @RequestBody SysUsersVO sysUsersVo) {
        log.info("UserManager = start edits user [{}] ", sysUsersVo);
        return ResponseResultVO.builder()
                .data(usersService.editsUser(sysUsersVo))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户修改成功")
                .build();
    }


    //tips 1 查询所有用户时需要 需查询出总记录数和相应角色
    @PostMapping("/findUsers")
    @LogController(description = "查询用户", method = "/findUsers")
    @TimeConsuming
    public ResponseResultVO findUsers(@RequestParam String userId, int pageIndex, int pageSize) {
        log.info("UserManager = start find userId [{}] ", userId);
        return ResponseResultVO.builder()
                .data(usersService.findUsers(pageIndex,pageSize))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户查询成功")
                .build();
    }

    //给用户分配角色
    @PostMapping("/assignRolesToUsers")
    @LogController(description = "给用户分配角色", method = "/assignRolesToUsers")
    @TimeConsuming
    public ResponseResultVO assignRolesToUsers(@RequestBody SysUsersVO sysUsersVo) {
        String userId = sysUsersVo.getUserId();
        List<String> roleId = sysUsersVo.getRoleId();
        log.info("UserManager = start assignRolesToUsers userId [{}] roleId [{}]", userId,roleId);
        return ResponseResultVO.builder()
                .data(usersService.assignRolesToUsers(userId,roleId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户分配角色成功")
                .build();
    }

    //tips 0 查询单个用户时需要带出该用户相关角色和所有权限
    @PostMapping("/findUsersByUserId")
    @LogController(description = "查询用户", method = "/findUsersByUserId")
    @TimeConsuming
    public ResponseResultVO findUsersByUserId(@RequestParam String userId) {
        log.info("UserManager = start find userId [{}] ", userId);
        return ResponseResultVO.builder()
                .data(usersService.findUsersByUserId(userId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户查询成功")
                .build();
    }
}
