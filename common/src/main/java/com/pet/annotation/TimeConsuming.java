package com.pet.annotation;

import java.lang.annotation.*;

/**
 * 需要统计耗时的接口
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeConsuming {
}
