package com.pet.listener;

import com.pet.convert.SysLogConvert;
import com.pet.dao.SysLogDao;
import com.pet.event.LogToDbEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogToDbEventListener implements ApplicationListener<LogToDbEvent> {

    private final SysLogDao sysLogDao;

    @Override
    @Async("logTaskExecutor")
    public void onApplicationEvent(LogToDbEvent logToDbEvent) {
        log.info("save log listener = save log [{}] to db", logToDbEvent.getLogToDbEventEntity().toString());
        // 日志入库
        sysLogDao.save(SysLogConvert.INSTANCE.dto2po(logToDbEvent.getLogToDbEventEntity()));
    }
}
