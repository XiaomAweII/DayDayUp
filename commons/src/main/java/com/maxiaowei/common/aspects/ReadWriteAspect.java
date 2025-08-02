package com.maxiaowei.common.aspects;

import com.maxiaowei.common.annotations.readwrite.ReadWrite;
import com.maxiaowei.common.annotations.readwrite.ReadWriteRoutingStrategy;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 读写分离Aspect
 * <p>
 * 作者: maxiaowei
 */
@Aspect
public class ReadWriteAspect {
    @Around("@annotation(readWrite)")
    public Object around(ProceedingJoinPoint joinPoint, ReadWrite readWrite) throws Throwable {
        //从ThreadLocal中获取读写策略
        ReadWriteRoutingStrategy readWriteRoutingStrategy = ReadWriteRoutingStrategyHolder.getReadWriteRoutingStrategy();
        // 若选择了强制路由到主库，则执行执行业务
        if (readWriteRoutingStrategy == ReadWriteRoutingStrategy.HIT_MASTER) {
            return joinPoint.proceed();
        }
        // 否则，从@ReadWrite注解中获取读写策略，放到ThreadLocal中，然后去执行业务
        ReadWriteRoutingStrategyHolder.setReadWriteRoutingStrategy(readWrite.value());
        return joinPoint.proceed();
    }
}
