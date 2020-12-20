package com.pet.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());
    private String createUser;
    private Timestamp modifyTime;
    private String modifyUser;
    private int isDelete = 0;
}
