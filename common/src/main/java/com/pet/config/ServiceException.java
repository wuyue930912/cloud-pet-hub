package com.pet.config;

public class ServiceException extends RuntimeException {

    public ServiceException() {
        super("系统服务异常");
    }

    public ServiceException(String message) {
        super(message);
    }
}
