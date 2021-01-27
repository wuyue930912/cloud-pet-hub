package com.pet.event;

import com.pet.event.entity.LogToDbEventEntity;
import org.springframework.context.ApplicationEvent;

public class LogToDbEvent extends ApplicationEvent {
    public LogToDbEvent(LogToDbEventEntity logToDbEventEntity) {
        super(logToDbEventEntity);
        this.logToDbEventEntity = logToDbEventEntity;
    }

    private LogToDbEventEntity logToDbEventEntity;

    public LogToDbEventEntity getLogToDbEventEntity() {
        return logToDbEventEntity;
    }
}
