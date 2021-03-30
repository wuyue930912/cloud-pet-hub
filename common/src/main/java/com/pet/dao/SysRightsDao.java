package com.pet.dao;

import com.pet.po.SysRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysRightsDao extends JpaRepository<SysRights,String> {


    @Modifying
    @Query(value = "delete from SysRights a where a.rightsId in ?1")
    void deleteIn(List<String> rightId);

    @Query(value = "select r from SysRights r where r.rightsId = ?1")
    SysRights findRights(String rId);

    Optional<SysRights> findByRightsName(String rightsName);

    @Query(value = "select * from sys_rights",nativeQuery = true)
    List<SysRights> findAllRights();
}
