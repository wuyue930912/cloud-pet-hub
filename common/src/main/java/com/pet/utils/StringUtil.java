package com.pet.utils;

import java.util.Objects;

/**
 * 字符串工具类
 */
public class StringUtil {
    private StringUtil() {

    }

    /**
     * 判断字符串是否为空或""
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }

    /**
     * 比较两个字符串的内容是否一样，去除两端空格
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return true/false
     */
    public static boolean equalsValue(String str1, String str2) {
        return str1.trim().equals(str2.trim());
    }

}
