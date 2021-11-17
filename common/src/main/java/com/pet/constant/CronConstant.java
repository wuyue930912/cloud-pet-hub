package com.pet.constant;

public class CronConstant {
    /**
     * 每小时执行一次
     */
    public static final String ONE_HOUR = "0 0 0/1 * * ? ";

    /**
     * 每1秒执行一次
     */
    public static final String ONE_SECOND = "0/1 * * * * ? ";

    /**
     * 每5秒执行一次
     */
    public static final String FIVE_SECOND = "0/5 * * * * ? ";
}
