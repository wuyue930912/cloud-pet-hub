package com.pet.dao;

import com.pet.po.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogDao extends JpaRepository<SysLog, String> {

}
