package com.pet.scheduling;

import com.alibaba.fastjson.JSONObject;
import com.pet.constant.CronConstant;
import com.pet.constant.RoomsConstant;
import com.pet.servers.SystemInfoServer;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class SystemScheduling {

    private final SystemInfoServer systemInfoServer;

    /**
     * 定时任务例子
     */
    @Async("schedulingTaskExecutor")
    @Scheduled(cron = CronConstant.FIVE_SECOND)
    public void logging() throws MalformedObjectNameException, InstanceNotFoundException, ReflectionException {
        JSONObject json = new JSONObject();
        json.put("cpu", getCpu());
        json.put("memory", Runtime.getRuntime().totalMemory());
        json.put("time", new Date());
        systemInfoServer.sendMsg(RoomsConstant.SYSTEM_INFO, json.toString());
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
