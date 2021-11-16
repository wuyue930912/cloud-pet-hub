package com.pet.vo.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "搜索用户VO", description = "搜索用户前端上行参数")
public class SearchUserVO {
    @ApiModelProperty(value = "用户名", position = 1, example = "xxp")
    private String userName;
    @ApiModelProperty(value = "电话号码", position = 2, example = "18410261912")
    private String phone;
}
