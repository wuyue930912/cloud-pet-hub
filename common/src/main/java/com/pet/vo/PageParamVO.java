package com.pet.vo;

import com.pet.constant.ErrorMsgConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "分页查询通用VO", description = "分页查询数据通用前端上行参数")
public class PageParamVO<T> {
    @NotNull(message = ErrorMsgConstant.PAGE_INDEX_NOT_NULL)
    @ApiModelProperty(value = "pageIndex", position = 1, example = "1")
    private Integer pageIndex;
    @NotNull(message = ErrorMsgConstant.PAGE_SIZE_NOT_NULL)
    @ApiModelProperty(value = "pageIndex", position = 2, example = "10")
    private Integer pageSize;
    @ApiModelProperty(value = "搜索条件", position = 3)
    private T searchData;
}
