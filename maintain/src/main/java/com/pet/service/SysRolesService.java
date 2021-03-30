package com.pet.service;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.dao.SysRoleRightsDao;
import com.pet.dao.SysRolesDao;
import com.pet.dao.SysUserRoleDao;
import com.pet.dto.SysRolesDTO;
import com.pet.po.SysRoleRights;
import com.pet.po.SysRoles;
import com.pet.po.SysUserRole;
import com.pet.vo.ResponseResultVO;
import com.pet.vo.SysRoleVO;
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
    private final SysUserRoleDao userRoleDao;

    public Optional<ResponseResultVO> createRoles(SysRoleVO sysRoleVo) {
        String roleName = sysRoleVo.getRoleName();
        String roleDescribe = sysRoleVo.getRoleDescribe();

        // 重名校验
        Optional<SysRoles> optional = rolesDao.findByRoleName(roleName);
        if (optional.isPresent()) {
            return Optional.of(ResponseResultVO.builder()
                    .data(optional.get().getRoleName())
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.ROLE_ALREADY_EXIST)
                    .build());
        }

        SysRoles roles = new SysRoles();
        roles.setRoleName(roleName);
        roles.setRoleDescribe(roleDescribe);
        rolesDao.save(roles);

        SysRolesDTO rolesDTO = new SysRolesDTO();
        rolesDTO.setRoleName(roleName);
        rolesDTO.setRoleDescribe(roleDescribe);

        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(rolesDTO)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }

    public Optional<ResponseResultVO> editsRoles(SysRoleVO sysRoleVo) {
        SysRolesDTO rolesDTO = new SysRolesDTO();
        String roleId = sysRoleVo.getRoleId();
        SysRoles roles = rolesDao.findRoles(roleId);
        if (roles == null) {
            return Optional.empty();
        }
        // 重名校验(此次要修改的名字判断是否重复了，需排除自身)
        Optional<SysRoles> optional = rolesDao.findByRoleName(sysRoleVo.getRoleName());
        if (optional.isPresent()) {
            return Optional.of(ResponseResultVO.builder()
                    .data(optional.get().getRoleName())
                    .code(ErrorCodeConstant.VALID_ERROR)
                    .msg(ErrorMsgConstant.ROLE_NAME_MODIFY_CONSISTENT)
                    .build());
        }
        roles.setRoleName(sysRoleVo.getRoleName());
        roles.setRoleDescribe(sysRoleVo.getRoleDescribe());
        rolesDao.save(roles);

        rolesDTO.setRoleName(roles.getRoleName());
        rolesDTO.setRoleDescribe(roles.getRoleDescribe());
        // 构造结果集
        return Optional.of(ResponseResultVO.builder()
                .data(rolesDTO)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build());
    }

    public Object findRoles( int pageIndex, int pageSize) {
        SysRolesDTO rolesDTO = new SysRolesDTO();
        List<SysRoles> rolesList = rolesDao.findAllRoles(pageIndex, pageSize);
        rolesDTO.setRolesList(rolesList);
        rolesDTO.setPageCount(rolesDao.findRoleCount());
        return rolesDTO;
    }

    public Object findRolesByRoleId(String roleId) {
        SysRolesDTO rolesDTO = new SysRolesDTO();
        SysRoles sysRoles = rolesDao.findRoles(roleId);
        rolesDTO.setRoleName(sysRoles.getRoleName());
        rolesDTO.setRoleDescribe(sysRoles.getRoleDescribe());
        return rolesDTO;
    }

    @Transactional
    public Optional<String> deleteRoles(List<String> roleId) {
        try {
            List<SysUserRole> userRoleList = new ArrayList<>();
            for (String rId : roleId) userRoleList = userRoleDao.findRoleId(rId);
            if (userRoleList.size() > 0) {
                return Optional.of("角色被占用,无法删除");
            }else {
                rolesDao.deleteIn(roleId);
            }
            return Optional.of("success");
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Object assignRightsToRoles(String roleId, List<String> rightsIds) {

        SysRoles roles = rolesDao.findRoles(roleId);
        if (roles == null) {
            return Optional.empty();
        }
        //忽略了重复赋予权限
        List<SysRoleRights> roleRightsList = roleRightsDao.findRoleIsRightsId(roleId,rightsIds);
        if(!roleRightsList.isEmpty()){
            return Optional.of("该角色已经有此权限,请重新分配");
        }

        rightsIds.forEach(rId -> {
            SysRoleRights roleRights = new SysRoleRights();
            roleRights.setRoleId(roleId);
            roleRights.setRightsId(rId);
            roleRightsDao.save(roleRights);
        });

        return Optional.of("success");
    }
}
