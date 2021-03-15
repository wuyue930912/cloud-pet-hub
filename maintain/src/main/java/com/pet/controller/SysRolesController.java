package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.service.SysRolesService;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysRoleVo;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/sysRoles")
@AllArgsConstructor
public class SysRolesController {

    private final SysRolesService rolesService;

    @PostMapping("/createRole")
    @LogController(description = "创建角色", method = "/createRole")
    @TimeConsuming
    public ResponseResultVO createUser(@Valid @RequestBody SysRoleVo sysRoleVo) {
        log.info("roleManager = start create role [{}] ,roleDescribe [{}]", sysRoleVo.getRoleName(),sysRoleVo.getRoleDescribe());

        return ResponseResultVO.builder()
                .data(rolesService.createRole(sysRoleVo))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("角色创建成功")
                .build();
    }


}
