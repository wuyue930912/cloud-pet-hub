package com.pet.po;

import com.pet.constant.RegConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@Entity
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty(message = "请输入用户名")
    @Column(length = 64)
    private String userName;

    @NotEmpty(message = "请输入密码")
    @Column(length = 128)
    @Pattern(regexp = RegConstant.PWD_REG, message = "密码不符合规范")
    private String userPwd;
    private String userNickName;
    private String userIcon;
    private String userPhone;
    private String userEmail;
}
