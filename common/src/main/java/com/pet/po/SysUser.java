package com.pet.po;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@ToString(callSuper = true)
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String userName;
    private String userPwd;
    private String userPhone;
    private String userEmail;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SysUser sysUser = (SysUser) o;
        return id != null && Objects.equals(id, sysUser.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
