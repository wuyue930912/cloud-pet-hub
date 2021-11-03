package com.pet.vo.system;

import com.pet.constant.ErrorMsgConstant;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class AddUserVO {

    @NotNull(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    @NotBlank(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    @Length(max = 64, message = ErrorMsgConstant.USER_NAME_TOO_LONG)
    private String userName;

    @NotNull(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    @NotBlank(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    @Length(max = 64, message = ErrorMsgConstant.USER_PWD_TOO_LONG)
    private String userPwd;

    @Length(min = 11, max = 11, message = ErrorMsgConstant.PHONE_ERROR)
    private String userPhone;

    @Length(max = 64, message = ErrorMsgConstant.EMAIL_TOO_LONG)
    private String userEmail;
}
