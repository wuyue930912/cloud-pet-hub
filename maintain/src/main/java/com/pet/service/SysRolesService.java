package com.pet.service;

import com.pet.dao.SysRolesDao;
import com.pet.dto.SysRolesDTO;
import com.pet.vo.SysRoleVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SysRolesService {

    private final SysRolesDao rolesDao;

    public SysRolesDTO createRole(SysRoleVo sysRoleVo) {

        return null;
    }
}
