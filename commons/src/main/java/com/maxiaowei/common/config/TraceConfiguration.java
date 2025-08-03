package com.maxiaowei.common.config;

import com.maxiaowei.common.aspects.ResultTraceIdAspect;
import com.maxiaowei.common.filters.TraceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述:
 * <p>
 * 作者: maxiaowei
 */
@Configuration(proxyBeanMethods = false)
public class TraceConfiguration {
    @Bean
    public TraceFilter traceFilter() {
        return new TraceFilter();
    }

    @Bean
    public ResultTraceIdAspect fillRequestIdAspect() {
        return new ResultTraceIdAspect();
    }
}

