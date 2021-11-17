package com.pet.constant;

public class RegConstant {
    private RegConstant(){
    }

    /**
     * 密码包含字符和数字 6~20位
     */
    public static final String PWD_REG = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";

    public static final String EMAIL_REG = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$";
}
