package com.pet.service;

import com.pet.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    public ResponseEntity<String> login(LoginVO vo) {

        return new ResponseEntity<>(vo.getUserName(), HttpStatus.OK);
    }
}
