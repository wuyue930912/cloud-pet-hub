
package com.pet.listener;

import com.pet.convert.ErrorSysLogConvert;
import com.pet.convert.SysLogConvert;
import com.pet.dao.ErrorSysLogDao;
import com.pet.dao.SysLogDao;
import com.pet.event.ErrorLogToDbEvent;
import com.pet.event.LogToDbEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ErrorLogToDbEventListener implements ApplicationListener<ErrorLogToDbEvent> {

    private final ErrorSysLogDao errorSysLogDao;

    @Override
    @Async("logTaskExecutor")
    public void onApplicationEvent(ErrorLogToDbEvent event) {
        log.info("save log listener = save log [{}] to db", event.getLogToDbEventEntity().toString());
        // 日志入库
        errorSysLogDao.save(ErrorSysLogConvert.INSTANCE.dto2po(event.getLogToDbEventEntity()));
    }
}
