package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class SysRightsVo {

    private String rightsId;
    @NotBlank(message = ErrorMsgConstant.RIGHTS_NAME_NOT_NULL)
    private String rightsName;
    private String rightsUrl;
    private String rightsIcon;

}
