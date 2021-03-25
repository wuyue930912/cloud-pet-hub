package com.pet.utils;

import cn.hutool.crypto.digest.BCrypt;

public class PasswordUtil {
    private PasswordUtil(){
    }

    public static String encodePassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean match(String password, String encodePassword){
        return BCrypt.checkpw(password, encodePassword);
    }
}
