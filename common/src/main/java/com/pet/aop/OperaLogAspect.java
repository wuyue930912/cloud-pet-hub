package com.pet.aop;

import com.pet.annotation.LogController;
import com.pet.constant.HttpConstant;
import com.pet.po.SysUser;
import com.pet.event.LogToDbEvent;
import com.pet.event.entity.LogToDbEventEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class OperaLogAspect {

    @Resource
    private ApplicationEventPublisher publisher;

    @Pointcut("@annotation(com.pet.annotation.LogController)")
    public void annotationPoint() {
    }

    @Pointcut("@annotation(com.pet.annotation.TimeConsuming)")
    public void methodTimePoint() {
    }

    @Before(value = "annotationPoint() && @annotation(logController)", argNames = "joinPoint, logController")
    public void beforeController(JoinPoint joinPoint, LogController logController) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(HttpConstant.SESSION_USER);
        String realMethodName = joinPoint.getSignature().getName();

        log.info("Aspect = [{}] ,user [{}] , method [{}] , logLevel [{}] , do [{}] , realMethod [{}]",
                new Date(), user.getUserName(), logController.method(), logController.logLevel(), logController.description(), realMethodName);

        // 异步处理日志
        publisher.publishEvent(new LogToDbEvent(
                LogToDbEventEntity.builder()
                        .date(new Date())
                        .userName(user.getUserName())
                        .method(logController.method())
                        .logLevel(logController.logLevel())
                        .description(logController.description())
                        .realMethod(realMethodName)
                        .build()));
    }

    @Around(value = "methodTimePoint()")
    public Object apiTimeConsuming(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        String method = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getName();

        Object ret = pjp.proceed();
        log.info("Aspect = [{}] ,class [{}] , method [{}] , time consuming[{}]", new Date(), className, method, System.currentTimeMillis() - begin);
        return ret;
    }
}
