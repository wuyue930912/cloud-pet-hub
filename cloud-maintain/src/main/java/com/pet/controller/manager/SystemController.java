package com.pet.controller.manager;

import com.pet.annotation.LogController;
import com.pet.annotation.TimeConsuming;
import com.pet.constant.ErrorMsgConstant;
import com.pet.constant.LogLevelConstant;
import com.pet.service.manager.SystemService;
import com.pet.vo.ResponseResultVO;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@Api(value = "系统管理模块", tags = "提供系统管理服务")
@RequestMapping("/api/system")
public class SystemController {

    private final SystemService systemService;

    private static final String CLEAR_LOG = "清除日志";

    @DeleteMapping("/clearLog/{type}")
    @LogController(description = CLEAR_LOG, logLevel = LogLevelConstant.WARN, method = "clearLog")
    @TimeConsuming
    @ApiOperation(value = CLEAR_LOG, notes = "清除系统日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "日志类型", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = ErrorMsgConstant.SUCCESS)
    })
    public ResponseEntity<ResponseResultVO<String>> clearLog(@PathVariable String type) {
        log.info("start clear log, log type : {}", type);
        return ResponseEntity.ok(systemService.clearLog(type));
    }

}
