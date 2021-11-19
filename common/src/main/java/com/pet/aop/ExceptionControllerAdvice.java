package com.pet.aop;

import com.pet.config.ServiceException;
import com.pet.constant.ErrorCodeConstant;
import com.pet.constant.LogLevelConstant;
import com.pet.event.ErrorLogToDbEvent;
import com.pet.event.entity.ErrorLogToDbEventEntity;
import com.pet.vo.ResponseResultVO;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 全局异常拦截
 */
@Component
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @Value("${server.port}")
    private int port;

    private final ApplicationEventPublisher publisher;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseResultVO<String>> bindExceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        String errorMsg = null;
        if (result.hasErrors()) {
            Set<FieldError> fieldErrors = new HashSet<>(result.getFieldErrors());
            errorMsg = fieldErrors.iterator().next().getDefaultMessage();
        }
        publisher.publishEvent(new ErrorLogToDbEvent(
                ErrorLogToDbEventEntity.builder()
                        .date(new Date())
                        .errorCode(400)
                        .description(errorMsg)
                        .logLevel(LogLevelConstant.ERROR)
                        .port(port)
                        .build()));
        return ResponseEntity.badRequest().body(ResponseResultVO.<String>builder().code(ErrorCodeConstant.VALID_ERROR).msg(errorMsg).build());
    }

    @ExceptionHandler(value = AuthorizationException.class)
    @ResponseBody
    public ResponseEntity<ResponseResultVO<String>> authorExceptionHandler(AuthorizationException e) {
        publisher.publishEvent(new ErrorLogToDbEvent(
                ErrorLogToDbEventEntity.builder()
                        .date(new Date())
                        .errorCode(401)
                        .description(e.getMessage())
                        .logLevel(LogLevelConstant.ERROR)
                        .port(port)
                        .build()));
        return ResponseEntity.status(401).body(ResponseResultVO.<String>builder().code(ErrorCodeConstant.VALID_ERROR).msg(e.getMessage()).build());
    }

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResponseEntity<ResponseResultVO<String>> serviceExceptionHandler(ServiceException e) {
        publisher.publishEvent(new ErrorLogToDbEvent(
                ErrorLogToDbEventEntity.builder()
                        .date(new Date())
                        .errorCode(400)
                        .description(e.getMessage())
                        .logLevel(LogLevelConstant.ERROR)
                        .port(port)
                        .build()));
        return ResponseEntity.status(400).body(ResponseResultVO.<String>builder().code(ErrorCodeConstant.VALID_ERROR).msg(e.getMessage()).build());
    }

    /**
     * 获取源IP
     * @param request 请求对象
     * @return  源IP
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-AuthenticationIp");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-AuthenticationIp");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
