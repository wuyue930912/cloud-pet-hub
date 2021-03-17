package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@Data
public class SysRoleVo {
    private String roleId;
    @NotBlank(message = ErrorMsgConstant.ROLE_NAME_NOT_NULL)
    private String roleName;
    private String roleDescribe;
    //用于给角色分配权限
    private List<String> rightsId;
}
