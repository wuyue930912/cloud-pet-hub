package com.pet.dao;

import com.pet.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysUserDao extends JpaRepository<SysUser, String> {

    @Query(value = "select a from SysUser a where a.userName = ?1")
    Optional<SysUser> findByUserName(String userName);
}
