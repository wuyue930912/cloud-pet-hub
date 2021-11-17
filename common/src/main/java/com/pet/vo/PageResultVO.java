package com.pet.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "分页查询结果通用VO", description = "分页查询数据结果通用前端上行参数")
public class PageResultVO<T> {
    @ApiModelProperty(value = "结果集", position = 1)
    private T result;
    @ApiModelProperty(value = "总数", position = 2)
    private long count;
}
