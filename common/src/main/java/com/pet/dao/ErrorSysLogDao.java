package com.pet.dao;

import com.pet.po.ErrorSysLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorSysLogDao extends JpaRepository<ErrorSysLog, String> {

}
