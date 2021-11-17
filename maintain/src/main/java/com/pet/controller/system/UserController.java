package com.pet.controller.system;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorMsgConstant;
import com.pet.constant.LogLevelConstant;
import com.pet.convert.SysUserConvert;
import com.pet.service.system.UserService;
import com.pet.vo.PageParamVO;
import com.pet.vo.PageResultVO;
import com.pet.vo.system.AddUserVO;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.system.SearchUserResultVO;
import com.pet.vo.system.SearchUserVO;
import io.swagger.annotations.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "用户管理模块", tags = "提供用户管理服务")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // 接口列表
    private static final String ADD_USER = "新增用户";
    private static final String DEL_USER_BY_ID = "通过ID删除用户";
    private static final String SEARCH_USER_BY_PAGE = "分页查询用户";


    /**
     * 11.8 EXAMPLE LogController annotation and publisher
     *
     * @param vo 新增用户VO
     * @return String
     */
    @PostMapping("/add")
    @LogController(description = ADD_USER, logLevel = LogLevelConstant.NOTICE, method = "addUser")
    @TimeConsuming
    @ApiOperation(value = ADD_USER, notes = "新增用户, 用户名密码必填")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo", value = "用户信息", required = true, dataType = "AddUserVO"),
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = ErrorMsgConstant.SUCCESS),
            @ApiResponse(code = 2, message = ErrorMsgConstant.USER_NAME_ALREADY_EXIST),
            @ApiResponse(code = 3, message = ErrorMsgConstant.USER_NAME_NOT_NULL),
            @ApiResponse(code = 3, message = ErrorMsgConstant.USER_PWD_NOT_NULL),
            @ApiResponse(code = 3, message = ErrorMsgConstant.USER_PWD_NOT_MATCH),
            @ApiResponse(code = 3, message = ErrorMsgConstant.USER_EMAIL_NOT_MATCH)
    })
    public ResponseEntity<ResponseResultVO<String>> addUser(@RequestBody @Validated AddUserVO vo) {
        log.info("start add user, username : {} , pwd : {}", vo.getUserName(), vo.getUserPwd());
        return ResponseEntity.ok(userService.add(SysUserConvert.INSTANCE.vo2dto(vo)));
    }

    /**
     * 删除单个用户
     *
     * @return String
     */
    @DeleteMapping("/del/{userId}")
    @LogController(description = DEL_USER_BY_ID, logLevel = LogLevelConstant.NOTICE, method = "del")
    @TimeConsuming
    @ApiOperation(value = DEL_USER_BY_ID, notes = "删除用户:通过用户id进行用户删除")
    @ApiImplicitParam(name = "userId", value = "被删除用户id", required = true, dataType = "String")
    @ApiResponses({
            @ApiResponse(code = 1, message = ErrorMsgConstant.SUCCESS),
            @ApiResponse(code = 2, message = ErrorMsgConstant.USER_NOT_EXIST_ERROR)
    })
    public ResponseEntity<ResponseResultVO<String>> del(@PathVariable String userId) {
        log.info("start delete user, userId :{}", userId);
        return ResponseEntity.ok(userService.delUser(userId));
    }

    /**
     * 分页查询用户
     *
     * @return String
     */
    @PostMapping("/searchUser")
    @LogController(description = SEARCH_USER_BY_PAGE, logLevel = LogLevelConstant.NOTICE, method = "searchUser")
    @TimeConsuming
    @ApiOperation(value = SEARCH_USER_BY_PAGE, notes = "查询用户:通过SearchUserVO进行查询")
    @ApiImplicitParam(name = "pageParam", value = "被删除用户id", required = true, dataType = "PageParamVO")
    @ApiResponses({
            @ApiResponse(code = 1, message = ErrorMsgConstant.SUCCESS),
            @ApiResponse(code = 3, message = ErrorMsgConstant.PAGE_INDEX_NOT_NULL),
            @ApiResponse(code = 3, message = ErrorMsgConstant.PAGE_SIZE_NOT_NULL)
    })
    public ResponseEntity<ResponseResultVO<PageResultVO<List<SearchUserResultVO>>>> searchUser(@RequestBody @Validated PageParamVO<SearchUserVO> pageParam) {
        log.info("start search user, example :{}", pageParam.toString());
        return ResponseEntity.ok(userService.searchUser(pageParam));
    }

}
