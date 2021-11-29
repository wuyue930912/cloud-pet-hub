package com.pet.enums;

import lombok.Getter;

@Getter
public enum ErrorMsgEnum {
    SUCCESS(0, "处理成功", "success"),
    ERROR(-1, "处理失败", "success"),
    USER_ALREADY_EXIST(1, "用户名重复", "user already exist"),
    USER_NOT_EXIST(2, "用户不存在", "user not exist");

    private final Integer code;
    private final String msg;
    private final String msgEn;

    ErrorMsgEnum(Integer code, String msg, String msgEn) {
        this.code = code;
        this.msg = msg;
        this.msgEn = msgEn;
    }
}
