package com.pet.aop;

import com.pet.annotation.LogController;
import com.pet.constant.HttpConstant;
import com.pet.event.LogToDbEvent;
import com.pet.event.entity.LogToDbEventEntity;
import com.pet.po.SysUser;
import lombok.AllArgsConstructor;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * 切面
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class OperaLogAspect {

    private final ApplicationEventPublisher publisher;

    @Pointcut("@annotation(com.pet.annotation.LogController)")
    public void annotationPoint() {
    }

    @Pointcut("@annotation(com.pet.annotation.TimeConsuming)")
    public void methodTimePoint() {
    }

    /**
     * 前置通知，进入具体方法前执行
     *
     * @param joinPoint     切点
     * @param logController 自定义注解LogController
     */
    @Before(value = "annotationPoint() && @annotation(logController)", argNames = "joinPoint, logController")
    public void beforeController(JoinPoint joinPoint, LogController logController) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute(HttpConstant.SESSION_USER);
        String realMethodName = joinPoint.getSignature().getName();

        log.info("Aspect = [{}] ,user [{}] , method [{}] , logLevel [{}] , do [{}] , realMethod [{}]",
                new Date(), user == null ? "system" : user.getUserName(), logController.method(), logController.logLevel(), logController.description(), realMethodName);

        // 异步处理日志
        publisher.publishEvent(new LogToDbEvent(
                LogToDbEventEntity.builder()
                        .date(new Date())
                        .userName(user == null ? "system" : user.getUserName())
                        .method(logController.method())
                        .logLevel(logController.logLevel())
                        .description(logController.description())
                        .realMethod(realMethodName)
                        .build()));
    }

    /**
     * 环绕通知，统计方法执行时常
     * TimeConsuming注解为切入点
     *
     * @param pjp 切点
     */
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
