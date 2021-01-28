package com.pet.annotation;

import com.pet.constant.LogLevelConstant;

import java.lang.annotation.*;

/**
 * 需要记录日志的方法上用这个注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogController {
    // 具体操作
    String description();

    // 日志级别
    int logLevel() default LogLevelConstant.INFO;

    // 日志进程/方法名
    String method();
}
