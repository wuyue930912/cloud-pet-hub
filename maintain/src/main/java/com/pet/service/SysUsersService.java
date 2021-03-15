package com.pet.service;

import com.pet.dao.SysUsersDao;
import com.pet.dto.SysUsersDTO;
import com.pet.po.SysUsers;
import com.pet.utils.PasswordUtil;
import com.pet.vo.SysUsersVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SysUsersService {

    private final SysUsersDao usersDao;

    @Transactional
    public Optional<SysUsersDTO> createUser(SysUsersVo sysUsersVo) {
        String userName = sysUsersVo.getUserName();
        String userPwd = sysUsersVo.getUserPwd();
        String userEmail = sysUsersVo.getUserEmail();
        String userPhone = sysUsersVo.getUserPhone();

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

        return Optional.of(usersDTO);
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
}
