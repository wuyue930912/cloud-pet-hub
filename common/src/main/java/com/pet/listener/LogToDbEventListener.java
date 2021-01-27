package com.pet.listener;

import com.pet.event.LogToDbEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogToDbEventListener implements ApplicationListener<LogToDbEvent> {

    @Override
    public void onApplicationEvent(LogToDbEvent logToDbEvent) {
        log.info("save log listener = save log [{}] to db", logToDbEvent.getLogToDbEventEntity().toString());
    }
}
