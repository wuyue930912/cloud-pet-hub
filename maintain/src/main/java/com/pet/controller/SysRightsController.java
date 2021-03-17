package com.pet.controller;

import com.pet.service.SysRightsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/sysRights")
@AllArgsConstructor
public class SysRightsController {

    private final SysRightsService rightsService;
}
