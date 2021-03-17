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

    //被删除的用户们
    private List<String> userNames;
    //查询的用户们
    private List<SysUsers> sysUserList;
}
