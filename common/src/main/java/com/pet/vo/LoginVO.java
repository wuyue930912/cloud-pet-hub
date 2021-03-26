package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@Builder
public class LoginVO {
    @NotEmpty(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    private String userName;
    @NotEmpty(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    private String password;
}
