package com.pet.enums;

import lombok.Getter;

@Getter
public enum ErrorMsgEnum {
    SUCCESS(0, "处理成功"),
    USER_ALREADY_EXIST(1, "用户名重复");

    private final Integer code;
    private final String msg;

    ErrorMsgEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
