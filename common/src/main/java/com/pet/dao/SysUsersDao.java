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

    /**
     * 查询某个用户
     * @param userId
     * @return
     */
    @Query(value = "select s from SysUsers s where s.userId = ?1")
    SysUsers findUsers(String userId);

    /**
     * 物理删除某个或多个用户
     * @param userId
     */
    @Modifying
    @Query(value = "delete from SysUsers a where a.userId in ?1")
    void deleteIn(List<String> userId);

    /**
     * 逻辑删除
     * @param userId
     */
    @Modifying
    void logicDelete(String userId);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    Optional<SysUsers> findByUserName(String userName);

    /**
     * 查询所有用户和相应角色（分页）
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Query(value = "select * from sys_users u LEFT JOIN sys_user_role ur ON u.user_id=ur.user_id left join sys_roles ro on ur.role_id = ro.role_id limit ?1 , ?2",nativeQuery = true)
    List<SysUsers> findAllUsersAndRoles(int pageIndex, int pageSize);

    @Query(value = "select count(user_id) from sys_users",nativeQuery = true)
    Integer findUserCount();
}
