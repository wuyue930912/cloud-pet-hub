package com.pet.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResultVO<T> {
    private long code;
    private String msg;
    private T data;
}
