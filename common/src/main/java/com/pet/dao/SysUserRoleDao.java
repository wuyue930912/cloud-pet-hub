package com.pet.dao;

import com.pet.po.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleDao extends JpaRepository<SysUserRole, String> {

    @Query(value = "select ur from  SysUserRole  ur where ur.roleId in ?2 and ur.userId = ?1 ")
    List<SysUserRole> findUserIsRoleId(String userId, List<String> roleId);

    @Query(value = "select ur from  SysUserRole  ur where ur.userId = ?1 ")
    List<SysUserRole> findRoleIdsByUserId(String userId);

    @Query(value = "select ur from  SysUserRole  ur where ur.roleId = ?1 ")
    List<SysUserRole> findRoleId(String roleId);

    @Query(value = "delete from SysUserRole a where a.userId = ?1")
    @Modifying
    void deleteByUserId(String userId);
}
