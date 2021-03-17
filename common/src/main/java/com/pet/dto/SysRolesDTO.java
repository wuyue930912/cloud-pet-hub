package com.pet.dto;

import com.pet.po.SysRoles;
import lombok.Data;

import java.util.List;

@Data
public class SysRolesDTO {

    private String roleName;
    private String roleDescribe;

    private List<SysRoles> rolesList;
}
