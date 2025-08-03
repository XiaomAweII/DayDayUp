package com.maxiaowei.common.utils;

import org.slf4j.MDC;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
public class TraceUtils {
    public static final String TRACE_ID = "traceId";
    public static ThreadLocal<String> traceIdThreadLocal = new ThreadLocal<>();

    public static String getTraceId() {
        return traceIdThreadLocal.get();
    }

    public static void setTraceId(String traceId) {
        traceIdThreadLocal.set(traceId);
        MDC.put(TRACE_ID, traceId);
    }

    public static void removeTraceId() {
        traceIdThreadLocal.remove();
        MDC.remove(TRACE_ID);
    }
}
