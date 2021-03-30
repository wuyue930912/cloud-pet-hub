package com.pet.dto;

import com.pet.po.SysRights;
import com.pet.po.SysRoles;
import com.pet.po.SysUsers;
import lombok.Data;

import java.util.List;

@Data
public class SysUsersDTO {

    private String userName;
    private String userPwd;
    private String userPhone;
    private String userEmail;
    //总记录数
    private Integer pageCount;

    //查询的用户们
    private List<SysUsers> sysUserList;
    //该用户的所有角色
    private List<SysRoles> sysRoleList;
    //该用户的所以权限
    private List<SysRights> sysRightList;
}
