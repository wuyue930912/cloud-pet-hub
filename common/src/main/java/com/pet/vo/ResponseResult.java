package com.pet.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResult {
    private long code;
    private String msg;
    private Object data;
}
