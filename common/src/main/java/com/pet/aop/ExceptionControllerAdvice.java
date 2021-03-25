package com.pet.aop;

import com.pet.constant.ErrorCodeConstant;
import com.pet.vo.ResponseResultVO;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 全局异常拦截，直接返回badRequest
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseResultVO> bindExceptionHandler(BindException exception) {
        exception.printStackTrace();
        BindingResult result = exception.getBindingResult();
        String errorMsg = null;
        if (result.hasErrors()) {
            Set<FieldError> fieldErrors = new HashSet<>(result.getFieldErrors());
            errorMsg = fieldErrors.iterator().next().getDefaultMessage();
        }
        return ResponseEntity.badRequest().body(ResponseResultVO.builder().code(ErrorCodeConstant.VALID_ERROR).msg(errorMsg).build());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public ResponseEntity<ResponseResultVO> authorExceptionHandler(AuthorizationException e) {
        return ResponseEntity.status(401).body(ResponseResultVO.builder().code(ErrorCodeConstant.VALID_ERROR).msg(e.getMessage()).build());
    }


}
