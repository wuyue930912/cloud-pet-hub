package com.pet.constant;

public class ErrorCodeConstant {
    private ErrorCodeConstant(){
    }

    /**
     * 处理成功
     */
    public final static Long SUCCESS = 0x01L;
    /**
     * 校验失败
     */
    public final static Long VALID_ERROR = 0x03L;
    /**
     * 系统错误
     */
    public final static Long SYSTEM_ERROR = 0x99L;
}
