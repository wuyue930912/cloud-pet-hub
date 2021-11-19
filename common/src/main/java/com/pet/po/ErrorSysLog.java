package com.pet.po;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class ErrorSysLog extends BaseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String description;
    private int logLevel;
    private int errorCode;
    private int port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ErrorSysLog errorSysLog = (ErrorSysLog) o;
        return id != null && Objects.equals(id, errorSysLog.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
