package com.pet.event;

import com.pet.event.entity.ErrorLogToDbEventEntity;
import com.pet.event.entity.LogToDbEventEntity;
import org.springframework.context.ApplicationEvent;

public class ErrorLogToDbEvent extends ApplicationEvent {
    public ErrorLogToDbEvent(ErrorLogToDbEventEntity errorLogToDbEventEntity) {
        super(errorLogToDbEventEntity);
        this.errorLogToDbEventEntity = errorLogToDbEventEntity;
    }

    private final ErrorLogToDbEventEntity errorLogToDbEventEntity;

    public ErrorLogToDbEventEntity getLogToDbEventEntity() {
        return errorLogToDbEventEntity;
    }
}
