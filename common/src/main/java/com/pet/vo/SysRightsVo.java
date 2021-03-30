package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class SysRightsVO {

    private String rightsId;
    @NotBlank(message = ErrorMsgConstant.RIGHTS_NAME_NOT_NULL)
    private String rightsName;
    private String rightsUrl;
    private String rightsIcon;
    @NotBlank(message = ErrorMsgConstant.RIGHT_PARENT_ID_NOT_NULL)
    private String parentId;

}
