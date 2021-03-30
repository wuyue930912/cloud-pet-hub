package com.pet.service;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.convert.UsersConvert;
import com.pet.dao.SysUserRoleDao;
import com.pet.dao.SysUsersDao;
import com.pet.dto.SysUsersDTO;
import com.pet.po.SysUserRole;
import com.pet.po.SysUsers;
import com.pet.utils.ControllerUtil;
import com.pet.utils.PasswordUtil;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class SysUsersService {

    private final SysUsersDao usersDao;

    private final SysUserRoleDao userRoleDao;

    @Transactional
    public Optional<ResponseResultVO> createUser(SysUsersVo sysUsersVo) {
        // 重名校验
        Optional<SysUsers> optional = usersDao.findByUserName(sysUsersVo.getUserName());
        if (optional.isPresent()) {
            return Optional.of(ControllerUtil.getErrorResultVO(ErrorCodeConstant.VALID_ERROR, ErrorMsgConstant.USER_ALREADY_EXIST, optional.get().getUserName()));
        }

        // 密码校验
        if (!sysUsersVo.getValidPwd().equals(sysUsersVo.getUserPwd())) {
            return Optional.of(ControllerUtil.getErrorResultVO(ErrorCodeConstant.VALID_ERROR, ErrorMsgConstant.USER_VALID_PWD_USER_PWD_NOT_EQUALS));
        }

        SysUsers sysUsers = UsersConvert.INSTANCE.vo2po(sysUsersVo);
        sysUsers.setUserPwd(PasswordUtil.encodePassword(sysUsersVo.getUserPwd()));

        // 保存数据库 构造结果集
        return Optional.of(ControllerUtil.getSuccessResultVO(UsersConvert.INSTANCE.po2dto(usersDao.save(sysUsers))));
    }

    @Transactional
    public Optional<ResponseResultVO> deleteUsers(List<String> userId) {
        try {
            //修改为逻辑删除
            userId.forEach(usersDao::logicDelete);
            return Optional.of(ControllerUtil.getSuccessResultVO());
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public SysUsersDTO findUsers(String userId, int pageIndex, int pageSize) {
        SysUsersDTO usersDTO = new SysUsersDTO();
        if (userId == null || userId.equals("")) {
            usersDTO.setSysUserList(usersDao.findAllUsers(pageIndex, pageSize));
        } else {
            SysUsers sysUsers = usersDao.findUsers(userId);
            usersDTO = UsersConvert.INSTANCE.po2dto(sysUsers);
        }
        return usersDTO;

    }

    public Optional<ResponseResultVO> editsUser(SysUsersVo sysUsersVo) {
        String userId = sysUsersVo.getUserId();
        SysUsers users = usersDao.findUsers(userId);
        if (users == null) {
            return Optional.empty();
        }
        // 重名校验(此次要修改的名字判断是否重复了，需排除自身)
        Optional<SysUsers> optional = usersDao.findByUserName(sysUsersVo.getUserName());
        if (optional.isPresent()) {
            return Optional.of(ResponseResultVO.builder()
                    .data(optional.get().getUserName())
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.USER_ALREADY_EXIST)
                    .build());
        }
        String userPwd = sysUsersVo.getUserPwd();
        SysUsers editUsers = UsersConvert.INSTANCE.vo2po(sysUsersVo);
        editUsers.setUserPwd(PasswordUtil.encodePassword(userPwd));
        //保存修改后的用户
        SysUsers editUser = usersDao.save(editUsers);

        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(UsersConvert.INSTANCE.po2dto(editUser))
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }

    public Optional<String> assignRolesToUsers(String userId, List<String> roleId) {
        SysUsers users = usersDao.findUsers(userId);
        if (users == null) {
            return Optional.empty();
        }
        //忽略了重复赋予角色
        List<SysUserRole> userRoleList = userRoleDao.findUserIsRoleId(userId, roleId);
        if (!userRoleList.isEmpty()) {
            return Optional.of("该用户已经有此角色,请重新分配");
        }
        roleId.forEach(rId -> {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(rId);
            userRoleDao.save(userRole);
        });

        return Optional.of("success");

    }
}
