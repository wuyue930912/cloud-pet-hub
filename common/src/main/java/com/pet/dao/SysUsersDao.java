package com.pet.dao;

import com.pet.po.SysUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SysUsersDao extends JpaRepository<SysUsers, String> {

    @Query(value = "select s from SysUsers s where s.userId = ?1")
    SysUsers findUsers(String userId);

    @Modifying
    @Query(value = "delete from SysUsers a where a.userId in ?1")
    void deleteIn(List<String> userId);

    Optional<SysUsers> findByUserName(String userName);
}
