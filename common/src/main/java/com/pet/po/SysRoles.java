package com.pet.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRoles extends BaseEntity{

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String roleId;
  private String roleName;
  private String roleDescribe;

}
