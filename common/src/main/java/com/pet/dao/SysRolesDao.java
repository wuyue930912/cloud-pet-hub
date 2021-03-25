package com.pet.dao;

import com.pet.po.SysRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRolesDao extends JpaRepository<SysRoles,String> {

    @Modifying
    @Query(value = "delete from SysRoles a where a.roleId in ?1")
    void deleteIn(List<String> roleId);

    @Query(value = "select s from SysRoles s where s.roleId = ?1")
    SysRoles findRoles(String rId);

    Optional<SysRoles> findByRoleName(String roleName);

    @Query(value = "select * from sys_roles  limit ?1 , ?2",nativeQuery = true)
    List<SysRoles> findAllRoles(int pageIndex, int pageSize);
}
