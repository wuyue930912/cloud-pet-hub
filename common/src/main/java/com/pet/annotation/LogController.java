package com.pet.annotation;

import com.pet.constant.LogLevelConstant;

import java.lang.annotation.*;

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
