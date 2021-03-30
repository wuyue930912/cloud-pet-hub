package com.pet.utils;

import cn.hutool.crypto.digest.BCrypt;

/**
 * 密码加密&校验工具类
 */
public class PasswordUtil {
    private PasswordUtil() {
    }

    /**
     * 加密密码
     *
     * @param password 明文密码
     * @return 加密密码
     */
    public static String encodePassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 校验密码
     *
     * @param password       明文密码
     * @param encodePassword 加密密码
     * @return true/false
     */
    public static boolean match(String password, String encodePassword) {
        return BCrypt.checkpw(password, encodePassword);
    }
}
