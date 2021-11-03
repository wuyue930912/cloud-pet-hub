package com.pet.event;

import com.pet.event.entity.MethodTimeToDbEventEntity;
import org.springframework.context.ApplicationEvent;

public class MethodTimeToDbEvent extends ApplicationEvent {
    public MethodTimeToDbEvent(MethodTimeToDbEventEntity eventEntity) {
        super(eventEntity);
        this.methodTimeToDbEventEntity = eventEntity;
    }

    private final MethodTimeToDbEventEntity methodTimeToDbEventEntity;

    public MethodTimeToDbEventEntity getMethodTimeToDbEventEntity() {
        return methodTimeToDbEventEntity;
    }
}
