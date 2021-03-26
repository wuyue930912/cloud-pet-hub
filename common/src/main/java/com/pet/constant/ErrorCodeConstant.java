package com.pet.constant;

public class ErrorCodeConstant {
    private ErrorCodeConstant(){
    }

    /**
     * 处理成功
     */
    public static final Long SUCCESS = 0x01L;
    /**
     * 业务异常
     */
    public static final Long CHECK_ERROR = 0x02L;
    /**
     * 校验异常
     */
    public static final Long VALID_ERROR = 0x03L;
    /**
     * 系统异常
     */
    public static final Long SYSTEM_ERROR = 0x99L;
}
