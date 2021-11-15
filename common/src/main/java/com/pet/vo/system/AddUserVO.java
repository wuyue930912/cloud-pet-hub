package com.pet.vo.system;

import com.pet.constant.ErrorMsgConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ApiModel(value = "新增用户VO", description = "新增用户前端上行参数")
public class AddUserVO {

    @NotNull(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    @NotBlank(message = ErrorMsgConstant.USER_NAME_NOT_NULL)
    @Length(max = 64, message = ErrorMsgConstant.USER_NAME_TOO_LONG)
    @ApiModelProperty(value = "用户名", required = true, position = 1, example = "xxp")
    private String userName;

    @NotNull(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    @NotBlank(message = ErrorMsgConstant.USER_PWD_NOT_NULL)
    @Length(max = 64, message = ErrorMsgConstant.USER_PWD_TOO_LONG)
    @ApiModelProperty(value = "密码", required = true,  position = 2, example = "wuyue930912")
    private String userPwd;

    @Length(min = 11, max = 11, message = ErrorMsgConstant.PHONE_ERROR)
    @ApiModelProperty(value = "手机号", position = 3, example = "18410261912")
    private String userPhone;

    @Length(max = 64, message = ErrorMsgConstant.EMAIL_TOO_LONG)
    @ApiModelProperty(value = "邮箱", position = 4, example = "wuyue930912@live.com")
    private String userEmail;

}
