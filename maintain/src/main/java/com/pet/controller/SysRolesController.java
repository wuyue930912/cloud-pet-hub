package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.dto.SysRolesDTO;
import com.pet.service.SysRolesService;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysRoleVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 基础接口（全都要考虑到权限）
 * 1.创建角色（角色名（判重）/角色描述）
 * 2.修改角色（参考创建角色））
 * 3.删除角色（删除单个/多个,物理删除（有用户关联了此角色，就删除不了））
 * 4.查询角色（查询单个/多个）
 */
@Slf4j
@RestController
@RequestMapping("/sysRoles")
@AllArgsConstructor
public class SysRolesController {

    private final SysRolesService rolesService;

    @PostMapping("/createRoles")
    @LogController(description = "创建角色", method = "/createRoles")
    @TimeConsuming
    public ResponseResultVO createRoles(@Valid @RequestBody SysRoleVo sysRoleVo) {
        log.info("roleManager = start create role [{}] ,roleDescribe [{}]", sysRoleVo.getRoleName(),sysRoleVo.getRoleDescribe());

        return ResponseResultVO.builder()
                .data(rolesService.createRoles(sysRoleVo).orElse(new SysRolesDTO()))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("角色创建成功")
                .build();
    }

    @PostMapping("/deleteRoles")
    @LogController(description = "删除角色", method = "/deleteRoles")
    @TimeConsuming
    public ResponseResultVO deleteRoles(@RequestBody List<String> roleId) {
        log.info("roleManager = start delete roleIdLists [{}] ", roleId);
        return ResponseResultVO.builder()
                .data(rolesService.deleteRoles(roleId).orElse("error"))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("角色删除成功")
                .build();
    }

    @PostMapping("/editsRoles")
    @LogController(description = "修改角色", method = "/editsRoles")
    @TimeConsuming
    public ResponseResultVO editsRoles(@Valid @RequestBody SysRoleVo sysRoleVo) {
        log.info("roleManager = start edits roles [{}] ", sysRoleVo);
        return ResponseResultVO.builder()
                .data(rolesService.editsRoles(sysRoleVo))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户修改成功")
                .build();
    }


    @PostMapping("/findRoles")
    @LogController(description = "查询角色", method = "/findRoles")
    @TimeConsuming
    public ResponseResultVO findRoles(@RequestBody List<String> roleId) {
        log.info("roleManager = start find userId [{}] ", roleId);
        return ResponseResultVO.builder()
                .data(rolesService.findRoles(roleId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户查询成功")
                .build();
    }

    //给角色分配权限
    @PostMapping("/assignRightsToRoles")
    @LogController(description = "给角色分配权限", method = "/assignRightsToRoles")
    @TimeConsuming
    public ResponseResultVO assignRightsToRoles(@RequestBody SysRoleVo sysRoleVo) {
        String roleId = sysRoleVo.getRoleId();
        List<String> rightsIds = sysRoleVo.getRightsId();
        log.info("UserManager = start assignRightsToRoles roleId [{}] rightsIds [{}]", roleId,rightsIds);
        return ResponseResultVO.builder()
                .data(rolesService.assignRightsToRoles(roleId,rightsIds))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("用户分配角色成功")
                .build();
    }
}
