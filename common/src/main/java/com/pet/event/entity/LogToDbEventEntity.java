package com.pet.event.entity;

import lombok.Builder;

import java.util.Date;

@Builder
public class LogToDbEventEntity {
    private Date date;
    private String userName;
    private String method;
    private int logLevel;
    private String description;
    private String realMethod;
}
