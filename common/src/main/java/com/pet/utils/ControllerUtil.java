package com.pet.utils;

import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.ErrorMsgConstant;
import com.pet.vo.ResponseResultVO;

public class ControllerUtil {
    private ControllerUtil() {
    }

    public static ResponseResultVO getErrorResultVO() {
        return ResponseResultVO.builder()
                .code(ErrorCodeConstant.SYSTEM_ERROR)
                .msg(ErrorMsgConstant.SYSTEM_ERROR)
                .build();
    }

    public static ResponseResultVO getErrorResultVO(Long errorCode, String errorMsg) {
        return ResponseResultVO.builder()
                .code(errorCode)
                .msg(errorMsg)
                .build();
    }

    public static ResponseResultVO getErrorResultVO(Long errorCode, String errorMsg, Object data) {
        return ResponseResultVO.builder()
                .data(data)
                .code(errorCode)
                .msg(errorMsg)
                .build();
    }

    public static ResponseResultVO getSuccessResultVO(Object data) {
        return ResponseResultVO.builder()
                .data(data)
                .code(ErrorCodeConstant.SYSTEM_ERROR)
                .msg(ErrorMsgConstant.SYSTEM_ERROR)
                .build();
    }

    public static ResponseResultVO getSuccessResultVO() {
        return ResponseResultVO.builder()
                .code(ErrorCodeConstant.SYSTEM_ERROR)
                .msg(ErrorMsgConstant.SYSTEM_ERROR)
                .build();
    }
}
