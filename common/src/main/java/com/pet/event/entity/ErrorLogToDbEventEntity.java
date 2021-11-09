package com.pet.event.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorLogToDbEventEntity {
    private Date date;
    private int logLevel;
    private String description;
    private int errorCode;
}
