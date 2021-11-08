package com.pet.controller.system;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.LogLevelConstant;
import com.pet.convert.UsersConvert;
import com.pet.service.system.UserService;
import com.pet.vo.system.AddUserVO;
import com.pet.vo.ResponseResultVO;
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
    public ResponseEntity<ResponseResultVO<String>> addUser(@RequestBody @Validated AddUserVO vo) {
        log.info("start add user, username : {} , pwd : {}", vo.getUserName(), vo.getUserPwd());
        return ResponseEntity.ok(userService.add(UsersConvert.INSTANCE.vo2dto(vo)));
    }


}
