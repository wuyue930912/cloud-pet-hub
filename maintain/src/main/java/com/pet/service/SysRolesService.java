package com.pet.service;

import com.pet.dao.SysRoleRightsDao;
import com.pet.dao.SysRolesDao;
import com.pet.dto.SysRolesDTO;
import com.pet.po.SysRoleRights;
import com.pet.po.SysRoles;
import com.pet.vo.SysRoleVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SysRolesService {

    private final SysRolesDao rolesDao;
    private final SysRoleRightsDao roleRightsDao;

    public  Optional<SysRolesDTO> createRoles(SysRoleVo sysRoleVo) {
        String roleName = sysRoleVo.getRoleName();
        String roleDescribe = sysRoleVo.getRoleDescribe();

        SysRoles roles = new SysRoles();
        roles.setRoleName(roleName);
        roles.setRoleDescribe(roleDescribe);
        rolesDao.save(roles);

        SysRolesDTO rolesDTO = new SysRolesDTO();
        rolesDTO.setRoleName(roleName);
        rolesDTO.setRoleDescribe(roleDescribe);

        return Optional.of(rolesDTO);
    }

    public SysRolesDTO editsRoles(SysRoleVo sysRoleVo) {
        SysRolesDTO rolesDTO = new SysRolesDTO();
        String roleId = sysRoleVo.getRoleId();
        SysRoles roles = rolesDao.findRoles(roleId);
        if (roles == null) {
            return null;
        }
        roles.setRoleName(sysRoleVo.getRoleName());
        roles.setRoleDescribe(sysRoleVo.getRoleDescribe());
        rolesDao.save(roles);

        rolesDTO.setRoleName(roles.getRoleName());
        rolesDTO.setRoleDescribe(roles.getRoleDescribe());
        return rolesDTO;
    }

    public Object findRoles(List<String> roleId) {
        SysRolesDTO rolesDTO = new SysRolesDTO();
        List<SysRoles> rolesList = new ArrayList<>();
        for (String rId : roleId) {
            SysRoles sysRoles = rolesDao.findRoles(rId);
            rolesList.add(sysRoles);
        }
        rolesDTO.setRolesList(rolesList);
        return rolesDTO;
    }


    @Transactional
    public Optional<String> deleteRoles(List<String> roleId) {
        try {
            rolesDao.deleteIn(roleId);
            return Optional.of("success");
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Object assignRightsToRoles(String roleId, List<String> rightsIds) {

        SysRoles roles = rolesDao.findRoles(roleId);
        if(roles == null ){
            return Optional.empty();
        }

        rightsIds.forEach(rId -> {
            SysRoleRights roleRights =  new SysRoleRights();
            roleRights.setRoleId(roleId);
            roleRights.setRightsId(rId);
            roleRightsDao.save(roleRights);
        });

        return Optional.of("success");
    }
}
