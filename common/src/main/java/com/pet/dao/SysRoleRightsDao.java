package com.pet.dao;

import com.pet.po.SysRoleRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleRightsDao extends JpaRepository<SysRoleRights,String> {

    @Query(value = "select rr from  SysRoleRights  rr where rr.rightsId in ?2 and rr.roleId = ?1 ")
    List<SysRoleRights> findRoleIsRightsId(String roleId, List<String> rightsIds);

    @Query(value = "select rr from  SysRoleRights  rr where rr.roleId = ?1 ")
    List<SysRoleRights> findRightIdsByRoleId(String roleId);

    @Query(value = "select rr from  SysRoleRights  rr where rr.rightsId = ?1 ")
    List<SysRoleRights> findRightIds(String rightsId);
}
