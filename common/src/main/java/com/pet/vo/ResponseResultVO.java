package com.pet.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResultVO {
    private long code;
    private String msg;
    private Object data;
}
