package com.pet.dao;

import com.pet.po.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SysUserRoleDao  extends JpaRepository<SysUserRole, String> {

    //select * from sys_user_role a where a.role_id in ('a9216c40-b3e6-4e1b-8e7a-7432db99e130') and a.user_id = 'c100dee7-9427-49e4-aead-37de6244a5fb'
    @Query(value = "select ur from  SysUserRole  ur where ur.roleId in ?2 and ur.userId = ?1 ")
    List<SysUserRole> findUserIsRoleId(String userId, List<String> roleId);

    @Query(value = "select ur from  SysUserRole  ur where ur.userId = ?1 ")
    List<SysUserRole> findRoleIdsByUserId(String userId);
}
