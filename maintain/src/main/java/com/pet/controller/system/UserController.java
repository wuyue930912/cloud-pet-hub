package com.pet.controller.system;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.LogLevelConstant;
import com.pet.convert.SysUserConvert;
import com.pet.service.system.UserService;
import com.pet.vo.system.AddUserVO;
import com.pet.vo.ResponseResultVO;
import io.swagger.annotations.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "用户管理模块", tags = "提供用户管理服务")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /**
     * 11.8 EXAMPLE LogController annotation and publisher
     *
     * @param vo 新增用户VO
     * @return String
     */
    @PostMapping("/add")
    @LogController(description = "新增用户", logLevel = LogLevelConstant.NOTICE, method = "addUser")
    @TimeConsuming
    @ApiOperation(value = "新增用户", notes = "新增用户, 用户名密码必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "用户信息", required = true, dataType = "AddUserVO"),
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "处理成功"),
            @ApiResponse(code = 2, message = "用户名重复"),
            @ApiResponse(code = 3, message = "用户名不能为空"),
            @ApiResponse(code = 3, message = "密码不能为空"),
            @ApiResponse(code = 3, message = "手机号应为11位数字"),
    })
    public ResponseEntity<ResponseResultVO<String>> addUser(@RequestBody @Validated AddUserVO vo) {
        log.info("start add user, username : {} , pwd : {}", vo.getUserName(), vo.getUserPwd());
        return ResponseEntity.ok(userService.add(SysUserConvert.INSTANCE.vo2dto(vo)));
    }


}
