package com.pet.po;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * po类基类，所有数据库表必须带这五个字段
 */
@Data
@MappedSuperclass
class BaseEntity implements Serializable {
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());
    private String createUser;
    private Timestamp modifyTime;
    private String modifyUser;
    private boolean deleteFlag;
}
