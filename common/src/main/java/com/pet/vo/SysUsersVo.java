package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import com.pet.constant.RegConstant;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

@Data
public class SysUsersVo {
    private String userId;
    @NotBlank(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    private String userName;
    @NotEmpty(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    @Pattern(regexp = RegConstant.PWD_REG, message = ErrorMsgConstant.USER_PWD_NOT_MATCH)
    private String userPwd;
    @NotEmpty(message = ErrorMsgConstant.USER_VALID_PWD_NOT_NULL)
    @Pattern(regexp = RegConstant.PWD_REG, message = ErrorMsgConstant.USER_VALID_PWD_NOT_MATCH)
    private String validPwd;
    private String userPhone;
    private String userEmail;
}
