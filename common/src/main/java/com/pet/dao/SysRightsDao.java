package com.pet.dao;

import com.pet.po.SysRights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRightsDao extends JpaRepository<SysRights,String> {

}
