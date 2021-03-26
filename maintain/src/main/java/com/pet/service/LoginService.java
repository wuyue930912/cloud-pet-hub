package com.pet.service;

import com.pet.constant.ErrorMsgConstant;
import com.pet.dao.*;
import com.pet.dto.SysUsersDTO;
import com.pet.po.*;
import com.pet.utils.ControllerUtil;
import com.pet.vo.LoginVO;
import com.pet.vo.ResponseResultVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pet.constant.ErrorCodeConstant.CHECK_ERROR;

@Service
@Slf4j
@AllArgsConstructor
public class LoginService {

    private final ApplicationEventPublisher publisher;

    private final SysUsersDao sysUsersDao;

    private final SysUserRoleDao userRoleDao;

    private final SysRoleRightsDao roleRightsDao;

    private final SysRolesDao rolesDao;

    private final SysRightsDao rightsDao;

    public SysUsersDTO getUserInfo(String getUserName) {
        return findUser(getUserName);
    }

    //获取该用户的所有角色和权限
    public SysUsersDTO findUser(String userName) {

        //查询用户的所有角色
        List<SysRoles> rolesList = new ArrayList<>();
        //查询用户的所有权限
        List<SysRights> rightsList = new ArrayList<>();

        Optional<SysUsers> optional = sysUsersDao.findByUserName(userName);
        if (!optional.isPresent()) {
            return null;
        } else {
            //查询userRole表获取相对应的所有角色对象
            List<SysUserRole> userRoleList = userRoleDao.findRoleIdsByUserId(optional.get().getUserId());
            userRoleList.forEach(userRole -> {
                SysRoles roles = rolesDao.findRoles(userRole.getRoleId());
                rolesList.add(roles);
                //查询roleRight表获取相对应的所有权限对象
                List<SysRoleRights> roleRightsList = roleRightsDao.findRightIdsByRoleId(userRole.getRoleId());
                roleRightsList.forEach(roleRights -> {
                    SysRights rights = rightsDao.findRights(roleRights.getRightsId());
                    rightsList.add(rights);
                });
            });

            SysUsersDTO usersDTO = new SysUsersDTO();
            usersDTO.setUserName(optional.get().getUserName());
            usersDTO.setUserPwd(optional.get().getUserPwd());
            usersDTO.setSysRoleList(rolesList);
            usersDTO.setSysRightList(rightsList);
            return usersDTO;
        }
    }

    public Optional<ResponseResultVO> login(LoginVO vo, HttpSession session) {
        sysUsersDao.findByUserName(vo.getUserName()).ifPresent(dbUser -> session.setAttribute("user", dbUser));
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(vo.getUserName(), vo.getPassword()));
        } catch (UnknownAccountException e) {
            log.error("UserService = user [{}] not exist！", vo.getUserName());
            return Optional.of(ControllerUtil.getErrorResultVO(CHECK_ERROR, ErrorMsgConstant.USER_NOT_EXIST_ERROR));
        } catch (AuthenticationException e) {
            log.error("UserService = user pwd not match！");
            return Optional.of(ControllerUtil.getErrorResultVO(CHECK_ERROR, ErrorMsgConstant.USER_NAME_OR_PWD_ERROR));
        } catch (AuthorizationException e) {
            log.error("UserService = user insufficient authority！");
            return Optional.of(ControllerUtil.getErrorResultVO(CHECK_ERROR, ErrorMsgConstant.USER_RIGHT_ERROR));
        }
        return Optional.of(ControllerUtil.getSuccessResultVO());
    }
}
