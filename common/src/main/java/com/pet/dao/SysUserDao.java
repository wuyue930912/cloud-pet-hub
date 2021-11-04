package com.pet.dao;

import com.pet.po.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao extends JpaRepository<SysUser, String> {

    boolean existsByUserName(String userName);

}
