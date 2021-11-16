package com.pet.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查询用户结果集VO", description = "分页查询用户结果集VO下行参数")
public class SearchUserResultVO {
    @ApiModelProperty(value = "用户ID", position = 1)
    private String userId;
    @ApiModelProperty(value = "用户名", position = 2)
    private String userName;
    @ApiModelProperty(value = "手机号码", position = 3)
    private String userPhone;
    @ApiModelProperty(value = "电子邮箱", position = 4)
    private String userEmail;
}
