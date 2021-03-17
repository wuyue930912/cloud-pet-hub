package com.pet.dao;

import com.pet.po.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleDao  extends JpaRepository<SysUserRole, String> {

}
