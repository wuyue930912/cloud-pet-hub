package com.pet.event.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LogToDbEventEntity {
    private Date date;
    private String userName;
    private String method;
    private int logLevel;
    private String description;
    private String realMethod;
    private String ip;
}
