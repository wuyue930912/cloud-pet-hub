package com.pet.controller;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorCodeConstant;
import com.pet.service.SysRightsService;
import com.pet.utils.ControllerUtil;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysRightsVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 1.创建权限（权限名（判重）/路径地址/路径标识）
 * 2.修改权限（参考创建权限）
 * 3.删除权限（删除单个/多个）
 * 4.查询权限（查询单个/多个）
 */
@Slf4j
@RestController
@RequestMapping("/api/v2/rights")
@AllArgsConstructor
public class SysRightsController {

    private final SysRightsService rightsService;

    @PostMapping("/findRights")
    @LogController(description = "查询权限", method = "/findRights")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> findRights() {
        log.info("UserManager = start find Rights ");
        return ResponseEntity.ok(rightsService.findRights().orElse(ControllerUtil.getErrorResultVO()));
    }

    //tips 5 需要增加上下级关系
    @PostMapping("/createRights")
    @LogController(description = "创建权限", method = "/createRights")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO> createRights(@Valid @RequestBody SysRightsVO sysRightsVo) {
        log.info("UserManager = start create rights name[{}] url [{}]", sysRightsVo.getRightsName(), sysRightsVo.getRightsUrl());
        return ResponseEntity.ok(rightsService.createRights(sysRightsVo).orElse(ControllerUtil.getErrorResultVO()));
    }

    //tips 6 判断是否有角色占用
    @PostMapping("/deleteRights")
    @LogController(description = "删除权限", method = "/deleteRights")
    @TimeConsuming
    public ResponseResultVO deleteRights(@RequestBody List<String> rightId) {
        log.info("UserManager = start delete rightIdLists [{}] ", rightId);
        return ResponseResultVO.builder()
                .data(rightsService.deleteRights(rightId).orElse("error"))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("权限删除成功")
                .build();
    }

    @PostMapping("/editsRights")
    @LogController(description = "修改权限", method = "/editsRights")
    @TimeConsuming
    public ResponseResultVO editsRights(@Valid @RequestBody SysRightsVO sysRightsVo) {
        log.info("UserManager = start edits right [{}] ", sysRightsVo);
        return ResponseResultVO.builder()
                .data(rightsService.editsRights(sysRightsVo))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("权限修改成功")
                .build();
    }

    @PostMapping("/findRightsByRightId")
    @LogController(description = "查询单个权限", method = "/findRightsByRightId")
    @TimeConsuming
    public ResponseResultVO findRightsByRightId(@RequestParam String rightId) {
        log.info("UserManager = start find rightId [{}] ", rightId);
        return ResponseResultVO.builder()
                .data(rightsService.findRightsByRightId(rightId))
                .code(ErrorCodeConstant.SUCCESS)
                .msg("单个权限查询成功")
                .build();
    }
}
