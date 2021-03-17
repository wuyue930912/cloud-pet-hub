package com.pet.dto;

import com.pet.po.SysUsers;
import lombok.Data;

import java.util.List;

@Data
public class SysUsersDTO {

    private String userName;
    private String userPwd;
    private String userPhone;
    private String userEmail;

    //查询的用户们
    private List<SysUsers> sysUserList;
}
