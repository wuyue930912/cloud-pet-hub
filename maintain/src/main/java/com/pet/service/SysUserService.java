package com.pet.service;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.dao.SysUserDao;
import com.pet.entity.SysUser;
import com.pet.vo.ResponseResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SysUserService {
    private final SysUserDao sysUserDao;

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<ResponseResult> create(SysUser sysUser) {
        Optional<SysUser> optional = sysUserDao.findByUserName(sysUser.getUserName());
        if (optional.isPresent()) {
            return new ResponseEntity<>(ResponseResult.builder()
                    .code(ErrorCodeConstant.USER_ALREADY_EXIST)
                    .msg(ErrorMsgConstant.USER_ALREADY_EXIST)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        SysUser saveUser = sysUserDao.save(sysUser);

        return new ResponseEntity<>(ResponseResult.builder()
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .data(saveUser.getId())
                .build(), HttpStatus.OK);
    }
}
