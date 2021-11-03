package com.pet.service.system.impl;

import com.pet.constant.ErrorCodeConstant;
import com.pet.convert.UsersConvert;
import com.pet.dao.SysUserDao;
import com.pet.dto.AddUserDTO;
import com.pet.enums.ErrorMsgEnum;
import com.pet.po.SysUser;
import com.pet.service.system.UserService;
import com.pet.utils.PasswordUtil;
import com.pet.vo.ResponseResultVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SysUserDao sysUsersDao;

    @Override
    @Transactional
    public ResponseResultVO<String> add(AddUserDTO dto) {
        // 转换成po
        SysUser user = UsersConvert.INSTANCE.dto2po(dto);

        // 密码加密
        user.setUserPwd(PasswordUtil.encodePassword(dto.getUserPwd()));

        // 入库
        sysUsersDao.save(user);

        // 返回结果
        return ResponseResultVO.<String>builder().code(ErrorCodeConstant.SUCCESS).msg(ErrorMsgEnum.SUCCESS.getMsg()).build();
    }
}