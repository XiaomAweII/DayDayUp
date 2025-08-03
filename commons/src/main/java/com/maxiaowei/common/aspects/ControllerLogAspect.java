package com.maxiaowei.common.aspects;

import cn.hutool.json.JSONUtil;
import com.maxiaowei.common.annotations.NoLog;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
    @Around("@annotation(com.maxiaowei.common.annotations.ControllerLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        long st = System.currentTimeMillis();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object result = null;
        try {
            //打印处理当前请求的完整类名和方法名称
            log.info("接口方法：{}.{}", methodSignature.getDeclaringTypeName(), methodSignature.getName());

            //获取所有要打印的参数，丢到map中，key为参数名称，value为参数的值，然后会将这个map以json格式输出
            Map<String, Object> logParamsMap = new LinkedHashMap<>();
            String[] parameterNames = methodSignature.getParameterNames();
            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                if (parameterIsLog(methodSignature, i)) {
                    //参数名称
                    String parameterName = parameterNames[i];
                    //参数值
                    Object parameterValue = args[i];
                    //将其放入到map中，稍后会以json格式输出
                    logParamsMap.put(parameterName, parameterValue);
                }
            }
            log.info("方法参数列表：{}", JSONUtil.toJsonStr(logParamsMap));

            result = joinPoint.proceed();
            return result;
        } finally {
            //判断方法的返回值是否需要打印？方法上有 @NoLog 注解的，表示结果不打印返回值
            if (this.resultIsLog(methodSignature)) {
                log.info("方法返回值：{}", JSONUtil.toJsonStr(result));
            }
        }
    }

    /**
     * 指定位置的参数是否需要打印出来？
     *
     * @param methodSignature
     * @param paramIndex
     * @return
     */
    private boolean parameterIsLog(MethodSignature methodSignature, int paramIndex) {
        if (methodSignature.getMethod().getParameterCount() == 0) {
            return false;
        }

        // 参数上有 @NoLog注解的不会打印
        Annotation[] parameterAnnotation = methodSignature.getMethod().getParameterAnnotations()[paramIndex];
        if (parameterAnnotation != null && parameterAnnotation.length > 0) {
            for (Annotation annotation : parameterAnnotation) {
                if (annotation.annotationType() == NoLog.class) {
                    return false;
                }
            }
        }

        // 参数类型是下面这些类型的，也不会打印，比如：ServletRequest、ServletResponse
        Class parameterType = methodSignature.getParameterTypes()[paramIndex];
        for (Class<?> type : noLogTypes) {
            if (type.isAssignableFrom(parameterType)) {
                return false;
            }
        }
        return true;
    }

    // 参数类型是下面这些类型的，也不会打印，比如：ServletRequest、ServletResponse，大家可以扩展
    private static List<Class<?>> noLogTypes = Arrays.asList(ServletRequest.class, ServletResponse.class);

    /**
     * 判断方法的返回值是否需要打印？方法上有 @NoLog 注解的，表示结果不打印返回值
     *
     * @param methodSignature
     * @return
     */
    private boolean resultIsLog(MethodSignature methodSignature) {
        return methodSignature.getMethod().getAnnotation(NoLog.class) == null;
    }
}
