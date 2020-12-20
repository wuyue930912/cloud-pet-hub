package com.pet.controller;

import com.pet.service.LoginService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/back")
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

}
