package com.pet.service;

import com.pet.constant.ErrorMsgConstant;
import com.pet.convert.SysUserMapper;
import com.pet.dao.SysUserDao;
import com.pet.dto.SysUserDTO;
import com.pet.po.SysUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class SysUserService {

    private final SysUserDao sysUserDao;

    @Transactional(rollbackFor = Exception.class)
    public Optional<SysUserDTO> create(SysUser sysUser) {
        Optional<SysUser> optional = sysUserDao.findByUserName(sysUser.getUserName());
        if (optional.isPresent()) {
            log.info("/back/user = create back user error because : [{}]", ErrorMsgConstant.USER_ALREADY_EXIST);
            return Optional.empty();
        }

        SysUser saveUser = sysUserDao.save(sysUser);
        log.info("/back/user = create back user success");
        return Optional.ofNullable(SysUserMapper.INSTANCE.po2dto(saveUser));
    }
}
