package com.pet.service;

import com.pet.dao.SysUserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class LoginService {

    private final ApplicationEventPublisher publisher;

    private final SysUserDao sysUserDao;

}
