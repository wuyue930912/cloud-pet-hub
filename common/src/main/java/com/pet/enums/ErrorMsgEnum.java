package com.pet.enums;

import lombok.Getter;

@Getter
public enum ErrorMsgEnum {
    SUCCESS(0, "处理成功");

    private Integer code;
    private String msg;

    ErrorMsgEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
