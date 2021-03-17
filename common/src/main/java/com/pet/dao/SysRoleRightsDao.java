package com.pet.dao;

import com.pet.po.SysRoleRights;
import com.pet.po.SysRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRightsDao extends JpaRepository<SysRoleRights,String> {
}
