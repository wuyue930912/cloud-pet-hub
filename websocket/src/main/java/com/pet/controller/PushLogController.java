package com.pet.controller;

import com.pet.constant.RoomsConstant;
import com.pet.servers.SystemInfoServer;
import com.pet.vo.PushLogVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PushLogController {

    private final SystemInfoServer systemInfoServer;

    @PostMapping("/pushLog")
    public void pushLogToWeb(@RequestBody PushLogVO log) {
        systemInfoServer.sendMsg(RoomsConstant.SYSTEM_INFO, log.toString());
    }
}
