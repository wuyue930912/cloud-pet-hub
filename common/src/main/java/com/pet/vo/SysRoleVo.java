package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
@Data
public class SysRoleVo {

    @NotBlank(message = ErrorMsgConstant.ROLE_NAME_NOT_NULL)
    private String roleName;
    private String roleDescribe;
}
