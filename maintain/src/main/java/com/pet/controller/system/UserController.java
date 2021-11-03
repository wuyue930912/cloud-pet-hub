package com.pet.controller.system;

import com.pet.annotation.TimeConsuming;
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

    @PostMapping("/add")
    @TimeConsuming
    public ResponseEntity<ResponseResultVO<String>> addUser(@RequestBody @Validated AddUserVO vo){
        log.info("start add user [{}]", vo);
        return ResponseEntity.ok(userService.add(UsersConvert.INSTANCE.vo2dto(vo)));
    }


}
