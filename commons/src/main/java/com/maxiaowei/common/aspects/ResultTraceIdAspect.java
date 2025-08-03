package com.maxiaowei.common.aspects;

import com.maxiaowei.common.Result;
import com.maxiaowei.common.utils.TraceUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
@Aspect
@Order
public class ResultTraceIdAspect {
    @Pointcut("@annotation(com.maxiaowei.common.annotations.ControllerLog) || execution(* com.maxiaowei.common.webhandler.GlobalExceptionHandler.*(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();
        if (object instanceof Result) {
            ((Result<?>) object).setTraceId(TraceUtils.getTraceId());
        }
        return object;
    }

}
