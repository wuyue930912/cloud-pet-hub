package com.pet.service;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.dao.SysUserRoleDao;
import com.pet.dao.SysUsersDao;
import com.pet.dto.SysUsersDTO;
import com.pet.po.SysUserRole;
import com.pet.po.SysUsers;
import com.pet.utils.PasswordUtil;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SysUsersService {

    private final SysUsersDao usersDao;

    private final SysUserRoleDao userRoleDao;

    @Transactional
    public Optional<ResponseResultVO> createUser(SysUsersVo sysUsersVo) {
        // 获取参数
        String userName = sysUsersVo.getUserName();
        String userPwd = sysUsersVo.getUserPwd();
        String validPwd = sysUsersVo.getValidPwd();
        String userEmail = sysUsersVo.getUserEmail();
        String userPhone = sysUsersVo.getUserPhone();

        // 重名校验
        Optional<SysUsers> optional = usersDao.findByUserName(userName);
        if (optional.isPresent()) {
            return Optional.of(ResponseResultVO.builder()
                    .data(optional.get().getUserName())
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.USER_ALREADY_EXIST)
                    .build());
        }

        // 密码校验
        if (!validPwd.equals(userPwd)) {
            return Optional.of(ResponseResultVO.builder()
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.USER_VALID_PWD_USER_PWD_NOT_EQUALS)
                    .build());
        }

        // 保存数据库
        SysUsers users = new SysUsers();
        users.setUserName(userName);
        users.setUserEmail(userEmail);
        users.setUserPwd(PasswordUtil.encodePassword(userPwd));
        users.setUserPhone(userPhone);
        usersDao.save(users);

        SysUsersDTO usersDTO = new SysUsersDTO();
        usersDTO.setUserName(userName);
        usersDTO.setUserPwd(userPwd);
        usersDTO.setUserEmail(userEmail);
        usersDTO.setUserPhone(userPhone);

        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(usersDTO)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }

    @Transactional
    public Optional<String> deleteUsers(List<String> userId) {
        try {
            usersDao.deleteIn(userId);
            return Optional.of("success");
        } catch (Exception e) {
            return Optional.empty();
        }
    }


    public SysUsersDTO findUsers(List<String> userId) {
        SysUsersDTO usersDTO = new SysUsersDTO();
        List<SysUsers> usersList = new ArrayList<>();
        for (String uId : userId) {
            SysUsers sysUsers = usersDao.findUsers(uId);
            usersList.add(sysUsers);
        }
        usersDTO.setSysUserList(usersList);
        return usersDTO;

    }

    public SysUsersDTO editsUser(SysUsersVo sysUsersVo) {
        SysUsersDTO usersDTO = new SysUsersDTO();
        String UserId = sysUsersVo.getUserId();
        SysUsers users = usersDao.findUsers(UserId);
        if (users == null) {
            return null;
        }
        users.setUserName(sysUsersVo.getUserName());
        users.setUserPwd(sysUsersVo.getUserPwd());
        users.setUserPhone(sysUsersVo.getUserPhone());
        users.setUserEmail(sysUsersVo.getUserEmail());
        usersDao.save(users);

        usersDTO.setUserName(users.getUserName());
        usersDTO.setUserPwd(users.getUserPwd());
        usersDTO.setUserEmail(users.getUserEmail());
        usersDTO.setUserPhone(users.getUserPhone());
        return usersDTO;
    }

    public Optional<String> assignRolesToUsers(String userId, List<String> roleId) {
        SysUsers users = usersDao.findUsers(userId);
        if(users == null){
            return Optional.empty();
        }

        roleId.forEach(rId -> {
           SysUserRole userRole =  new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(rId);
            userRoleDao.save(userRole);
        });

        return Optional.of("success");

    }
}
