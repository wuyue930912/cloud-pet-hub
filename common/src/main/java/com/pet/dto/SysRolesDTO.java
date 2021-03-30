package com.pet.dto;

import com.pet.po.SysRoles;
import lombok.Data;

import java.util.List;

@Data
public class SysRolesDTO {
    private String roleName;
    private String roleDescribe;
    //总记录数
    private Integer pageCount;
    private List<SysRoles> rolesList;
}
