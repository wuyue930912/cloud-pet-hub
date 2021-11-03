package com.pet.utils;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.vo.ResponseResultVO;

/**
 * 构造返回结果工具类
 */
public class ControllerUtil {
    private ControllerUtil() {
    }

    /**
     * 系统异常
     *
     * @return 系统异常
     */
    public static ResponseResultVO getErrorResultVO() {
        return ResponseResultVO.builder()
                .code(ErrorCodeConstant.SYSTEM_ERROR)
                .msg(ErrorMsgConstant.SYSTEM_ERROR)
                .build();
    }

    /**
     * 返回指定异常
     *
     * @param errorCode 指定异常code
     * @param errorMsg  指定异常msg
     * @return 指定异常
     */
    public static ResponseResultVO getErrorResultVO(Long errorCode, String errorMsg) {
        return ResponseResultVO.builder()
                .code(errorCode)
                .msg(errorMsg)
                .build();
    }

    /**
     * 返回指定异常（带数据）
     *
     * @param errorCode 指定异常code
     * @param errorMsg  指定异常msg
     * @param data      返回数据
     * @return 指定异常
     */
    public static ResponseResultVO getErrorResultVO(Long errorCode, String errorMsg, Object data) {
        return ResponseResultVO.builder()
                .data(data)
                .code(errorCode)
                .msg(errorMsg)
                .build();
    }

    /**
     * 处理成功
     *
     * @param data 返回数据
     * @return 处理成功
     */
    public static ResponseResultVO<Object> getSuccessResultVO(Object data) {
        return ResponseResultVO.builder()
                .data(data)
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build();
    }

    /**
     * 处理成功
     *
     * @return 处理成功
     */
    public static ResponseResultVO getSuccessResultVO() {
        return ResponseResultVO.builder()
                .code(ErrorCodeConstant.SUCCESS)
                .msg(ErrorMsgConstant.SUCCESS)
                .build();
    }
}
