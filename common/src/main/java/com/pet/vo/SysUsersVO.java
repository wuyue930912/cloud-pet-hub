package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import com.pet.constant.RegConstant;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class SysUsersVO {
    private String userId;
    @NotEmpty(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    private String userName;

    @NotEmpty(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    @Pattern(regexp = RegConstant.PWD_REG, message = ErrorMsgConstant.USER_PWD_NOT_MATCH)
    private String userPwd;

    @NotEmpty(message = ErrorMsgConstant.USER_VALID_PWD_NOT_NULL)
    @Pattern(regexp = RegConstant.PWD_REG, message = ErrorMsgConstant.USER_VALID_PWD_NOT_MATCH)
    private String validPwd;

    private String userPhone;
    private String userEmail;

    //用于给用户分配角色
    private List<String> roleId;
    //多个用户id
    private List<String> userIds;
}
