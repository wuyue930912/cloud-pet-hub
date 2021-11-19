package com.pet.service.manager;

import com.pet.vo.PushLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "websocket")
public interface PushLogService {

    @PostMapping(value = "/pushLog", consumes = "application/json")
    void pushDateToWeb(@RequestBody PushLogVO log);

}
