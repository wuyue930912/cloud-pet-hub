package com.pet.scheduling;

import com.pet.constant.CronConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.util.Date;

/**
 * 系统定时任务
 */
@Slf4j
@Component
public class SystemScheduling {

    /**
     * 定时任务例子
     */
    @Async("schedulingTaskExecutor")
    @Scheduled(cron = CronConstant.ONE_HOUR)
    public void logging() throws MalformedObjectNameException, InstanceNotFoundException, ReflectionException {
        log.info("SystemScheduling = now : [{}] memory : [{}] cpu : [{}]", new Date(), Runtime.getRuntime().totalMemory() / (1024 * 1024) + "M", getCpu());
    }

    private int getCpu() throws MalformedObjectNameException, ReflectionException, InstanceNotFoundException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{"ProcessCpuLoad"});
        if (list.isEmpty()) return 0;
        Attribute att = (Attribute) list.get(0);
        Double value = (Double) att.getValue();
        if (value == -1.0) return 0;
        return (int) ((int) (value * 1000) / 10.0);
    }
}
