package com.pet.config.aop;

import com.pet.annotation.LogController;
import com.pet.constant.HttpConstant;
import com.pet.event.entity.LogToDbEventEntity;
import com.pet.po.SysUser;
import com.pet.service.manager.PushLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
@Aspect
@Component
@Slf4j
public class PushLogAspect {

    private final PushLogService pushLogService;

    @Pointcut("@annotation(com.pet.annotation.LogController)")
    public void pushPoint() {
    }

    @Before(value = "pushPoint() && @annotation(logController)", argNames = "joinPoint, logController")
    public void beforeController(JoinPoint joinPoint, LogController logController) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(HttpConstant.SESSION_USER);
        String realMethodName = joinPoint.getSignature().getName();
        String requestIp = getIp(request);

        pushLogService.pushDateToWeb(LogToDbEventEntity.builder()
                .date(new Date())
                .userName(Objects.isNull(user) ? "system" : user.getUserName())
                .method(logController.method())
                .logLevel(logController.logLevel())
                .description(logController.description())
                .realMethod(realMethodName)
                .ip(requestIp)
                .build());
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-AuthenticationIp");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-AuthenticationIp");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
