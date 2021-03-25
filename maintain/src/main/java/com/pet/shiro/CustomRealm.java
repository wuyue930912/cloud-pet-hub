package com.pet.shiro;

import com.pet.dto.SysUsersDTO;
import com.pet.po.SysRights;
import com.pet.po.SysRoles;
import com.pet.service.LoginService;
import com.pet.utils.StringUtil;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Objects;

@AllArgsConstructor
public class CustomRealm extends AuthorizingRealm {

    private final LoginService loginService;

    /**
     * @MethodName doGetAuthorizationInfo
     * @Description 权限配置类
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        SysUsersDTO user = loginService.getUserInfo(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (SysRoles role : user.getSysRoleList()) {
            simpleAuthorizationInfo.addRole(role.getRoleName());
        }
        for (SysRights rights : user.getSysRightList()) {
            simpleAuthorizationInfo.addStringPermission(rights.getRightsName());
        }
        return simpleAuthorizationInfo;
    }

    /**
     * @MethodName doGetAuthenticationInfo
     * @Description 认证配置类
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtil.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        SysUsersDTO user = loginService.getUserInfo(name);
        if (Objects.isNull(user)) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            ByteSource credentialsSalt = ByteSource.Util.bytes(name);
            return new SimpleAuthenticationInfo(name, user.getUserPwd(),credentialsSalt, getName());
        }
    }
}
