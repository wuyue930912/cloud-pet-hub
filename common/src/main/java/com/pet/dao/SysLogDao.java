package com.pet.dao;

import com.pet.po.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysLogDao extends JpaRepository<SysLog, String> {

    @Modifying
    @Query(value = "delete from SysLog ")
    void clear();
}
