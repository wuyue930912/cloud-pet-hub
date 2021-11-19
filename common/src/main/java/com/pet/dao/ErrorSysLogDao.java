package com.pet.dao;

import com.pet.po.ErrorSysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorSysLogDao extends JpaRepository<ErrorSysLog, String> {

    @Modifying
    @Query(value = "delete from ErrorSysLog ")
    void clear();
}
